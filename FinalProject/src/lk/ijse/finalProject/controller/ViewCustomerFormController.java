package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.dto.CustomerDTO;
import lk.ijse.finalProject.model.CusromerModel;

import java.time.LocalDate;

public class ViewCustomerFormController {
    public AnchorPane pane;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public Label txtDate;
    public JFXComboBox cmbCusId;
    public TableView tblCusDetail;
    public TableColumn tblName;
    public TableColumn tblAddress;
    public TableColumn tblContact;
    public TableColumn tblEmail;
    public TableColumn clmCusId;

    public void initialize(){
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));

        setCellValueFactory();
        loadCusData();
    }

    public void searchOnAction(ActionEvent actionEvent){
        String id = txtId.getText();
        try {
            CustomerDTO customerDTO = CusromerModel.searchCustomer(id);
                txtId.setText(customerDTO.getId());
                txtName.setText(customerDTO.getName());
                txtAddress.setText(customerDTO.getAddress());
                txtContact.setText(customerDTO.getContact());
                txtEmail.setText(customerDTO.getEmail());
        } catch (Exception exception) {
            System.out.println(exception);
        } 
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(id);
            customerDTO.setAddress(address);
            customerDTO.setName(name);
            customerDTO.setContact(contact);
            customerDTO.setEmail(email);

        try {
            boolean isUpdated = CusromerModel.updateCustomer(customerDTO);
            if (isUpdated){
                loadCusData();
                setCellValueFactory();
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION,"Update Success.!").show();
                tblCusDetail.refresh();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            boolean isDelete = CusromerModel.deleteCustomer(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted.!").show();
                loadCusData();
                setCellValueFactory();
                clearText();
                tblCusDetail.refresh();
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
        txtEmail.clear();
    }

    public void loadCusData(){
        try {
            ObservableList<CustomerDTO> customerDTOS = CusromerModel.searchAllCustomer();
            tblCusDetail.setItems(customerDTOS);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private void setCellValueFactory() {
       tblName.setCellValueFactory(new PropertyValueFactory("name"));
       tblAddress.setCellValueFactory(new PropertyValueFactory("address"));
       tblContact.setCellValueFactory(new PropertyValueFactory("contact"));
       tblEmail.setCellValueFactory(new PropertyValueFactory("email"));
       clmCusId.setCellValueFactory(new PropertyValueFactory("id"));
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

    public void slipToEmail(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void searchOnEnter(ActionEvent actionEvent) {
        searchOnAction(actionEvent);
    }
}
