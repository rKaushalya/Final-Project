package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.UserBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    //DI
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO checkUser(String name, String password) throws SQLException, ClassNotFoundException {
        UserEntity userEntity = userDAO.checkUser(name, password);
        return new UserDTO(userEntity.getUserId(),userEntity.getUserName(),userEntity.getPassword(),userEntity.getEmail(),
                userEntity.getRole());
    }
}
