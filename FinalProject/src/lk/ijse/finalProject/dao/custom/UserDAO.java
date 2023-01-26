package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<UserEntity,String> {

    ResultSet checkUser(String name, String password) throws SQLException, ClassNotFoundException;
}
