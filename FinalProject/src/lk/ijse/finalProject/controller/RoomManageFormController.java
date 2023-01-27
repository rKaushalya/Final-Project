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
import lk.ijse.finalProject.bo.BOFactory;
import lk.ijse.finalProject.bo.custom.RoomBo;
import lk.ijse.finalProject.dto.RoomDTO;
import lk.ijse.finalProject.view.tdm.RoomTDM;

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

    private final RoomBo roomBo = (RoomBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    public void initialize() {
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));

        setCellValueFactory();
        searchAllRoom();
    }

    public void addOnAction(ActionEvent actionEvent) {
        try {
            //Refactor
            boolean isAdded = roomBo.addRoom(new RoomDTO(txtRId.getText(), txtType.getText(), txtAc.getText(),
                    Double.valueOf(txtPrice.getText()), txtAvailability.getText()));
            if (isAdded) {
                searchAllRoom();
                setCellValueFactory();
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION, "Added Success").show();
                tblTable.refresh();
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
            RoomTDM roomTDM = roomBo.searchRoom(txtRId.getText());
            txtRId.setText(roomTDM.getId());
            txtType.setText(roomTDM.getType());
            txtAc.setText(roomTDM.getAc());
            txtPrice.setText(String.valueOf(roomTDM.getPrice()));
            txtAvailability.setText(roomTDM.getAvailability());

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try {
            //Refactor
            boolean isUpdate = roomBo.updateRoom(new RoomDTO(txtRId.getText(), txtType.getText(), txtAc.getText(),
                    Double.valueOf(txtPrice.getText()), txtAvailability.getText()));
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update Success.!").show();
                searchAllRoom();
                setCellValueFactory();
                clearText();
                tblTable.refresh();
            } else {
                new Alert(Alert.AlertType.ERROR, "Somthing Wrong.!").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            //Refactor
            boolean isDelete = roomBo.deleteRoom(txtRId.getText());
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delete Success").show();
                searchAllRoom();
                setCellValueFactory();
                clearText();
                tblTable.refresh();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void clearText() {
        txtRId.clear();
        txtType.clear();
        txtAc.clear();
        txtPrice.clear();
        txtAvailability.clear();
    }

    public void searchAllRoom() {
        try {
            //Refactor
            ObservableList<RoomTDM> allRooms = roomBo.getAllRooms();
            tblTable.setItems(allRooms);
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
