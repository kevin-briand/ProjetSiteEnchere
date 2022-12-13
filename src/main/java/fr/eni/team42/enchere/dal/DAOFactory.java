package fr.eni.team42.enchere.dal;

import fr.eni.team42.enchere.dal.jdbc.ArticleJdbcImpl;
import fr.eni.team42.enchere.dal.jdbc.EnchereJdbcImpl;
import fr.eni.team42.enchere.dal.jdbc.RetraitJdbcImpl;
import fr.eni.team42.enchere.dal.jdbc.UtilisateurJdbcImpl;

public class DAOFactory {

    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurJdbcImpl();
    }
    
    public static EnchereDAO getEnchereDAO() {
    	return new EnchereJdbcImpl();
    }

    public static ArticleDAO getArticleDAO() {
        return new ArticleJdbcImpl();
    }

    public static RetraitDAO getRetraitDAO() {
        return new RetraitJdbcImpl();
    }
}