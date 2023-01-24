package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.EmployeeDAO;
import lk.ijse.finalProject.entity.EmployeeEntity;

import java.sql.SQLException;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean add(EmployeeEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(EmployeeEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public EmployeeEntity search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
