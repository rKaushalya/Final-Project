package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.RentBikeDAO;
import lk.ijse.finalProject.entity.RentBikeEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentBikeDAOImpl implements RentBikeDAO {
    @Override
    public boolean add(RentBikeEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO rentbike VALUES (?,?,?,?)", entity.getRegNo(), entity.getModel(),
                entity.getAvailability(), entity.getPricePerDay());
    }

    @Override
    public boolean update(RentBikeEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE rentbike SET model=?,availability=?,pricePerDay=? WHERE regNo=?",
                entity.getModel(), entity.getAvailability(), entity.getPricePerDay(), entity.getRegNo());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM rentbike WHERE regNo=?", id);
    }

    @Override
    public RentBikeEntity search(String id) throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT * FROM rentbike WHERE regNo=?", id);
        if (execute.next()) {
            return new RentBikeEntity(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public int bikeCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM rentbike WHERE availability ='YES' || 'yes'";
        ResultSet execute = CrudUtil.execute(sql);
        int cusCount = 0;
        if (execute.next()) {
            cusCount = execute.getInt(1);
        }
        return cusCount;
    }

    @Override
    public boolean updateAvailability(String regNo) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE rentbike SET availability = 'no' WHERE regNo = ?", regNo);
    }

    @Override
    public ArrayList<String> loadBikeId() throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT regNo FROM rentbike");
        ArrayList<String> addRegNo = new ArrayList<>();
        while (execute.next()) {
            addRegNo.add(execute.getString(1));
        }
        return addRegNo;
    }

    @Override
    public ArrayList<String> loadRegNo() throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT regNo FROM rentbike WHERE availability='YES' || 'yes'");
        ArrayList<String> regNo = new ArrayList<>();

        while (execute.next()) {
            regNo.add(execute.getString(1));
        }
        return regNo;
    }
}
