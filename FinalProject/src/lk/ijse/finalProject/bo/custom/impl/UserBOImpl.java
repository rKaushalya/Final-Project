package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.UserBO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    public static ResultSet checkUser(String name, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE userName = ? AND password = ?";
        return CrudUtil.execute(sql,name,password);
    }
}
