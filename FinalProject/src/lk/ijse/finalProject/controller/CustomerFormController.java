package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.finalProject.bo.custom.impl.RentBikeBOImpl;
import lk.ijse.finalProject.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.finalProject.bo.custom.impl.RoomBOImpl;
import lk.ijse.finalProject.utill.Navigation;
import lk.ijse.finalProject.utill.Routes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javafx.collections.FXCollections.observableArrayList;

public class CustomerFormController {
    public AnchorPane pane;
    public Label txtDate;
    public AnchorPane addCustomerPane;
    public Text txtTime;
    public JFXButton btnCusCount;
    public JFXButton btnRoomCount;
    public JFXButton btnEmployeeCount;
    public PieChart piechart;

    public void initialize(){
        setDate();
        setTime();
        loadRoomCount();
        loadEmployeeCount();
        loadAllRoom();
        loadPieChart();
        notAvailableRoom();
    }

    int roomCount;
    int notAvailableRoomCount;

    public void loadPieChart() {
        ObservableList<PieChart.Data> pieChartData = observableArrayList(

                new PieChart.Data("Available Room", roomCount),
                new PieChart.Data("Not Available Room", notAvailableRoomCount));

        piechart.setData(pieChartData);
    }

    private void loadRoomCount(){
        try {
            int count = RentBikeBOImpl.bikeCount();
            btnCusCount.setText(String.valueOf(count));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadAllRoom(){
        try {
            roomCount = RoomBOImpl.loadRoomCount();
            btnRoomCount.setText(String.valueOf(roomCount));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void notAvailableRoom(){
        try {
            notAvailableRoomCount = RoomBOImpl.loadNotAvailableRoomCount();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadEmployeeCount(){
        try {
            int empCount = EmployeeBOImpl.loadAllEmployee();
            btnEmployeeCount.setText(String.valueOf(empCount));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void setDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(dateFormat.format(new Date()));
    }

    private void setTime(){
        Thread clock = new Thread() {
            public void run() {
                while (true) {
                    DateFormat hour = new SimpleDateFormat("hh:mm:ss");
                    txtTime.setText(hour.format(new Date()));

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        clock.start();
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER,pane);
    }

    public void setUi(String ui) throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource(ui));
        addCustomerPane.getChildren().clear();
        addCustomerPane.getChildren().add(node);
    }

    public void loadBookingFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/bookingForm.fxml");
    }

    public void loadCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/viewCustomerForm.fxml");
    }

    public void loadRoomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/roomManageForm.fxml");
    }

    public void loadEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/employeeManageForm.fxml");
    }

    public void loadMealOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/meailManage.fxml");
    }

    public void loadBikeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/rentBikeForm.fxml");
    }

    public void loadReportOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/reportForm.fxml");
    }

    public void loadDashbordOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,pane);
    }
}
