package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.entity.PaymentDetailEntity;

import java.sql.SQLException;

public interface PaymentDAO extends SuperDAO {

    boolean addPayment(PaymentDetailEntity paymentDetailEntity) throws SQLException, ClassNotFoundException;
}
