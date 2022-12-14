package fr.eni.team42.enchere.bll;

import java.util.List;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.Enchere;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DAOFactory;

public class EnchereManager {

	public EnchereManager() {
	}
	
	//selectById complet : articleID innerjoin catégorie id +retrait + encheres + userID
	public List<Enchere> selectById(int idArticle) throws BusinessException {
		return DAOFactory.getEnchereDAO().selectById(idArticle);
	}
	
	public Enchere selectById(int idArticle, Utilisateur user) throws BusinessException {
		return DAOFactory.getEnchereDAO().selectById(idArticle, user.getIdUtilisateur());
	}
	
	public void addEnchere(Enchere enchere) throws BusinessException{
		DAOFactory.getEnchereDAO().insert(enchere);
	}
	
	public void updateEnchere(Enchere enchere) throws BusinessException{
		DAOFactory.getEnchereDAO().update(enchere);
	}

	public void deleteEnchere(Enchere enchere) throws BusinessException{
		DAOFactory.getEnchereDAO().delete(enchere);
	}

}
