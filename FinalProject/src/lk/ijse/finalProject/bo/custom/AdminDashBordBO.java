package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;

import java.sql.SQLException;

public interface AdminDashBordBO extends SuperBO {

    int LoadBikeCount() throws SQLException, ClassNotFoundException;

    int getAllRoomCount() throws SQLException, ClassNotFoundException;

    int getNotAvailableRoomCount() throws SQLException, ClassNotFoundException;

    int getAllEmployeeCount() throws SQLException, ClassNotFoundException;
}
