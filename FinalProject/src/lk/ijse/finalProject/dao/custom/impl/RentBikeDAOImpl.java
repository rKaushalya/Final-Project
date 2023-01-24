package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.RentBikeDAO;
import lk.ijse.finalProject.entity.RentBikeEntity;

import java.sql.SQLException;

public class RentBikeDAOImpl implements RentBikeDAO {
    @Override
    public boolean add(RentBikeEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RentBikeEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RentBikeEntity search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
