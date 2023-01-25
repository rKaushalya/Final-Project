package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.bo.custom.EmployeeBO;
import lk.ijse.finalProject.dto.EmployeeDTO;
import lk.ijse.finalProject.bo.custom.impl.EmployeeBOImpl;

import java.time.LocalDate;

public class EmployeeManageFormController {
    public AnchorPane pane;
    public Label txtDate;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtContact;

    private final EmployeeBO empBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize() {
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));
    }

    public void addOnAction(ActionEvent actionEvent) {
        try {
           //Refactor
            boolean isAdded = empBO.addEmployee(new EmployeeDTO(txtId.getText(), txtName.getText(), txtAddress.getText(),
                    txtContact.getText(), txtNic.getText()));
            if (isAdded) {
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            //Refactor
            EmployeeDTO employeeDTO = empBO.searchEmployee(txtId.getText());
            txtName.setText(employeeDTO.getName());
            txtAddress.setText(employeeDTO.getAddress());
            txtContact.setText(employeeDTO.getContact());
            txtNic.setText(employeeDTO.getNic());

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try {
            //Refactor
            boolean isUpdate = empBO.updateEmployee(new EmployeeDTO(txtId.getText(), txtName.getText(),
                    txtAddress.getText(), txtContact.getText(), txtNic.getText()));
            if (isUpdate) {
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION, "Update success").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong.!").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            //Refactor
            boolean isDelete = empBO.deleteEmployee(txtId.getText());
            if (isDelete) {
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION, "Delete success").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong.!").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void clearText() {
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
