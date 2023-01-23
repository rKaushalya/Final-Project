package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.to.Meal;
import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealModel {
    public static boolean addMeal(Meal meal) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO meal VALUES (?,?,?,?)";
        return CrudUtill.execute(sql,meal.getId(),meal.getName(),meal.getAvailableTime(),meal.getPrice());
    }

    public static Meal searchMeal(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM meal WHERE mId=?";
        ResultSet execute = CrudUtill.execute(sql, id);

        if (execute.next()){
            return new Meal(
                execute.getString(1),
                execute.getString(2),
                execute.getString(3),
                execute.getDouble(4)
            );
        }
        return null;
    }

    public static boolean updateMeal(Meal meal) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE meal SET name=?, availableTime=?,price=? WHERE mId=?";
        return CrudUtill.execute(sql,meal.getName(),meal.getAvailableTime(),meal.getPrice(),meal.getId());
    }

    public static boolean deleteMeal(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM meal WHERE mId=?";
        return CrudUtill.execute(sql,id);
    }

    public static ObservableList<Meal> searchAllMeal() throws SQLException, ClassNotFoundException {
        ObservableList<Meal> tmlist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM meal";
        ResultSet execute = CrudUtill.execute(sql);
        while (execute.next()){
            Meal meal = new Meal(execute.getString(1),execute.getString(2),execute.getString(3),execute.getDouble(4));
            tmlist.add(meal);
        }
        return tmlist;
    }
}
