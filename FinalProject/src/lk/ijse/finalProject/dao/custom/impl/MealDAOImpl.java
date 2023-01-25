package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.MealDAO;
import lk.ijse.finalProject.entity.MealEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class MealDAOImpl implements MealDAO {
    @Override
    public boolean add(MealEntity entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO meal VALUES (?,?,?,?)";
        return CrudUtil.execute(sql, entity.getmId(), entity.getName(), entity.getAvailableTime(), entity.getPrice());
    }

    @Override
    public boolean update(MealEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public MealEntity search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
