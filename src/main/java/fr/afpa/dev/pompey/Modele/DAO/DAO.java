package fr.afpa.dev.pompey.Modele.DAO;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
    protected Connection connect = Singleton.getInstanceDB();

    public abstract int create(T obj);

    public abstract boolean delete(T obj);

    public abstract boolean update(T obj);

    public abstract T find(int id);

    public abstract List<T> findAll();

}
