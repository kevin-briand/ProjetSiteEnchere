package fr.eni.team42.enchere.dal;

public interface DAO<T,S> {

    T selectById(S id) throws Exception;
    void insert(T obj) throws Exception;
    void update(T obj) throws Exception;
    void delete(T obj) throws Exception;
}
