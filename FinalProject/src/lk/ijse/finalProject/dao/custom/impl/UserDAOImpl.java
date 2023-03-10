package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.entity.UserEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(UserEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO user VALUES (?,?,?,?,?)", entity.getUserId(), entity.getUserName(),
                entity.getPassword(), entity.getEmail(), entity.getRole());
    }

    @Override
    public boolean update(UserEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE user SET password=? WHERE userId=? && userName=? && email=?",
                entity.getPassword(), entity.getUserId(), entity.getUserName(), entity.getEmail());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public UserEntity search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public UserEntity checkUser(String name, String password) throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT * FROM user WHERE userName = ? AND password = ?", name, password);
        if (execute.next()){
            return new UserEntity(execute.getString(1),execute.getString(2),execute.getString(3),
                    execute.getString(4),execute.getString(5));
        }
        return null;
    }
}
