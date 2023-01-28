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
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.BookingBO;
import lk.ijse.finalProject.bo.custom.RoomBo;
import lk.ijse.finalProject.dto.*;
import lk.ijse.finalProject.view.tdm.BikeTDM;
import lk.ijse.finalProject.view.tdm.MealTDM;
import lk.ijse.finalProject.view.tdm.PackageTDM;
import lk.ijse.finalProject.view.tdm.RoomTDM;
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

    private final RoomBo roomBO = (RoomBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    private Matcher userNameMatcher;
    private Matcher emailMatcher;
    private Matcher telMatcher;
    private Matcher address;

    private final BookingBO bookingBO = (BookingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOKING);

    private double showTotal = 0;
    private double getCash = 0;
    private double balance = 0;

    private double price = 0;

    public void initialize() {
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

    private void loadOrderId() {
        try {
            //Refactor
            String orderId = bookingBO.generateNextOrderId();
            txtOrderId.setText(orderId);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadNextCusId() {
        try {
            //Refactor
            String customerId = bookingBO.generateNextCusID();
            txtCusId.setText(customerId);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    public void orderPlaceOnAction(ActionEvent actionEvent) {
        if (btnPlaceOrder.getText().equals("Rent A Room")) {
            try {
                CustomerDTO customerDTO = new CustomerDTO(txtCusId.getText(), txtCusName.getText(), txtAddress.getText(),
                        txtCusContact.getText(), txtEmail.getText());

                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(txtOrderId.getText(), txtDate.getText(),
                        Integer.parseInt(txtRoomDayCount.getText()), String.valueOf(cmbRoomId.getValue()),
                        Double.valueOf(txtTotal.getText()), Double.valueOf(txtCash.getText()), Double.valueOf(txtBalance.getText()));
                //Refactor
                if (bookingBO.rentRoom(customerDTO, orderDetailDTO)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Rent Success").show();
                    printBill();
                    clearPane();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Wrong.!").show();
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }

        } else {
            try {
                CustomerDTO customerDTO = new CustomerDTO(txtCusId.getText(), txtCusName.getText(), txtAddress.getText(),
                        txtCusContact.getText(), txtEmail.getText());

                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(txtOrderId.getText(), txtDate.getText(),
                        Integer.parseInt(txtRoomDayCount.getText()), String.valueOf(cmbRoomId.getValue()),
                        String.valueOf(cmbPkgId.getValue()), String.valueOf(cmbBikeId.getValue()), Integer.parseInt(txtDayCount.getText()),
                        Double.valueOf(txtTotal.getText()), Double.valueOf(txtCash.getText()), Double.valueOf(txtBalance.getText()));

                //Refactor
                if (bookingBO.placeOrder(customerDTO, orderDetailDTO)) {
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

    private void loadRegNo() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            //Refactor
            ArrayList<String> arrayList = bookingBO.loadAllRegNo();
            for (String regNo : arrayList) {
                observableList.add(regNo);
            }
            cmbBikeId.setItems(observableList);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadRoomId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            //Refactor
            ArrayList<String> allRoomIDS = bookingBO.getAllRoomIDS();

            for (String id : allRoomIDS) {
                observableList.add(id);
            }
            cmbRoomId.setItems(observableList);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadPkgId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            //Refactor
            ArrayList<String> arrayList = bookingBO.loadAllPackagesIDS();
            for (String id : arrayList) {
                observableList.add(id);
            }
            cmbPkgId.setItems(observableList);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void roomIdOnAction(ActionEvent actionEvent) {
        ObservableList<RoomTDM> tmlist = FXCollections.observableArrayList();
        try {
            //Refactor
            RoomTDM roomTDM = roomBO.searchRoom(String.valueOf(cmbRoomId.getValue()));
            price = roomTDM.getPrice();
            tmlist.add(roomTDM);
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
        ObservableList<BikeTDM> load = FXCollections.observableArrayList();
        try {
            //Refactor
            BikeTDM bikeTDM = bookingBO.searchAllBikes(String.valueOf(cmbBikeId.getValue()));
            price = bikeTDM.getPricePerDay();
            load.add(bikeTDM);
            tblBike.setItems(load);
            txtDayCount.requestFocus();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void loadPkgOnAction(ActionEvent actionEvent) {
        ObservableList<PackageTDM> tmlist = FXCollections.observableArrayList();
        try {
            //Refactor
            PackageTDM packageTDM = bookingBO.searchAllPackages(String.valueOf(cmbPkgId.getValue()));
            price = packageTDM.getPrice();
            tmlist.add(packageTDM);
            tblPkg.setItems(tmlist);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        showTotal += price;
        txtTotal.setText(String.valueOf(showTotal));

        cmbBikeId.requestFocus();
        if (cmbPkgId.getValue() != null) {
            btnPlaceOrder.setText("Place Order");
        }
    }

    private void searchMeal() {
        try {
            //Refactor
            ObservableList<MealTDM> mealTDMS = bookingBO.GetAllMeals();
            tblMeal.setItems(mealTDMS);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void slipToContact(ActionEvent actionEvent) {
        setPatten();
        if (userNameMatcher.matches()) {
            txtCusContact.requestFocus();
        } else {
            txtCusName.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToEmail(ActionEvent actionEvent) {
        setPatten();
        if (address.matches()) {
            txtEmail.requestFocus();
        } else {
            txtAddress.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToAddress(ActionEvent actionEvent) {
        setPatten();
        if (telMatcher.matches()) {
            txtAddress.requestFocus();
        } else {
            txtCusContact.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToCmbId(ActionEvent actionEvent) {
        setPatten();
        if (emailMatcher.matches()) {
            cmbRoomId.requestFocus();
        } else {
            txtEmail.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToCmbPkg(ActionEvent actionEvent) {
        if (cmbPkgId.getValue() == null) {
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

    private void setPatten() {

        Pattern userNamePatten = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePatten.matcher(txtCusName.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtEmail.getText());

        Pattern telPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        telMatcher = telPattern.matcher(txtCusContact.getText());

        Pattern userAddress = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        address = userAddress.matcher(txtAddress.getText());
    }

    private void printBill() {
        HashMap bill = new HashMap();

        bill.put("cusName", txtCusName.getText());
        bill.put("total", Double.parseDouble(txtTotal.getText()));
        bill.put("cash", Double.parseDouble(txtCash.getText()));
        bill.put("balance", Double.parseDouble(txtBalance.getText()));
        bill.put("amount", Double.parseDouble(txtTotal.getText()));
        bill.put("dayCount", Integer.parseInt(txtRoomDayCount.getText()));
        try {
            InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/finalProject/view/report/Bill.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, bill, new JREmptyDataSource(1));

            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void printFullBill() {

        HashMap bill = new HashMap();
        bill.put("oId", txtOrderId.getText());
        bill.put("cId", txtCusId.getText());
        bill.put("name", txtCusName.getText());
        bill.put("address", txtAddress.getText());
        bill.put("contact", txtCusContact.getText());
        bill.put("roomNo", String.valueOf(cmbRoomId.getValue()));
        bill.put("pkgNo", String.valueOf(cmbPkgId.getValue()));
        bill.put("bikeNo", String.valueOf(cmbBikeId.getValue()));
        bill.put("total", Double.parseDouble(txtTotal.getText()));
        bill.put("cash", Double.parseDouble(txtCash.getText()));
        bill.put("balance", Double.parseDouble(txtBalance.getText()));

        try {
            InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/finalProject/view/report/Blank_A4.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, bill, new JREmptyDataSource(1));

            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
