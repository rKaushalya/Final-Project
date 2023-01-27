package lk.ijse.finalProject.utill;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes routes, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (routes) {
            case CUSTOMER:
                window.setTitle("customer Form");
                initUi("customerForm.fxml");
                break;
            case DASHBORD:
                window.setTitle("DashBord Form");
                initUi("dashBordForm.fxml");
                break;
            case USER:
                window.setTitle("User Form");
                initUi("userForm.fxml");
                break;
            case REGISTER:
                window.setTitle("register Form");
                initUi("registerForm.fxml");
                break;
            case VIEWCUSTOMER:
                window.setTitle("customer Form");
                initUi("viewCustomerForm.fxml");
                break;
            case REPORT:
                window.setTitle("report Form");
                initUi("reportForm.fxml");
                break;
            case FORGOT:
                window.setTitle("Forgot Password Form");
                initUi("fogetPassword.fxml");
                break;
            case RECEPTION:
                window.setTitle("reception Form");
                initUi("dashBordForm.fxml");
                break;
        }
    }

    private static void initUi(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.
                class.getResource("/lk/ijse/finalProject/view/" + location)));
    }

}
