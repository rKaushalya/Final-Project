package lk.ijse.finalProject.dao;

import java.sql.SQLException;

public interface CrudDAO<T,ID> extends SuperDAO{

    boolean add(T entity) throws SQLException, ClassNotFoundException;

    boolean update(T entity) throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    T search(ID id) throws SQLException, ClassNotFoundException;
}
