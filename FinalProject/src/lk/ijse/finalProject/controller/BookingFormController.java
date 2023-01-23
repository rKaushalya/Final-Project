package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lk.ijse.finalProject.model.*;
import lk.ijse.finalProject.dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookingFormController {
    public JFXComboBox cmbRoomId;
    public JFXComboBox cmbPkgId;
    public JFXComboBox cmbBikeId;
    public TextField txtDayCount;
    public TextField txtRoomDayCount;
    public Label txtDate;
    public Label txtTotal;
    public Label txtBalance;
    public TableView tblRoom;
    public TableColumn clmType;
    public TableColumn clmAc;
    public TableColumn clmPrice;
    public TableView tblBike;
    public TableColumn clmModel;
    public TableColumn clmBikePrice;
    public TableView tblPkg;
    public TableColumn clmPkgName;
    public TableColumn clmPkgPrice;
    public TableColumn clmInclude;
    public JFXTextField txtCusName;
    public JFXTextField txtCusContact;
    public TableView tblMeal;
    public TableColumn clmMealName;
    public TableColumn clmMealPrice;
    public TableColumn clmMealTime;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public AnchorPane bookingPane;
    public JFXTextField txtCash;
    public JFXButton btnPlaceOrder;
    public Text txtCusId;
    public Label txtOrderId;


    private Matcher userNameMatcher;
    private Matcher emailMatcher;
    private Matcher telMatcher;
    private Matcher address;

    private double showTotal = 0;
    private double getCash = 0;
    private double balance = 0;

    private double price = 0;

    public void initialize(){
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));
        txtCusName.requestFocus();

        loadRegNo();
        loadRoomId();
        loadPkgId();
        setCellValueFactory();
        searchMeal();
        loadOrderId();
        loadNextCusId();
    }

    private void loadOrderId(){
        try {
            String orderId = BookingModel.generateNextOrderId();
            txtOrderId.setText(orderId);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadNextCusId(){
        try {
            String customerId = BookingModel.generateNextCusId();
            txtCusId.setText(customerId);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    public void orderPlaceOnAction(ActionEvent actionEvent) {
        if (btnPlaceOrder.getText().equals("Rent A Room")) {
            try {
                String cusId = txtCusId.getText();
                String name = txtCusName.getText();
                String address = txtAddress.getText();
                String contact = txtCusContact.getText();
                String email = txtEmail.getText();
                String orderId = txtOrderId.getText();
                String date = txtDate.getText();
                int roomDayCount = Integer.parseInt(txtRoomDayCount.getText());
                String rId = String.valueOf(cmbRoomId.getValue());
                Double total = Double.valueOf(txtTotal.getText());
                Double receverdAmount = Double.valueOf(txtCash.getText());
                Double balance = Double.valueOf(txtBalance.getText());

                CustomerDTO customerDTO = new CustomerDTO(cusId,name,address,contact,email);
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderId,date,roomDayCount,rId,total,receverdAmount,balance);

                    boolean isRentRoom = BookingModel.rentRoom(customerDTO, orderDetailDTO);
                    if (isRentRoom){
                        new Alert(Alert.AlertType.CONFIRMATION,"Room Rent Success").show();
                        printBill();
                        clearPane();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Something Wrong.!").show();
                    }
            } catch (Exception exception) {
                System.out.println(exception);
            }

        }else {
            try {
                String cusId = txtCusId.getText();
                String name = txtCusName.getText();
                String address = txtAddress.getText();
                String contact = txtCusContact.getText();
                String email = txtEmail.getText();
                String orderId = txtOrderId.getText();
                String date = txtDate.getText();
                int roomDayCount = Integer.parseInt(txtRoomDayCount.getText());
                String rId = String.valueOf(cmbRoomId.getValue());
                String pkgId = String.valueOf(cmbPkgId.getValue());
                String regNo = String.valueOf(cmbBikeId.getValue());
                int bikeDayCount = Integer.parseInt(txtDayCount.getText());
                Double total = Double.valueOf(txtTotal.getText());
                Double receverdAmount = Double.valueOf(txtCash.getText());
                Double balance = Double.valueOf(txtBalance.getText());

                CustomerDTO customerDTO = new CustomerDTO(cusId, name, address, contact, email);
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderId, date, roomDayCount, rId, pkgId, regNo, bikeDayCount,
                        total, receverdAmount, balance);

                boolean isPlaceOrder = BookingModel.placeOrder(customerDTO, orderDetailDTO);
                if (isPlaceOrder) {
                    clearPane();
                    new Alert(Alert.AlertType.CONFIRMATION, "OrderPlace Success").show();
                    printFullBill();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Wrong").show();
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }

    private void loadRegNo(){
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> arrayList = BookingModel.loadRegNo();

            for (String regNo : arrayList){
                observableList.add(regNo);
            }
            cmbBikeId.setItems(observableList);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadRoomId(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<String> arrayList = RoomModel.loadRoomId();

            for (String id : arrayList){
                observableList.add(id);
            }
            cmbRoomId.setItems(observableList);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadPkgId(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<String> arrayList = BookingModel.loadPkgId();

            for (String id : arrayList){
                observableList.add(id);
            }
            cmbPkgId.setItems(observableList);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void roomIdOnAction(ActionEvent actionEvent) {
        String id = String.valueOf(cmbRoomId.getValue());
        ObservableList<RoomDTO> tmlist = FXCollections.observableArrayList();
        try {
            RoomDTO roomDTO = RoomModel.searchRoom(id);
            price = roomDTO.getPrice();
            tmlist.add(roomDTO);
            tblRoom.setItems(tmlist);
            txtRoomDayCount.requestFocus();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        txtRoomDayCount.requestFocus();
    }

    private void setCellValueFactory() {
        clmType.setCellValueFactory(new PropertyValueFactory("type"));
        clmAc.setCellValueFactory(new PropertyValueFactory("ac"));
        clmPrice.setCellValueFactory(new PropertyValueFactory("price"));
        clmModel.setCellValueFactory(new PropertyValueFactory("model"));
        clmBikePrice.setCellValueFactory(new PropertyValueFactory("pricePerDay"));
        clmPkgName.setCellValueFactory(new PropertyValueFactory("name"));
        clmPkgPrice.setCellValueFactory(new PropertyValueFactory("price"));
        clmInclude.setCellValueFactory(new PropertyValueFactory("include"));
        clmMealName.setCellValueFactory(new PropertyValueFactory("name"));
        clmMealPrice.setCellValueFactory(new PropertyValueFactory("price"));
        clmMealTime.setCellValueFactory(new PropertyValueFactory("availableTime"));
    }

    public void loadRegNoOnAction(ActionEvent actionEvent) {
        String regNo = String.valueOf(cmbBikeId.getValue());
        ObservableList<BikeDTO> load = FXCollections.observableArrayList();
        try {
            BikeDTO bikeDTO = BikeModel.searchBikeTbl(regNo);
            price= bikeDTO.getPricePerDay();
            load.add(bikeDTO);
            tblBike.setItems(load);
            txtDayCount.requestFocus();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void loadPkgOnAction(ActionEvent actionEvent) {
        String regNo = String.valueOf(cmbPkgId.getValue());
        ObservableList<PackagesDTO> tmlist = FXCollections.observableArrayList();
        try {
            PackagesDTO packagesDTO = BookingModel.searchPkg(regNo);
            price = packagesDTO.getPrice();
            tmlist.add(packagesDTO);
            tblPkg.setItems(tmlist);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        showTotal+=price;
        txtTotal.setText(String.valueOf(showTotal));

        cmbBikeId.requestFocus();
        if (cmbPkgId.getValue() != null){
            btnPlaceOrder.setText("Place Order");
        }
    }

    private void searchMeal(){
        try {
            ObservableList<MealDTO> mealDTOS = MealModel.searchAllMeal();
            tblMeal.setItems(mealDTOS);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void slipToContact(ActionEvent actionEvent) {
        setPatten();
        if (userNameMatcher.matches()) {
            txtCusContact.requestFocus();
        }else{
            txtCusName.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToEmail(ActionEvent actionEvent) {
        setPatten();
        if (address.matches()) {
            txtEmail.requestFocus();
        }else{
            txtAddress.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToAddress(ActionEvent actionEvent) {
        setPatten();
        if (telMatcher.matches()) {
            txtAddress.requestFocus();
        }else{
            txtCusContact.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToCmbId(ActionEvent actionEvent) {
        setPatten();
        if (emailMatcher.matches()) {
            cmbRoomId.requestFocus();
        }else {
            txtEmail.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToCmbPkg(ActionEvent actionEvent) {
        if (cmbPkgId.getValue() == null){
            btnPlaceOrder.setText("Rent A Room");

        }
        double count = Double.valueOf(txtRoomDayCount.getText());
        showTotal += price * count;
        txtTotal.setText(String.valueOf(showTotal));

        cmbPkgId.requestFocus();
    }

    public void slipToCash(ActionEvent actionEvent) {
        double count = Double.parseDouble(txtDayCount.getText());
        showTotal += price * count;
        txtTotal.setText(String.valueOf(showTotal));

        txtDayCount.requestFocus();
        txtCash.requestFocus();
    }

    private void clearPane() throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource("/lk/ijse/finalProject/view/bookingForm.fxml"));
        bookingPane.getChildren().clear();
        bookingPane.getChildren().add(node);
    }

    public void calBalance(ActionEvent actionEvent) {
        getCash = Double.parseDouble(txtCash.getText());
        balance = getCash - showTotal;
        txtBalance.setText(String.valueOf(balance));
    }

    private void setPatten(){

        Pattern userNamePatten = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePatten.matcher(txtCusName.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtEmail.getText());

        Pattern telPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        telMatcher = telPattern.matcher(txtCusContact.getText());

        Pattern userAddress = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        address = userAddress.matcher(txtAddress.getText());
    }

    private void printBill(){
        String oId = txtOrderId.getText();
        String name = txtCusName.getText();
        double total = Double.parseDouble(txtTotal.getText());
        double cash = Double.parseDouble(txtCash.getText());
        double balance = Double.parseDouble(txtBalance.getText());
        int day = Integer.parseInt(txtRoomDayCount.getText());

        HashMap bill = new HashMap();

        bill.put("cusName",name);
        bill.put("total",total);
        bill.put("cash",cash);
        bill.put("balance",balance);
        bill.put("amount",total);
        bill.put("dayCount",day);
        try {
            InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/finalProject/report/Bill.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, bill, new JREmptyDataSource(1));

            JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void printFullBill(){
        String oId = txtOrderId.getText();
        String cusId = txtCusId.getText();
        String name = txtCusName.getText();
        String address = txtAddress.getText();
        String contact = txtCusContact.getText();
        double total = Double.parseDouble(txtTotal.getText());
        double cash = Double.parseDouble(txtCash.getText());
        double balance = Double.parseDouble(txtBalance.getText());
        String rId = String.valueOf(cmbRoomId.getValue());
        String pkgId = String.valueOf(cmbPkgId.getValue());
        String regNo = String.valueOf(cmbBikeId.getValue());

        HashMap bill = new HashMap();
        bill.put("oId",oId);
        bill.put("cId",cusId);
        bill.put("name",name);
        bill.put("address",address);
        bill.put("contact",contact);
        bill.put("roomNo",rId);
        bill.put("pkgNo",pkgId);
        bill.put("bikeNo",regNo);
        bill.put("total",total);
        bill.put("cash",cash);
        bill.put("balance",balance);

        try {
            InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/finalProject/report/Blank_A4.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, bill, new JREmptyDataSource(1));

            JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
