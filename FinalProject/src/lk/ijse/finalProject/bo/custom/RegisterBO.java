package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.UserDTO;

import java.sql.SQLException;

public interface RegisterBO extends SuperBO {

    boolean registerUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
