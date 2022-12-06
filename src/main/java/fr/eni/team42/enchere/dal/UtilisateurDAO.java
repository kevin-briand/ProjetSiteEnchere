package fr.eni.team42.enchere.dal;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur, Integer> {
    Utilisateur selectByPseudo(String pseudo) throws BusinessException;
    Utilisateur selectByEmail(String email) throws BusinessException;
}
