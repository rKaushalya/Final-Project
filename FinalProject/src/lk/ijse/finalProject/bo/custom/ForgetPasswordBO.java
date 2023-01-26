package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.UserDTO;

import java.sql.SQLException;

public interface ForgetPasswordBO extends SuperBO {

    boolean resetPassword(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
