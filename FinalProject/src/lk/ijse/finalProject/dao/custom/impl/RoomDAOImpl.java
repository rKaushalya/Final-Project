package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.RoomDAO;
import lk.ijse.finalProject.entity.RoomEntity;

import java.sql.SQLException;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean add(RoomEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RoomEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RoomEntity search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
