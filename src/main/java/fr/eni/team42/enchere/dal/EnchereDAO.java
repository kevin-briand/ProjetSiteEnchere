package fr.eni.team42.enchere.dal;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Enchere;
import fr.eni.team42.enchere.bo.Utilisateur;

public interface EnchereDAO extends DAO<Enchere, Integer>{

	Enchere selectById(Utilisateur utilisateur, ArticleVendu articleVendu) throws BusinessException;

}
