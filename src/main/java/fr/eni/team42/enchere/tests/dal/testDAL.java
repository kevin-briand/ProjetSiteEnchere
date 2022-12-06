package fr.eni.team42.enchere.tests.dal;

import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DAOFactory;


public class testDAL {
    public static void init() {

        System.out.println("---------------BEGIN TEST DAL---------------------");
        System.out.println("CREATE NEW USER 'test'");
        Utilisateur user = new Utilisateur("test","valjean","jean","jean@valjean.fr","0100000000","10 rue de la barre",
                "85710","BOIS DE CENE","gre4g586re4fzegrte6g4ze",0,false);

        System.out.println("CREATE NEW USER 'test2'");
        Utilisateur user2 = new Utilisateur("test2","valjean","gui","gui@valjean.fr","0100000001","1 rue de la barre",
                "85710","BOIS DE CENE","gre4g586re4fzegrte6g4ze",100,true);

        System.out.print("INSERT NEW USER 'test' ");
        try {
            DAOFactory.getUtilisateurDAO().insert(user);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.print("INSERT NEW USER 'test2' ");
        try {
            DAOFactory.getUtilisateurDAO().insert(user2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.print("SELECT BY ID USER 'test' ");
        try {
            Utilisateur u = DAOFactory.getUtilisateurDAO().selectById(user.getIdUtilisateur());
            if(u.getNom().equals("valjean"))
                System.out.println("OK");
            else
                System.out.println("FAIL");

        } catch (Exception e) {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.print("UPDATE USER 'test' (nom=UPDATE) ");
        user.setNom("UPDATE");
        try {
            DAOFactory.getUtilisateurDAO().update(user);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.print("SELECT BY ID USER 'test' ");
        try {
            Utilisateur u = DAOFactory.getUtilisateurDAO().selectById(user.getIdUtilisateur());
            if(u.getNom().equals("UPDATE"))
                System.out.println("OK");
            else
                System.out.println("FAIL");
        } catch (Exception e) {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.print("SELECT BY PSEUDO USER 'test' ");
        try {
            Utilisateur u = DAOFactory.getUtilisateurDAO().selectByPseudo(user.getPseudo());
            if(u.getNom().equals("UPDATE"))
                System.out.println("OK");
            else
                System.out.println("FAIL");
        } catch (Exception e) {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.print("SELECT BY EMAIL USER 'test' ");
        try {
            Utilisateur u = DAOFactory.getUtilisateurDAO().selectByEmail(user.getEmail());
            if(u.getNom().equals("UPDATE"))
                System.out.println("OK");
            else
                System.out.println("FAIL");
        } catch (Exception e) {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.print("DELETE USER 'test' ");
        try {
            DAOFactory.getUtilisateurDAO().delete(user2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println("---------------END TEST DAL---------------------");
    }
}
