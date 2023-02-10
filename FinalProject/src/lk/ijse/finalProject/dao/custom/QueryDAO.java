package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dto.OrderDetailDTO;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    OrderDetailDTO getOrderDetail() throws SQLException, ClassNotFoundException;
}
