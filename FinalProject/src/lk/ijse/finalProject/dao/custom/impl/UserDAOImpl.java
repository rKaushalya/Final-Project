package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.entity.UserEntity;

import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(UserEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(UserEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public UserEntity search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
