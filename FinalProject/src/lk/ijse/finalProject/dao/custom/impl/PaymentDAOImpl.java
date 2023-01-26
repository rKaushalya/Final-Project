package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.PaymentDAO;
import lk.ijse.finalProject.entity.PaymentDetailEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean addPayment(PaymentDetailEntity paymentDetailEntity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO paymentdetail VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql, paymentDetailEntity.getOrderId(), paymentDetailEntity.getDate(),
                paymentDetailEntity.getRecevedAmount(), paymentDetailEntity.getBalance(), paymentDetailEntity.getTotal());
    }
}
