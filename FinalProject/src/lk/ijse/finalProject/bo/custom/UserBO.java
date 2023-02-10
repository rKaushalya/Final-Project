package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserBO extends SuperBO {

    UserDTO checkUser(String name, String password) throws SQLException, ClassNotFoundException;
}
