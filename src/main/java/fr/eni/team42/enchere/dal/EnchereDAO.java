package fr.eni.team42.enchere.dal;

import java.util.List;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere, Integer>{

	List <Enchere> selectById(int idArticle) throws BusinessException;
	
	Enchere selectById(int idArticle, int idUser) throws BusinessException;

}
