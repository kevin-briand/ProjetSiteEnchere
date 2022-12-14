package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Categorie;
import fr.eni.team42.enchere.bo.Enchere;
import fr.eni.team42.enchere.bo.Retrait;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DAOFactory;

import java.util.List;

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

	public List<Enchere> selectByUser(int idUser) throws BusinessException {
		return DAOFactory.getEnchereDAO().selectByUser(idUser);
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
