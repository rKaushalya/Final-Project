package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.ForgetPasswordBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.entity.UserEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class ForgotPasswordBOImpl implements ForgetPasswordBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    public boolean resetPassword(UserDTO userDTO) throws SQLException, ClassNotFoundException {
       return userDAO.update(new UserEntity(userDTO.getPassword(), userDTO.getUserId(), userDTO.getUserName(), userDTO.getEmail()));
    }
}
