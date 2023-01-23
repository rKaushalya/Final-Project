package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.dto.EmployeeDTO;
import lk.ijse.finalProject.model.EmployeeModel;

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

        EmployeeDTO employeeDTO = new EmployeeDTO(id,name,address,contact,nic);

        try {
            boolean isAdded = EmployeeModel.addEmployee(employeeDTO);
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
            EmployeeDTO employeeDTO = EmployeeModel.searchEmployee(id);
            txtName.setText(employeeDTO.getName());
            txtAddress.setText(employeeDTO.getAddress());
            txtContact.setText(employeeDTO.getContact());
            txtNic.setText(employeeDTO.getNic());

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

        EmployeeDTO employeeDTO = new EmployeeDTO(id,name,address,contact,nic);
        try {
            boolean isUpdate = EmployeeModel.updateEmployee(employeeDTO);
            if (isUpdate){
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION,"Update success").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something Wrong.!").show();
            }
        } catch (Exception exception) {
            System.out.println(employeeDTO);
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
