package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.dto.OrderDetailDTO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class OrderBOImpl {
    public static boolean addOrder(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES (?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql, orderDetailDTO.getOrderId(), orderDetailDTO.getDate(), orderDetailDTO.getRoomDayCount(),
                orderDetailDTO.getrId(), orderDetailDTO.getPkgId(), orderDetailDTO.getRegNo(), orderDetailDTO.getBikeDayCount());
    }

    public static boolean addPayment(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO paymentdetail VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql, orderDetailDTO.getOrderId(), orderDetailDTO.getDate(), orderDetailDTO.getReceverdAmount(),
                orderDetailDTO.getBalance(), orderDetailDTO.getTotal());
    }

    public static boolean addOrderDetails(String cId,String orderId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orderdetail VALUES (?,?)";
        return CrudUtil.execute(sql,cId,orderId);
    }

    public static boolean addRoomDetails(OrderDetailDTO orderDetailDTO, String cId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO roomorderdetail VALUES (?,?,?,?)";
        return CrudUtil.execute(sql, orderDetailDTO.getrId(),cId, orderDetailDTO.getDate(),
                orderDetailDTO.getRoomDayCount());
    }
}
