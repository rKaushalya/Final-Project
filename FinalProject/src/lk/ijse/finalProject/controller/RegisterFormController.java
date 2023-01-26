package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.bo.custom.RegisterBO;
import lk.ijse.finalProject.bo.custom.impl.RegisterBOImpl;
import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.utill.Navigation;
import lk.ijse.finalProject.utill.Routes;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFormController {
    public AnchorPane pane;
    public JFXTextField txtUserId;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtPwShow;
    public JFXPasswordField txtPassword;
    public JFXCheckBox cbxShow;
    public JFXComboBox cmbRole;

    private Matcher userNameMatcher;
    private Matcher emailMatcher;
    private Matcher pwMatcher;
    private Matcher userIdMatcher;

    private final RegisterBO rBO = (RegisterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTER);

    public void initialize(){
        txtPwShow.setVisible(false);
        loadRole();
    }

    private void loadRole(){
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Admin","Reception");
        cmbRole.setItems(list);
    }

    public void registerOnAction(ActionEvent actionEvent) throws IOException {
        try {
            //Refactor
            boolean isRegister = rBO.registerUser(new UserDTO(txtUserId.getText(), txtName.getText(), txtPassword.getText(),
                    txtEmail.getText(), String.valueOf(cmbRole.getValue())));
            if (isRegister){
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION,"added Success.!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"something wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void loadUserFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER,pane);
    }

    private void clearText(){
        txtUserId.clear();
        txtName.clear();
        txtPassword.clear();
        txtEmail.clear();
        txtPwShow.clear();
    }

    public void cbxPasswordOnAction(ActionEvent actionEvent) {
        if (cbxShow.isSelected()){
            txtPwShow.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            txtPwShow.setVisible(true);
            return;
        }
        txtPassword.setText(txtPwShow.getText());
        txtPassword.setVisible(true);
        txtPwShow.setVisible(false);
    }

    public void slipToName(ActionEvent actionEvent) {
        setPatten();
        if (!userIdMatcher.matches()) {
            txtUserId.setFocusColor(Paint.valueOf("Red"));
        }else{
            txtName.requestFocus();
        }
    }

    public void slipToPw(ActionEvent actionEvent) {
        setPatten();
        if (userNameMatcher.matches()) {
            txtPassword.requestFocus();
        }else {
            txtName.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToRole(ActionEvent actionEvent) {
        setPatten();
        if (emailMatcher.matches()) {
            cmbRole.requestFocus();
        }else{
            txtEmail.setFocusColor(Paint.valueOf("Red"));
        }
    }

    public void slipToEmail(ActionEvent actionEvent) {
        setPatten();
        if (pwMatcher.matches()) {
            txtEmail.requestFocus();
        }else{
            txtPassword.setFocusColor(Paint.valueOf("Red"));
        }
    }

    private void setPatten(){

        Pattern userIdPattern = Pattern.compile("^(U0)([0-9]{1,})([1-9]{0,})$");
        userIdMatcher = userIdPattern.matcher(txtUserId.getText());

        Pattern userNamePatten = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePatten.matcher(txtName.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtEmail.getText());

        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        pwMatcher = passwordPattern.matcher(txtPassword.getText());
    }

    public void duplicatePassword(ActionEvent actionEvent) {
        setPatten();
        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        pwMatcher = passwordPattern.matcher(txtPwShow.getText());
        if (pwMatcher.matches()) {
            txtEmail.requestFocus();
        }else{
            txtPwShow.setFocusColor(Paint.valueOf("Red"));
        }
    }
}
