package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.Categorie;
import fr.eni.team42.enchere.dal.DAOFactory;

import java.util.List;

public class CategorieManager {

    public CategorieManager() {
    }

    public List<Categorie> selectAllCategories() throws BusinessException {
        return DAOFactory.getCategorieDAO().selectAllCategories();
    }

    public Categorie selectById(Integer id) throws BusinessException {
        return DAOFactory.getCategorieDAO().selectById(id);
    }

}
