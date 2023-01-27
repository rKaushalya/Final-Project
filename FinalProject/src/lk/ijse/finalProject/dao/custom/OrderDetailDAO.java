package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.SuperDAO;

import java.sql.SQLException;

public interface OrderDetailDAO extends SuperDAO {

    boolean addOrderDetails(String cId, String orderId) throws SQLException, ClassNotFoundException;
}
