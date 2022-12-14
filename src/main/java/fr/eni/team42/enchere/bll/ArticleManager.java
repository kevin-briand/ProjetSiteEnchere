package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.EtatVenteArticle;
import fr.eni.team42.enchere.dal.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class ArticleManager {

    public ArticleManager() {
    }

    public ArticleVendu selectById(Integer id) throws BusinessException {
        return DAOFactory.getArticleDAO().selectById(id);
    }

    public void addArticle(ArticleVendu articleVendu) throws BusinessException {
        validerArticle(articleVendu);
        DAOFactory.getArticleDAO().insert(articleVendu);
    }

    public void updateArticle(ArticleVendu articleVendu) throws BusinessException {
        validerArticle(articleVendu);
        DAOFactory.getArticleDAO().update(articleVendu);
    }

    public void removeArticle(ArticleVendu articleVendu) throws BusinessException {
        DAOFactory.getArticleDAO().delete(articleVendu);
    }

    public void validerArticle(ArticleVendu articleVendu) throws BusinessException {
        if (articleVendu.getNomArticle() == null
                || articleVendu.getDescription() == null
                || articleVendu.getDateDebutEnchere() == null
                || articleVendu.getDateFinEnchere() == null
                || articleVendu.getPrixInitial() == null
                || articleVendu.getPrixVente() == null
                || articleVendu.getEtatVenteArticle() == null) {
            throw new BusinessException(BLLExceptionCode.ERREUR_VALIDATION);
        }
        if (articleVendu.getDateDebutEnchere().isEqual(articleVendu.getDateFinEnchere())
                || articleVendu.getDateDebutEnchere().isAfter(articleVendu.getDateFinEnchere())) {
            throw new BusinessException(BLLExceptionCode.ERREUR_VALIDATION);
        }
        if (articleVendu.getPrixInitial() > articleVendu.getPrixVente()) {
            throw new BusinessException(BLLExceptionCode.ERREUR_VALIDATION);
        }
    }

    public List<ArticleVendu> recherche (Integer utilisateurId, String nomArticle, String libelleCategorie, boolean encheresOuvertes, boolean encheresEnCours, boolean encheresRemportees, boolean ventesEnCours, boolean ventesNonDebutees, boolean ventesTerminees) throws BusinessException {
        List<ArticleVendu> articles = new ArrayList<>();
        if (nomArticle != null && libelleCategorie != null) {
            articles = DAOFactory.getArticleDAO().selectByNomArticleEtCategorie(nomArticle, libelleCategorie);
        } else if (nomArticle == null && libelleCategorie != null) {
            articles = DAOFactory.getArticleDAO().selectByCategorie(libelleCategorie);
        } else if (nomArticle != null && libelleCategorie == null) {
            articles = DAOFactory.getArticleDAO().selectByNomArticle(nomArticle);
        } else {
            articles = DAOFactory.getArticleDAO().selectAll();
        }

        List<ArticleVendu> articlesFiltres = new ArrayList<>();

        for (ArticleVendu article : articles) {
            if (encheresOuvertes && article.getEtatVenteArticle().equals(EtatVenteArticle.EN_COURS)) {
                articlesFiltres.add(article);
            }
            if (encheresEnCours && article.getEtatVenteArticle().equals(EtatVenteArticle.EN_COURS)
                    && article.getEnchere().getUtilisateur().getIdUtilisateur() == utilisateurId) {
                articlesFiltres.add(article);
            }
            if (encheresRemportees && article.getEtatVenteArticle().equals(EtatVenteArticle.TERMINEE)
                    && article.getEnchere().getUtilisateur().getIdUtilisateur() == utilisateurId) {
                articlesFiltres.add(article);
            }
            if (ventesEnCours && article.getEtatVenteArticle().equals(EtatVenteArticle.EN_COURS)
                    && article.getUtilisateur().getIdUtilisateur() == utilisateurId) {
                articles.add(article);
            }
            if (ventesNonDebutees && article.getEtatVenteArticle().equals(EtatVenteArticle.NON_DEBUTEE)
                    && article.getUtilisateur().getIdUtilisateur() == utilisateurId) {
                articles.add(article);
            }
            if (ventesTerminees && article.getEtatVenteArticle().equals(EtatVenteArticle.TERMINEE)
                    && article.getUtilisateur().getIdUtilisateur() == utilisateurId) {
                articles.add(article);
            }
        }
        return articlesFiltres;
    }
}
