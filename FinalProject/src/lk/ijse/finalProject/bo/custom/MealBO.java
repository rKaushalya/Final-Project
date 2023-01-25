package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.MealDTO;

import java.sql.SQLException;

public interface MealBO extends SuperBO {

    boolean addMeal(MealDTO mealDTO) throws SQLException, ClassNotFoundException;

    MealDTO searchMeal(String id) throws SQLException, ClassNotFoundException;

    boolean updateMeal(MealDTO mealDTO) throws SQLException, ClassNotFoundException;

    boolean deleteMeal(String id) throws SQLException, ClassNotFoundException;
}
