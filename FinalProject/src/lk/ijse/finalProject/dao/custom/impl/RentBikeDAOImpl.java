package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.RentBikeDAO;
import lk.ijse.finalProject.entity.RentBikeEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
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

    @Override
    public int bikeCount() throws SQLException, ClassNotFoundException {
            String sql = "SELECT COUNT(*) FROM rentbike WHERE availability ='YES' || 'yes'";
            ResultSet execute = CrudUtil.execute(sql);
            int cusCount = 0;
            if (execute.next()){
                cusCount = execute.getInt(1);
            }
            return cusCount;
    }
}
