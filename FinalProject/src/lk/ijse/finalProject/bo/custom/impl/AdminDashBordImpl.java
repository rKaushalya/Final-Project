package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.AdminDashBordBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dao.custom.EmployeeDAO;
import lk.ijse.finalProject.dao.custom.RentBikeDAO;
import lk.ijse.finalProject.dao.custom.RoomDAO;

import java.sql.SQLException;

public class AdminDashBordImpl implements AdminDashBordBO {

    private final RentBikeDAO bikeDAO = (RentBikeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RENTBIKE);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final EmployeeDAO empDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public int LoadBikeCount() throws SQLException, ClassNotFoundException {
        return bikeDAO.bikeCount();
    }

    @Override
    public int getAllRoomCount() throws SQLException, ClassNotFoundException {
        return roomDAO.loadRoomCount();
    }

    @Override
    public int getNotAvailableRoomCount() throws SQLException, ClassNotFoundException {
        return roomDAO.loadNotAvailableRoomCount();
    }

    @Override
    public int getAllEmployeeCount() throws SQLException, ClassNotFoundException {
        return empDAO.loadAllEmployee();
    }
}
