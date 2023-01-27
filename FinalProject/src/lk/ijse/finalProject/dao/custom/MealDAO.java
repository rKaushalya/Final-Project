package lk.ijse.finalProject.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.MealEntity;

import java.sql.SQLException;

public interface MealDAO extends CrudDAO<MealEntity, String> {

    ObservableList<MealEntity> searchAllMeal() throws SQLException, ClassNotFoundException;
}
