package lk.ijse.finalProject.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.*;
import lk.ijse.finalProject.view.tdm.BikeTDM;
import lk.ijse.finalProject.view.tdm.MealTDM;
import lk.ijse.finalProject.view.tdm.PackageTDM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingBO extends SuperBO {

    String generateNextOrderId() throws SQLException, ClassNotFoundException;

    String newOrderID(String currentOrderId);

    String generateNextCusID() throws SQLException, ClassNotFoundException;

    String nextCusID(String currentCusId);

    boolean placeOrder(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;

    boolean rentRoom(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;

    BikeTDM searchAllBikes(String regNo) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadAllRegNo() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadAllPackagesIDS() throws SQLException, ClassNotFoundException;

    PackageTDM searchAllPackages(String id) throws SQLException, ClassNotFoundException;

    ObservableList<MealTDM> GetAllMeals() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllRoomIDS() throws SQLException, ClassNotFoundException;

}
