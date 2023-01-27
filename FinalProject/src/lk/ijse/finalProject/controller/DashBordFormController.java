package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.DashBordBO;
import lk.ijse.finalProject.utill.Navigation;
import lk.ijse.finalProject.utill.Routes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBordFormController {
    public AnchorPane pane;

    public Label txtDate;
    public Text txtTime;
    public AnchorPane addCustomerPane;
    public JFXButton btnCusCount;
    public JFXButton btnRoomCount;
    public JFXButton btnEmployeeCount;

    private final DashBordBO dashBordBO = (DashBordBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBORD);

    public void initialize() {
        setDate();
        setTime();
        loadAllRoom();
        LoadBikeCount();
        loadEmployeeCount();
    }

    private void LoadBikeCount() {
        try {
            //Refactor
            int count = dashBordBO.LoadBikeCount();
            btnCusCount.setText(String.valueOf(count));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadAllRoom() {
        try {
            //Refactor
            int roomCount = dashBordBO.getAllRoomCount();
            btnRoomCount.setText(String.valueOf(roomCount));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void loadEmployeeCount() {
        try {
            //Refactor
            int empCount = dashBordBO.getAllEmployeeCount();
            btnEmployeeCount.setText(String.valueOf(empCount));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void setTime() {
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

    public void setUi(String ui) throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource(ui));
        addCustomerPane.getChildren().clear();
        addCustomerPane.getChildren().add(node);
    }

    private void setDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(dateFormat.format(new Date()));
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER, pane);
    }

    public void loadBookingFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/bookingForm.fxml");
    }

    public void loadRoomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/roomManageForm.fxml");
    }

    public void loadBikeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/finalProject/view/rentBikeForm.fxml");
    }

    public void loadDashbordOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTION, pane);
    }
}
