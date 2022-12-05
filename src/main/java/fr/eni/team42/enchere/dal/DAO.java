package fr.eni.team42.enchere.dal;

public interface DAO<T,S> {

    T selectById(S id);
    void insert(T obj);
    void update(T obj);
    void delete(T obj);
}
