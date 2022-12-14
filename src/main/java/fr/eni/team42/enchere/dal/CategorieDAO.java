package fr.eni.team42.enchere.dal;

import java.util.List;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.Categorie;

public interface CategorieDAO extends DAO<Categorie, Integer>{
	
    List<Categorie> selectAllCategories() throws BusinessException;
	
}
