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
import lk.ijse.finalProject.model.RoomModel;
import lk.ijse.finalProject.dto.RoomDTO;

import java.time.LocalDate;

public class RoomManageFormController {
    public AnchorPane pane;
    public Label txtDate;
    public JFXTextField txtType;
    public JFXTextField txtAc;
    public JFXTextField txtPrice;
    public JFXComboBox cmbRoomId;
    public JFXTextField txtRId;
    public JFXTextField txtAvailability;
    public TableView tblTable;
    public TableColumn clmType;
    public TableColumn clmAc;
    public TableColumn clmPrice;
    public TableColumn clmAvailability;
    public TableColumn clmRoomId;

    public void initialize(){
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));

        setCellValueFactory();
        searchAllRoom();
    }

    public void addOnAction(ActionEvent actionEvent) {
            String id = txtRId.getText();
            String type = txtType.getText();
            String ac = txtAc.getText();
            Double price = Double.valueOf(txtPrice.getText());
            String availability = txtAvailability.getText();

        RoomDTO roomDTO = new RoomDTO(id,type,ac,price,availability);
        try {
            boolean isAdded = RoomModel.addRoom(roomDTO);
            if (isAdded){
                searchAllRoom();
                setCellValueFactory();
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION,"Added Success").show();
                tblTable.refresh();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something Wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            RoomDTO roomDTO = RoomModel.searchRoom(txtRId.getText());
            txtRId.setText(roomDTO.getId());
            txtType.setText(roomDTO.getType());
            txtAc.setText(roomDTO.getAc());
            txtPrice.setText(String.valueOf(roomDTO.getPrice()));
            txtAvailability.setText(roomDTO.getAvailability());

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtRId.getText();
        String type = txtType.getText();
        String ac = txtAc.getText();
        Double price = Double.valueOf(txtPrice.getText());
        String availability = txtAvailability.getText();

        RoomDTO roomDTO = new RoomDTO(id,type,ac,price,availability);
        try {
            boolean isUpdate = RoomModel.updateRoom(roomDTO);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Success.!").show();
                searchAllRoom();
                setCellValueFactory();
                clearText();
                tblTable.refresh();
            }else {
                new Alert(Alert.AlertType.ERROR,"Somthing Wrong.!").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDelete = RoomModel.deleteRoom(txtRId.getText());
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Success").show();
                searchAllRoom();
                setCellValueFactory();
                clearText();
                tblTable.refresh();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void clearText(){
        txtRId.clear();
        txtType.clear();
        txtAc.clear();
        txtPrice.clear();
        txtAvailability.clear();
    }

    public void searchAllRoom(){
        try {
            ObservableList<RoomDTO> roomDTOS = RoomModel.searchAvailableRoom();
            tblTable.setItems(roomDTOS);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        clmType.setCellValueFactory(new PropertyValueFactory("type"));
        clmAc.setCellValueFactory(new PropertyValueFactory("ac"));
        clmPrice.setCellValueFactory(new PropertyValueFactory("price"));
        clmAvailability.setCellValueFactory(new PropertyValueFactory("availability"));
        clmRoomId.setCellValueFactory(new PropertyValueFactory("id"));
    }

    public void slipToAc(ActionEvent actionEvent) {
        txtAc.requestFocus();
    }

    public void slipToPrice(ActionEvent actionEvent) {
        txtPrice.requestFocus();
    }

    public void slipToAvailability(ActionEvent actionEvent) {
        txtAvailability.requestFocus();
    }

    public void slipToType(ActionEvent actionEvent) {
        txtType.requestFocus();
    }
}
