package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.BikeDTO;
import lk.ijse.finalProject.dto.CustomerDTO;
import lk.ijse.finalProject.dto.OrderDetailDTO;
import lk.ijse.finalProject.dto.PackagesDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingBO extends SuperBO {

    String generateNextOrderId() throws SQLException, ClassNotFoundException;

    String newOrderID(String currentOrderId);

    String generateNextCusID() throws SQLException, ClassNotFoundException;

    String nextCusID(String currentCusId);

    boolean placeOrder(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;

    boolean rentRoom(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;

    BikeDTO searchAllBikes(String regNo) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadAllRegNo() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadAllPackagesIDS() throws SQLException, ClassNotFoundException;

    PackagesDTO searchAllPackages(String id) throws SQLException, ClassNotFoundException;
}
