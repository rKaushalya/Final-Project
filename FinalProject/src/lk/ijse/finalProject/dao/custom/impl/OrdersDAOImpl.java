package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.OrdersDAO;
import lk.ijse.finalProject.entity.OrdersEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ResultSet generateNextOrderId() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1");
    }

    @Override
    public ResultSet generateNextCusID() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT cId FROM customer ORDER BY cId DESC LIMIT 1");
    }

    @Override
    public boolean addOrder(OrdersEntity ordersEntity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orders VALUES (?,?,?,?,?,?,?)", ordersEntity.getOrderId(),
                ordersEntity.getDate(), ordersEntity.getStayDayCount(), ordersEntity.getrId(), ordersEntity.getPkgId(),
                ordersEntity.getRegNo(), ordersEntity.getBikeDayCount());
    }
}
