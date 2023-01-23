package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.finalProject.model.BikeModel;
import lk.ijse.finalProject.model.BookingModel;
import lk.ijse.finalProject.to.Bike;
import lk.ijse.finalProject.to.Customer;
import lk.ijse.finalProject.to.OrderDetail;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RentBikeFormController {
    public AnchorPane pane;
    public Label txtDate;
    public JFXComboBox cmbRegNo;
    public JFXTextField txtCusName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public Label txtCusId;
    public JFXTextField txtRegNo;
    public JFXTextField txtModel;
    public JFXTextField txtPrice;
    public JFXTextField txtAvailability;
    public TableView tblBike;
    public TableColumn clmModel;
    public TableColumn clmPrice;
    public TableColumn clmAvailability;

    private Matcher userNameMatcher;
    private Matcher emailMatcher;
    private Matcher telMatcher;
    private Matcher address;

    public void initialize(){
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));

        loadRegNo();
        setCellValueFactory();
        loadNextCusId();
    }

    private void loadNextCusId(){
        try {
            String customerId = BookingModel.generateNextCusId();
            txtCusId.setText(customerId);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void rentOnAction(ActionEvent actionEvent) {
        String cusId = txtCusId.getText();
        String name = txtCusName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String regNo = String.valueOf(cmbRegNo.getValue());

        Customer customer = new Customer(cusId,name,address,contact,email);
        try {
            boolean isRentBike = BikeModel.rentBike(customer, regNo);
            if (isRentBike){
                clearPane();
                new Alert(Alert.AlertType.CONFIRMATION,"Bike rent Success.").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void addOnAction(ActionEvent actionEvent) {
        String regNo = txtRegNo.getText();
        String model = txtModel.getText();
        String availability = txtAvailability.getText();
        Double pricePerDay = Double.valueOf(txtPrice.getText());

        Bike bike = new Bike(regNo,model,availability,pricePerDay);
        try {
            boolean isAdded = BikeModel.addBike(bike);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"added Success").show();
                clearText();
                loadRegNo();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wromg").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String regNo = txtRegNo.getText();
        String model = txtModel.getText();
        String availability = txtAvailability.getText();
        Double pricePerDay = Double.valueOf(txtPrice.getText());

        Bike bike = new Bike(regNo,model,availability,pricePerDay);
        try {
            boolean isUpdate = BikeModel.updateBike(bike);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Success").show();
                loadRegNo();
                clearText();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wromg").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String regNo = txtRegNo.getText();
        try {
            boolean isDelete = BikeModel.deleteBike(regNo);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Success").show();
                clearText();
                loadRegNo();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadRegNo(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<String> arrayList = BikeModel.loadBikeId();

            for (String regNo : arrayList){
                observableList.add(regNo);
            }
            cmbRegNo.setItems(observableList);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void clearText(){
        txtRegNo.clear();
        txtModel.clear();
        txtAvailability.clear();
        txtPrice.clear();
    }

    public void bikeDetailOnAction(ActionEvent actionEvent) {
        String regNo = String.valueOf(cmbRegNo.getValue());
        ObservableList<Bike> tmlist = FXCollections.observableArrayList();
        try {
            Bike bike = BikeModel.searchBike(regNo);
            fillText(bike);
            tmlist.add(bike);
            tblBike.setItems(tmlist);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void fillText(Bike bike){
        txtRegNo.setText(bike.getRegNo());
        txtModel.setText(bike.getModel());
        txtPrice.setText(String.valueOf(bike.getPricePerDay()));
        txtAvailability.setText(bike.getAvailability());
    }

    private void setCellValueFactory(){
        clmModel.setCellValueFactory(new PropertyValueFactory("model"));
        clmPrice.setCellValueFactory(new PropertyValueFactory("pricePerDay"));
        clmAvailability.setCellValueFactory(new PropertyValueFactory("availability"));
    }

    private void clearPane() throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource("/lk/ijse/finalProject/view/rentBikeForm.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(node);
    }

    public void slipToAddress(ActionEvent actionEvent) {
        setPatten();
        if (userNameMatcher.matches()) {
            txtAddress.requestFocus();
        }else{
            txtCusName.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToContact(ActionEvent actionEvent) {
        setPatten();
        if (address.matches()) {
            txtContact.requestFocus();
        }else{
            txtAddress.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToEmail(ActionEvent actionEvent) {
        setPatten();
        if (telMatcher.matches()) {
            txtEmail.requestFocus();
        }else{
            txtContact.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToCombo(ActionEvent actionEvent) {
        setPatten();
        if (emailMatcher.matches()) {
            cmbRegNo.requestFocus();
        }else {
            txtEmail.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToModel(ActionEvent actionEvent) {
        txtModel.requestFocus();
    }

    public void slipToPricePerDay(ActionEvent actionEvent) {
        txtPrice.requestFocus();
    }

    public void slipToAvailability(ActionEvent actionEvent) {
        txtAvailability.requestFocus();
    }

    private void setPatten(){

        Pattern userNamePatten = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePatten.matcher(txtCusName.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtEmail.getText());

        Pattern telPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        telMatcher = telPattern.matcher(txtContact.getText());

        Pattern userAddress = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        address = userAddress.matcher(txtAddress.getText());
    }
}
