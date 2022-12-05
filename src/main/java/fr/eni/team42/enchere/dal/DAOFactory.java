package fr.eni.team42.enchere.dal;

import fr.eni.team42.enchere.dal.jdbc.UtilisateurJdbcImpl;

public class DAOFactory {

    public static DAO getUtilisateurDAO() {
        return new UtilisateurJdbcImpl();
    }
}
