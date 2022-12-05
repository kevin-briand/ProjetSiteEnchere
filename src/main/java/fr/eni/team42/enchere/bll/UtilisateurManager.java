package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.UtilisateurDAO;
import fr.eni.team42.enchere.dal.jdbc.UtilisateurJdbcImpl;


public class UtilisateurManager {

    private UtilisateurDAO utilisateurDAO = new UtilisateurJdbcImpl();

    public UtilisateurManager() throws BLLException {
    }

    public Utilisateur selectById(Integer id) throws BLLException {
        return utilisateurDAO.selectById(id);
    }

    public Utilisateur selectByPseudo(String pseudo) throws BLLException {
        return utilisateurDAO.selectByPseudo(pseudo);
    }

    public void addUtilisateur(Utilisateur u) throws BLLException {
        validerUtilisateur(u);
        utilisateurDAO.insert(u);

    }

    public void updateUtilisateur(Utilisateur u) throws BLLException {
        validerUtilisateur(u);
        utilisateurDAO.update(u);
    }

    public void removeUtilisateur(Utilisateur u) throws BLLException {
        utilisateurDAO.delete(u);
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

}
