package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.UserBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    //DI
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public ResultSet checkUser(String name, String password) throws SQLException, ClassNotFoundException {
        return userDAO.checkUser(name, password);
    }
}
