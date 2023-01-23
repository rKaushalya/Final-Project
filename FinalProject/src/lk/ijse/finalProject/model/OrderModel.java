package lk.ijse.finalProject.model;

import lk.ijse.finalProject.to.OrderDetail;
import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.SQLException;

public class OrderModel {
    public static boolean addOrder(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES (?,?,?,?,?,?,?)";
        return CrudUtill.execute(sql,orderDetail.getOrderId(),orderDetail.getDate(),orderDetail.getRoomDayCount(),
                orderDetail.getrId(),orderDetail.getPkgId(),orderDetail.getRegNo(),orderDetail.getBikeDayCount());
    }

    public static boolean addPayment(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO paymentdetail VALUES (?,?,?,?,?)";
        return CrudUtill.execute(sql,orderDetail.getOrderId(),orderDetail.getDate(),orderDetail.getReceverdAmount(),
                orderDetail.getBalance(),orderDetail.getTotal());
    }

    public static boolean addOrderDetails(String cId,String orderId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orderdetail VALUES (?,?)";
        return CrudUtill.execute(sql,cId,orderId);
    }

    public static boolean addRoomDetails(OrderDetail orderDetail,String cId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO roomorderdetail VALUES (?,?,?,?)";
        return CrudUtill.execute(sql,orderDetail.getrId(),cId,orderDetail.getDate(),
                orderDetail.getRoomDayCount());
    }
}
