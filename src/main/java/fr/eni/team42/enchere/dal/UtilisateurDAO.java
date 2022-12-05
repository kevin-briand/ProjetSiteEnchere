package fr.eni.team42.enchere.dal;

import fr.eni.team42.enchere.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur, Integer> {

    Utilisateur selectByPseudo(String pseudo);

}
