package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Enchere;
import fr.eni.team42.enchere.bo.EtatVenteArticle;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DAOFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager {

    public ArticleManager() {
    }

    public List<ArticleVendu> selectAll() throws BusinessException {
        return DAOFactory.getArticleDAO().selectAll();
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
        List<ArticleVendu> articles;
        List<ArticleVendu> listeFiltree = new ArrayList<>();
        if (nomArticle != null && libelleCategorie != null) {
            articles = DAOFactory.getArticleDAO().selectByNomArticleEtCategorie(nomArticle, libelleCategorie);
        } else if (nomArticle == null && libelleCategorie != null) {
            articles = DAOFactory.getArticleDAO().selectByCategorie(libelleCategorie);
        } else if (nomArticle != null && libelleCategorie == null) {
            articles = DAOFactory.getArticleDAO().selectByNomArticle(nomArticle);
        } else {
            articles = DAOFactory.getArticleDAO().selectAll();
        }

        //Si pas de filtres achats/ventes actifs
        if(utilisateurId == null || (!ventesEnCours && !ventesTerminees && !ventesNonDebutees && !encheresEnCours && !encheresOuvertes && !encheresRemportees))
            return articles;

        //Filtre
        List<EtatVenteArticle> filtreEtat = new ArrayList<>();
        if (ventesNonDebutees) {
            filtreEtat.add(EtatVenteArticle.NON_DEBUTEE);
        }
        if (encheresOuvertes || encheresEnCours || ventesEnCours) {
            if(!filtreEtat.contains(EtatVenteArticle.EN_COURS))
                filtreEtat.add(EtatVenteArticle.EN_COURS);
        }
        if (encheresRemportees || ventesTerminees) {
            filtreEtat.add(EtatVenteArticle.TERMINEE);
        }

        if(encheresOuvertes || encheresEnCours || encheresRemportees) { //Filtre Achat


            for (ArticleVendu art : articles) {
                //on boucle uniquement si l'état est dans le filtre, pour gagner du temps
                if (filtreEtat.contains(art.getEtatVenteArticle())) {
                    //Si on veux la liste de toutes les enchères en cours
                    if(encheresOuvertes) {
                        listeFiltree.add(art);
                        break;
                    }
                    //récupération des enchères faites par l'utilisateur
                    EnchereManager em = new EnchereManager();
                    Utilisateur user = new Utilisateur();
                    user.setIdUtilisateur(utilisateurId);
                    Enchere e = em.selectById(art.getIdArticle(),user);
                    //Si l'utilisateur à fait une enchère sur l'article, alors on l'ajoute
                    if(e != null)
                        listeFiltree.add(art);
                }
            }
        } else { //Filtre Vente
            for (ArticleVendu art : articles) {
                if (filtreEtat.contains(art.getEtatVenteArticle())
                        && art.getUtilisateur().getIdUtilisateur() == utilisateurId) {
                    listeFiltree.add(art);
                }
            }
        }
        return listeFiltree;
    }

    public void majEtat () throws BusinessException {
        List<ArticleVendu> articles = DAOFactory.getArticleDAO().selectAll();
        for (ArticleVendu articleVendu : articles) {
            ArticleVendu a = DAOFactory.getArticleDAO().selectById(articleVendu.getIdArticle());
            if (LocalDateTime.now().isBefore(a.getDateFinEnchere())
                    && LocalDateTime.now().isAfter(a.getDateDebutEnchere())) {
                a.setEtatVenteArticle(EtatVenteArticle.EN_COURS);
            } else if (LocalDateTime.now().isAfter(a.getDateFinEnchere())) {
                a.setEtatVenteArticle(EtatVenteArticle.TERMINEE);
                //MAJ solde vendeur
                UtilisateurManager um = new UtilisateurManager();
                Utilisateur u = um.selectById(a.getUtilisateur().getIdUtilisateur());
                u.setCredit(u.getCredit() + a.getPrixVente());
                um.updateUtilisateur(u);
            }
            DAOFactory.getArticleDAO().update(a);
        }
    }
}