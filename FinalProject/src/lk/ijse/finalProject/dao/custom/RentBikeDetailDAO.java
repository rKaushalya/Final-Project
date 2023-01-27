package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.SuperDAO;

import java.sql.SQLException;

public interface RentBikeDetailDAO extends SuperDAO {

    boolean add(String cusId, String regNo) throws SQLException, ClassNotFoundException;
}
