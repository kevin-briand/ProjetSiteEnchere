package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DAOFactory;

public class UtilisateurManager {

    public UtilisateurManager() {
    }

    public Utilisateur selectById(Integer id) throws BusinessException {
        return DAOFactory.getUtilisateurDAO().selectById(id);
    }

    public Utilisateur selectByPseudo(String pseudo) throws BusinessException {
        return DAOFactory.getUtilisateurDAO().selectByPseudo(pseudo);
    }

    public Utilisateur selectByEmail(String email) throws BusinessException {
        return DAOFactory.getUtilisateurDAO().selectByEmail(email);
    }

    public void addUtilisateur(Utilisateur u) throws BusinessException {
        validerUtilisateur(u);
        DAOFactory.getUtilisateurDAO().insert(u);
    }

    public void updateUtilisateur(Utilisateur u) throws BusinessException {
        validerUtilisateur(u);
        DAOFactory.getUtilisateurDAO().update(u);
    }

    public void removeUtilisateur(Utilisateur u) throws BusinessException {
        DAOFactory.getUtilisateurDAO().delete(u);
    }

    public void validerUtilisateur(Utilisateur u) throws BusinessException {
        if (u.getPseudo() == null
                || u.getNom() == null
                || u.getPrenom() == null
                || u.getRue() == null
                || u.getCodePostal() == null
                || u.getVille() == null
                || u.getMdp() == null) {
            throw new BusinessException();
        }
    }

    public Utilisateur logIn(String identifiant, String password) throws BusinessException {
        Utilisateur u;
        if (identifiant.matches(".+@.+\\.[a-z]+")) {
            u = selectByEmail(identifiant);
        } else {
            u = selectByPseudo(identifiant);
        }

        try {
            if (u == null || !u.getMdp().equals(PasswordHashManager.hashMdp(password))) {
                return null;
            }
            return u;
        } catch (Exception e) {
            throw new BusinessException();
        }
    }
}
