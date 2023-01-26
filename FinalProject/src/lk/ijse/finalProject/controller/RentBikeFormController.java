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
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.RentBikeBO;
import lk.ijse.finalProject.bo.custom.impl.BookingBOImpl;
import lk.ijse.finalProject.dto.BikeDTO;
import lk.ijse.finalProject.dto.CustomerDTO;

import java.io.IOException;
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

    private final RentBikeBO bikeBO = (RentBikeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RENTBIKE);

    public void initialize(){
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));

        loadRegNo();
        setCellValueFactory();
        loadNextCusId();
    }

    private void loadNextCusId(){
        try {
            //Refactor
            String customerId = bikeBO.generateNextCusID();
            txtCusId.setText(customerId);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void rentOnAction(ActionEvent actionEvent) {
        try {
            //Refactor
            boolean isRentBike = bikeBO.rentBike(new CustomerDTO(txtCusId.getText(), txtCusName.getText(),
                    txtAddress.getText(), txtContact.getText(), txtEmail.getText()), String.valueOf(cmbRegNo.getValue()));
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
        try {
            //Refactor
            boolean isAdded = bikeBO.addBike(new BikeDTO(txtRegNo.getText(),txtModel.getText(),txtAvailability.getText(),
                    Double.valueOf(txtPrice.getText())));
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
        try {
            //Refactor
            boolean isUpdate = bikeBO.updateBike(new BikeDTO(txtRegNo.getText(), txtModel.getText(), txtAvailability.getText(),
                    Double.valueOf(txtPrice.getText())));
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
        try {
            //Refactor
            if (bikeBO.deleteBike(txtRegNo.getText())){
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
            //Refactor
            ArrayList<String> arrayList = bikeBO.loadBikeId();
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
        ObservableList<BikeDTO> tmlist = FXCollections.observableArrayList();
        try {
            //Refactor
            BikeDTO bikeDTO = bikeBO.searchBike(String.valueOf(cmbRegNo.getValue()));
            fillText(bikeDTO);
            tmlist.add(bikeDTO);
            tblBike.setItems(tmlist);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void fillText(BikeDTO bikeDTO){
        txtRegNo.setText(bikeDTO.getRegNo());
        txtModel.setText(bikeDTO.getModel());
        txtPrice.setText(String.valueOf(bikeDTO.getPricePerDay()));
        txtAvailability.setText(bikeDTO.getAvailability());
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
