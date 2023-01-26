package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.RegisterBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.entity.UserEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class RegisterBOImpl implements RegisterBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    public boolean registerUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.add(new UserEntity(userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword(),
                userDTO.getEmail(), userDTO.getRole()));
    }
}
