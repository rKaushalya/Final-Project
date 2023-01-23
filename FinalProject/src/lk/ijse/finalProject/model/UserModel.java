package lk.ijse.finalProject.model;

import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static ResultSet checkUser(String name, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE userName = ? AND password = ?";
        return CrudUtill.execute(sql,name,password);
    }
}
