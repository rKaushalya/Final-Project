package lk.ijse.finalProject.model;

import lk.ijse.finalProject.to.User;
import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.SQLException;

public class RegisterModel {
    public static boolean register(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES (?,?,?,?,?)";
        return CrudUtill.execute(sql,user.getUserId(),user.getUserName(),user.getPassword(),user.getEmail(),user.getRole());
    }
}
