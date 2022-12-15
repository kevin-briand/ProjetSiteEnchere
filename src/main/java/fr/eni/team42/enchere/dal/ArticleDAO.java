package fr.eni.team42.enchere.dal;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Enchere;

import java.util.List;

public interface ArticleDAO extends DAO<ArticleVendu, Integer> {

    List<ArticleVendu> selectAll() throws BusinessException;
    List<ArticleVendu> selectByCategorie(String libelle) throws BusinessException;

    List<ArticleVendu> selectByNomArticle(String nomArticle) throws BusinessException;

    List<ArticleVendu> selectByNomArticleEtCategorie(String nomArticle, String libelle) throws BusinessException;

    void updatePrixVente(Enchere enchere) throws BusinessException;

}
