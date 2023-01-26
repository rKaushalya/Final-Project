package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserBO extends SuperBO {

    ResultSet checkUser(String name, String password) throws SQLException, ClassNotFoundException;
}
