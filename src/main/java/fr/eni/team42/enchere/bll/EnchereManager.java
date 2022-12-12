package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Categorie;
import fr.eni.team42.enchere.bo.Enchere;
import fr.eni.team42.enchere.bo.Retrait;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DAOFactory;

public class EnchereManager {

	public EnchereManager() {
	}
	
	//selectById complet : articleID innerjoin catégorie id +retrait + encheres + userID
	public Enchere selectById(ArticleVendu article, Categorie categories, Retrait retrait, Enchere enchere, Utilisateur utilisateur) throws BusinessException {
		return DAOFactory.getEnchereDAO().selectById(article, categories, retrait, enchere, utilisateur);
	}
	
	//selectByCatName : articleID innerjoin userID
	public Enchere selectByCatName(ArticleVendu article, Utilisateur utilisateur) throws BusinessException {
		return DAOFactory.getEnchereDAO().selectByCatName(article, utilisateur);
	}
	
	//selectByAchatVente : articleID innerjoin userID + encheres
	public Enchere selectByAchatVente(ArticleVendu article, Utilisateur utilisateur, Enchere enchere) {
		return DAOFactory.getEnchereDAO().selectByAchatVente(article, utilisateur, enchere);
	}
	
	//insert "standard", objet enchere solo
	public void addEnchere(Enchere enchere) throws BusinessException{
		DAOFactory.getEnchereDAO().insert(enchere);
	}
	
	//insert "complet" : objet enchere avec article vendu & retrait
	public void addEnchere(Enchere enchere, ArticleVendu article, Retrait retrait) throws BusinessException{
		DAOFactory.getEnchereDAO().insert(enchere, article, retrait);
	}
	
	public void updateEnchere(Enchere enchere) throws BusinessException{
		DAOFactory.getEnchereDAO().update(enchere);
	}

	public void deleteEnchere(Enchere enchere) throws BusinessException{
		DAOFactory.getEnchereDAO().delete(enchere);
	}

}
