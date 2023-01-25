package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class RegisterBOImpl {
    public static boolean register(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql, userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getRole());
    }
}
