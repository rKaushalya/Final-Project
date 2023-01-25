package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.bo.custom.impl.MealBOImpl;
import lk.ijse.finalProject.dto.MealDTO;

import java.time.LocalDate;

public class MeailManageController {
    public AnchorPane pane;
    public Label txtDate;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtTime;
    public JFXTextField txtPrice;

    public void initialize(){
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));
    }

    public void addOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String availableTime = txtTime.getText();
        Double price = Double.valueOf(txtPrice.getText());

        try {
//            boolean isAdded = MealBOImpl.addMeal(mealDTO);
            MealBOImpl mealBOImpl = new MealBOImpl();
            boolean isAdded = mealBOImpl.addMeal(new MealDTO(id, name, availableTime, price));
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Added Success").show();
                clearText();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            MealDTO mealDTO = MealBOImpl.searchMeal(txtId.getText());
            txtId.setText(mealDTO.getId());
            txtName.setText(mealDTO.getName());
            txtTime.setText(mealDTO.getAvailableTime());
            txtPrice.setText(String.valueOf(mealDTO.getPrice()));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String availableTime = txtTime.getText();
        Double price = Double.valueOf(txtPrice.getText());

        MealDTO mealDTO = new MealDTO(id,name,availableTime,price);
        try {
            boolean isUpdate = MealBOImpl.updateMeal(mealDTO);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Success").show();
                clearText();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wrong").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDelete = MealBOImpl.deleteMeal(txtId.getText());
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Success").show();
                clearText();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something Wrong.!").show();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void clearText(){
        txtId.clear();
        txtName.clear();
        txtTime.clear();
        txtPrice.clear();
    }

    public void slipToName(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void slipToTime(ActionEvent actionEvent) {
        txtTime.requestFocus();
    }

    public void slipToPrice(ActionEvent actionEvent) {
        txtPrice.requestFocus();
    }
}
