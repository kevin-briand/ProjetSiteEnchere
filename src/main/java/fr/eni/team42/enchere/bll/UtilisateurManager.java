package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DAOFactory;


public class UtilisateurManager {

    public UtilisateurManager() throws BLLException {
    }

    public Utilisateur selectById(Integer id) throws Exception {
        try {
            return DAOFactory.getUtilisateurDAO().selectById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Utilisateur selectByPseudo(String pseudo) throws Exception {
        return DAOFactory.getUtilisateurDAO().selectByPseudo(pseudo);
    }

    public Utilisateur selectByEmail(String email) throws Exception {
        return DAOFactory.getUtilisateurDAO().selectByEmail(email);
    }

    public void addUtilisateur(Utilisateur u) throws Exception {
        validerUtilisateur(u);
        DAOFactory.getUtilisateurDAO().insert(u);

    }

    public void updateUtilisateur(Utilisateur u) throws Exception {
        validerUtilisateur(u);
        DAOFactory.getUtilisateurDAO().update(u);
    }

    public void removeUtilisateur(Utilisateur u) throws Exception {
        DAOFactory.getUtilisateurDAO().delete(u);
    }

    public void validerUtilisateur(Utilisateur u) throws BLLException {
        if (u.getPseudo() == null
        || u.getNom() == null
        || u.getPrenom() == null
        || u.getRue() == null
        || u.getCodePostal() == null
        || u.getVille() == null
        || u.getMdp() == null) {
            throw new BLLException();
        }
    }

    public void logIn(String identifiant, String password) throws Exception {
        Utilisateur u = null;
        if (identifiant.matches(".+@.+\\.[a-z]+")) {
            u = selectByEmail(identifiant);
        } else {
            u = selectByPseudo(identifiant);
        }
    }
}
