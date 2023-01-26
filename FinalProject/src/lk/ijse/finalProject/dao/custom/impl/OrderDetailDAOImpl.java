package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.OrderDetailDAO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean addOrderDetails(String cId, String orderId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orderdetail VALUES (?,?)",cId,orderId);
    }
}
