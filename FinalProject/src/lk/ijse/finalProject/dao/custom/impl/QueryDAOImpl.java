package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.QueryDAO;
import lk.ijse.finalProject.dto.OrderDetailDTO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public OrderDetailDTO getOrderDetail() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT orderdetail.cId,orderdetail.orderId,orders.date,orders.stayDayCount,orders.rId,orders.pkgId,orders.regNo FROM orders INNER JOIN\n" +
                "orderdetail ON orders.orderId = orderdetail.orderId;");
    }
}
