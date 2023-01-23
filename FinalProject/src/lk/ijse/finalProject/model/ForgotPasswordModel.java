package lk.ijse.finalProject.model;

import lk.ijse.finalProject.to.User;
import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.SQLException;

public class ForgotPasswordModel {
    public static boolean resetPassword(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET password=? WHERE userId=? && userName=? && email=?";
        return CrudUtill.execute(sql,user.getPassword(),user.getUserId(),user.getUserName(),user.getEmail());
    }
}
