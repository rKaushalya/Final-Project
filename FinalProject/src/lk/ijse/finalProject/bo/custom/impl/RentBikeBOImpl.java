package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.RentBikeBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dao.custom.CustomerDAO;
import lk.ijse.finalProject.dao.custom.OrdersDAO;
import lk.ijse.finalProject.dao.custom.RentBikeDAO;
import lk.ijse.finalProject.dao.custom.RentBikeDetailDAO;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.BikeDTO;
import lk.ijse.finalProject.dto.CustomerDTO;
import lk.ijse.finalProject.entity.CustomerEntity;
import lk.ijse.finalProject.entity.RentBikeEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentBikeBOImpl implements RentBikeBO {

    private final RentBikeDAO bikeDAO = (RentBikeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RENTBIKE);
    private final RentBikeDetailDAO bikeDetailDAO = (RentBikeDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RENTBIKEDETAIL);
    private final CustomerDAO cusDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final OrdersDAO orderDAO = (OrdersDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS);

    public boolean addBike(BikeDTO bikeDTO) throws SQLException, ClassNotFoundException {
        return bikeDAO.add(new RentBikeEntity(bikeDTO.getRegNo(), bikeDTO.getModel(), bikeDTO.getAvailability(),
                bikeDTO.getPricePerDay()));
    }

    public boolean deleteBike(String id) throws SQLException, ClassNotFoundException {
        return bikeDAO.delete(id);
    }

    public ArrayList<String> loadBikeId() throws SQLException, ClassNotFoundException {
        return bikeDAO.loadBikeId();
    }

    public BikeDTO searchBike(String id) throws SQLException, ClassNotFoundException {
        RentBikeEntity search = bikeDAO.search(id);
        return new BikeDTO(search.getRegNo(),search.getModel(),search.getAvailability(),search.getPricePerDay());
    }

    public boolean updateBike(BikeDTO bikeDTO) throws SQLException, ClassNotFoundException {
        return bikeDAO.update(new RentBikeEntity(bikeDTO.getRegNo(), bikeDTO.getModel(), bikeDTO.getAvailability(),
                bikeDTO.getPricePerDay()));
    }

    public boolean rentBike(CustomerDTO customerDTO, String regNo) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            //Refactor
            boolean isAdded = cusDAO.add(new CustomerEntity(customerDTO.getId(),customerDTO.getName(),
                    customerDTO.getAddress(),customerDTO.getContact(),customerDTO.getEmail()));
            if (isAdded) {
                //Refactor
                boolean isBikeDetailAdded = bikeDetailDAO.add(customerDTO.getId(), regNo);
                if (isBikeDetailAdded) {
                    //Refactor
                    boolean isAvailabilityChange = bikeDAO.updateAvailability(regNo);
                    if (isAvailabilityChange) {
                        DBConnection.getDbConnection().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getDbConnection().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }

    public String generateNextCusID() throws SQLException, ClassNotFoundException {
        //Refactor
        ResultSet resultSet = orderDAO.generateNextCusID();
        if (resultSet.next()){
            return nextCusID(resultSet.getString(1));
        }
        return nextCusID(resultSet.getString(null));
    }

    public String nextCusID(String currentCusId) {
        if (currentCusId != null){
            String[] split = currentCusId.split("C0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >= 10){
                return "C0" + id;
            }
            return "C00" + id;
        }
        return "C001";
    }
}
