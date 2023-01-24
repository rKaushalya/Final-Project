package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.CustomerDAO;
import lk.ijse.finalProject.entity.CustomerEntity;

import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(CustomerEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(CustomerEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CustomerEntity search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
