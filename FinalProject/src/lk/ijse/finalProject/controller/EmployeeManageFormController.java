package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.model.EmployeeModel;
import lk.ijse.finalProject.to.Employee;

import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeManageFormController {
    public AnchorPane pane;
    public Label txtDate;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtContact;

    public void initialize(){
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));
    }

    public void addOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String nic = txtNic.getText();

        Employee employee = new Employee(id,name,address,contact,nic);

        try {
            boolean isAdded = EmployeeModel.addEmployee(employee);
            if (isAdded){
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something Wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            Employee employee = EmployeeModel.searchEmployee(id);
            txtName.setText(employee.getName());
            txtAddress.setText(employee.getAddress());
            txtContact.setText(employee.getContact());
            txtNic.setText(employee.getNic());

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String nic = txtNic.getText();

        Employee employee = new Employee(id,name,address,contact,nic);
        try {
            boolean isUpdate = EmployeeModel.updateEmployee(employee);
            if (isUpdate){
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION,"Update success").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something Wrong.!").show();
            }
        } catch (Exception exception) {
            System.out.println(employee);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDelete = EmployeeModel.deleteEmployee(txtId.getText());
            if(isDelete){
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION,"Delete success").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something Wrong.!").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void clearText(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtNic.clear();
    }

    public void slipToName(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void slipToAddress(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void slipToContact(ActionEvent actionEvent) {
        txtContact.requestFocus();
    }

    public void slipToNic(ActionEvent actionEvent) {
        txtNic.requestFocus();
    }
}
