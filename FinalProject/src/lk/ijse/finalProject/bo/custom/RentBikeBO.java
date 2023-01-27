package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.BikeDTO;
import lk.ijse.finalProject.dto.CustomerDTO;
import lk.ijse.finalProject.view.tdm.BikeTDM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentBikeBO extends SuperBO {

    boolean addBike(BikeDTO bikeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteBike(String id) throws SQLException, ClassNotFoundException;

    BikeTDM searchBike(String id) throws SQLException, ClassNotFoundException;

    boolean updateBike(BikeDTO bikeDTO) throws SQLException, ClassNotFoundException;

    boolean rentBike(CustomerDTO customerDTO, String regNo) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadBikeId() throws SQLException, ClassNotFoundException;

    String generateNextCusID() throws SQLException, ClassNotFoundException;

    String nextCusID(String currentCusId);
}
