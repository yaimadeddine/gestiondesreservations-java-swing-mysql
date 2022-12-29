package dao;

import java.util.List;

public interface IDAO<T> {

    boolean create(T object);
    boolean update(T object);
    boolean delete(T object);
    T findById(int i);
    List<T> findAll();

}
