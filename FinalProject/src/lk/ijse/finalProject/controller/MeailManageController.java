package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.model.MealModel;
import lk.ijse.finalProject.to.Meal;

import java.sql.SQLException;
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

        Meal meal = new Meal(id,name,availableTime,price);
        try {
            boolean isAdded = MealModel.addMeal(meal);
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
            Meal meal = MealModel.searchMeal(txtId.getText());
            txtId.setText(meal.getId());
            txtName.setText(meal.getName());
            txtTime.setText(meal.getAvailableTime());
            txtPrice.setText(String.valueOf(meal.getPrice()));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String availableTime = txtTime.getText();
        Double price = Double.valueOf(txtPrice.getText());

        Meal meal = new Meal(id,name,availableTime,price);
        try {
            boolean isUpdate = MealModel.updateMeal(meal);
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
            boolean isDelete = MealModel.deleteMeal(txtId.getText());
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
