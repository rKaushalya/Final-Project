package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.entity.OrdersEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrdersDAO extends SuperDAO {

    ResultSet generateNextOrderId() throws SQLException, ClassNotFoundException;

    ResultSet generateNextCusID() throws SQLException, ClassNotFoundException;

    boolean addOrder(OrdersEntity ordersEntity) throws SQLException, ClassNotFoundException;

}
