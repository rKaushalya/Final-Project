package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.UserBO;
import lk.ijse.finalProject.utill.Navigation;
import lk.ijse.finalProject.utill.Routes;

import java.io.IOException;
import java.sql.ResultSet;

public class UserFormController {
    public AnchorPane pane;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXCheckBox cbxShow;
    public JFXTextField txtPwShow;

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        txtPwShow.setVisible(false);
    }

    public void loadCustomerForm(ActionEvent actionEvent) throws IOException {
        try {
            //Refactor
            ResultSet resultSet = userBO.checkUser(txtUserName.getText(), txtPassword.getText());
            if (resultSet.next()) {
                if (resultSet.getString(5).equals("Admin")) {
                    Navigation.navigate(Routes.CUSTOMER, pane);
                } else {
                    Navigation.navigate(Routes.RECEPTION, pane);
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Enter Right UserName and Password.").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void loadRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.REGISTER, pane);
    }

    public void loadFogetPasswordOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.FORGOT, pane);
    }

    public void showPasswordOnAction(ActionEvent actionEvent) {
        if (cbxShow.isSelected()) {
            txtPwShow.setText(txtPassword.getText());
            txtPwShow.setVisible(true);
            txtPassword.setVisible(false);
            return;
        } else {
            txtPassword.setText(txtPwShow.getText());
            txtPassword.setVisible(true);
            txtPwShow.setVisible(false);
        }
    }

    public void slipToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        loadCustomerForm(actionEvent);
    }
}
