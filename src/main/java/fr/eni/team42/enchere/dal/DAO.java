package fr.eni.team42.enchere.dal;

import fr.eni.team42.enchere.BusinessException;

public interface DAO<T,S> {

    T selectById(S id) throws BusinessException;
    T insert(T obj) throws BusinessException;
    void update(T obj) throws BusinessException;
    void delete(T obj) throws BusinessException;
}
