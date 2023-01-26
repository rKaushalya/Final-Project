package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.RentBikeDetailDAO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class RentBikeDetailDAOImpl implements RentBikeDetailDAO {
    @Override
    public boolean add(String cusId, String regNo) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO rentbikedetail VALUES (?,?)",regNo,cusId);
    }
}
