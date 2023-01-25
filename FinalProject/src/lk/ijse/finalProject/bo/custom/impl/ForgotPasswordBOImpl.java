package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class ForgotPasswordBOImpl {
    public static boolean resetPassword(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET password=? WHERE userId=? && userName=? && email=?";
        return CrudUtil.execute(sql, userDTO.getPassword(), userDTO.getUserId(), userDTO.getUserName(), userDTO.getEmail());
    }
}
