package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.ForgetPasswordBO;
import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.utill.Navigation;
import lk.ijse.finalProject.utill.Routes;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FogetPasswordController {
    public AnchorPane pane;
    public JFXTextField txtUserId;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtCmPwShow;
    public JFXTextField txtPwShow;
    public JFXPasswordField txtNewPw;
    public JFXPasswordField txtConformPw;
    public JFXCheckBox cbxShowpw;
    public Label lblShowError;

    private Matcher userNameMatcher;
    private Matcher emailMatcher;
    private Matcher pwMatcher;
    private Matcher userIdMatcher;

    private final ForgetPasswordBO fBO = (ForgetPasswordBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.FORGETPASSWORD);

    public void initialize() {
        txtPwShow.setVisible(false);
        txtCmPwShow.setVisible(false);

        //setPatten();
    }

    public void resetOnAction(ActionEvent actionEvent) throws IOException {
        if (txtNewPw.getText().equals(txtConformPw.getText())) {
            try {
                //Refactor
                boolean isUpdate = fBO.resetPassword(new UserDTO(txtUserId.getText(), txtName.getText(),
                        txtNewPw.getText(), txtEmail.getText()));
                if (isUpdate) {
                    Navigation.navigate(Routes.FORGOT, pane);
                    new Alert(Alert.AlertType.CONFIRMATION, "Update success.!").show();
                } else {
                    clearText();
                    new Alert(Alert.AlertType.ERROR, "something wrong.!").show();
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }
        } else {
            lblShowError.setText("Password didnt match");
            txtConformPw.setFocusColor(Paint.valueOf("Red"));
            new Alert(Alert.AlertType.ERROR, "Password didnt match").show();
        }
        //Navigation.navigate(Routes.USER,pane);
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER, pane);
    }

    private void clearText() {
        txtUserId.clear();
        txtName.clear();
        txtEmail.clear();
        txtNewPw.clear();
        txtConformPw.clear();
    }

    public void cbxOnAction(ActionEvent actionEvent) {
        if (cbxShowpw.isSelected()) {

            txtPwShow.setText(txtNewPw.getText());
            txtCmPwShow.setText(txtConformPw.getText());

            txtNewPw.setVisible(false);
            txtConformPw.setVisible(false);

            txtPwShow.setVisible(true);
            txtCmPwShow.setVisible(true);
            return;
        }
        txtNewPw.setText(txtPwShow.getText());
        txtConformPw.setText(txtCmPwShow.getText());

        txtNewPw.setVisible(true);
        txtConformPw.setVisible(true);

        txtPwShow.setVisible(false);
        txtCmPwShow.setVisible(false);
    }

    public void slipToName(ActionEvent actionEvent) {
        setPatten();
        if (!userIdMatcher.matches()) {
            txtUserId.setFocusColor(Paint.valueOf("Red"));
        } else {
            txtName.requestFocus();
        }
    }

    public void slipToEmail(ActionEvent actionEvent) {
        setPatten();
        if (userNameMatcher.matches()) {
            txtEmail.requestFocus();
        } else {
            txtName.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToPw(ActionEvent actionEvent) {
        setPatten();
        if (emailMatcher.matches()) {
            txtNewPw.requestFocus();
        } else {
            txtEmail.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToPwConform(ActionEvent actionEvent) {
        setPatten();
        if (pwMatcher.matches()) {
            txtConformPw.requestFocus();
        } else {
            txtNewPw.setFocusColor(Paint.valueOf("Red"));
            txtPwShow.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void okOnAction(ActionEvent actionEvent) throws IOException {
        resetOnAction(actionEvent);
    }

    private void setPatten() {

        Pattern userIdPattern = Pattern.compile("^(U0)([0-9]{1,})([1-9]{0,})$");
        userIdMatcher = userIdPattern.matcher(txtUserId.getText());

        Pattern userNamePatten = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePatten.matcher(txtName.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtEmail.getText());

        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        pwMatcher = passwordPattern.matcher(txtNewPw.getText());
    }

    public void duplicateSlipToCmPw(ActionEvent actionEvent) {
        setPatten();
        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        pwMatcher = passwordPattern.matcher(txtPwShow.getText());
        if (pwMatcher.matches()) {
            txtCmPwShow.requestFocus();
        } else {
            txtNewPw.setFocusColor(Paint.valueOf("Red"));
            txtPwShow.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void duplicateOnAction(ActionEvent actionEvent) {
        if (txtNewPw.getText().equals(txtConformPw.getText())) {
            try {
                //Refactor
                boolean isUpdate = fBO.resetPassword(new UserDTO(txtUserId.getText(), txtName.getText(),
                        txtNewPw.getText(), txtEmail.getText()));
                if (isUpdate) {
                    Navigation.navigate(Routes.FORGOT, pane);
                    new Alert(Alert.AlertType.CONFIRMATION, "Update success.!").show();
                } else {
                    clearText();
                    new Alert(Alert.AlertType.ERROR, "something wrong.!").show();
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }
        } else {
            lblShowError.setText("Password didnt match");
            txtConformPw.setFocusColor(Paint.valueOf("Red"));
            new Alert(Alert.AlertType.ERROR, "Password didnt match").show();
        }
    }
}
