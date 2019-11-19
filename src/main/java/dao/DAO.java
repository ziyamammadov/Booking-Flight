package dao;

import java.util.List;

public interface DAO<T> {
    T get(int id);

    List<T> getAll();

    void put(T t);

    void delete(int id);

}
