package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.Retrait;
import fr.eni.team42.enchere.dal.DAOFactory;

public class RetraitManager {

    public RetraitManager() {
    }

    public Retrait selectById(Integer id) throws BusinessException {
        return DAOFactory.getRetraitDAO().selectById(id);
    }

    public void addRetrait(Retrait retrait) throws BusinessException {
        validerRetrait(retrait);
        DAOFactory.getRetraitDAO().insert(retrait);
    }

    public void updateRetrait(Retrait retrait) throws BusinessException {
        validerRetrait(retrait);
        DAOFactory.getRetraitDAO().update(retrait);
    }

    public void removeRetrait(Retrait retrait) throws BusinessException {
        DAOFactory.getRetraitDAO().delete(retrait);
    }

    public void validerRetrait(Retrait retrait) throws BusinessException {
        if (retrait.getRue() == null
                || retrait.getCodePostal() == null
                || retrait.getVille() == null) {
            throw new BusinessException(BLLExceptionCode.ERREUR_VALIDATION);
        }
    }
}
