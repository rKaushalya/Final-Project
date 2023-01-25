package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dao.custom.MealDAO;
import lk.ijse.finalProject.dto.MealDTO;
import lk.ijse.finalProject.entity.MealEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealModel {
    //apply DI and Loos Coupling
    //Factory Design Pattern
    MealDAO mealDAO = (MealDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MEAL);

    public boolean addMeal(MealDTO mealDTO) throws SQLException, ClassNotFoundException {
        return mealDAO.add(new MealEntity(mealDTO.getId(),mealDTO.getName(),mealDTO.getAvailableTime(),mealDTO.getPrice()));
    }

    public static MealDTO searchMeal(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM meal WHERE mId=?";
        ResultSet execute = CrudUtil.execute(sql, id);

        if (execute.next()){
            return new MealDTO(
                execute.getString(1),
                execute.getString(2),
                execute.getString(3),
                execute.getDouble(4)
            );
        }
        return null;
    }

    public static boolean updateMeal(MealDTO mealDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE meal SET name=?, availableTime=?,price=? WHERE mId=?";
        return CrudUtil.execute(sql, mealDTO.getName(), mealDTO.getAvailableTime(), mealDTO.getPrice(), mealDTO.getId());
    }

    public static boolean deleteMeal(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM meal WHERE mId=?";
        return CrudUtil.execute(sql,id);
    }

    public static ObservableList<MealDTO> searchAllMeal() throws SQLException, ClassNotFoundException {
        ObservableList<MealDTO> tmlist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM meal";
        ResultSet execute = CrudUtil.execute(sql);
        while (execute.next()){
            MealDTO mealDTO = new MealDTO(execute.getString(1),execute.getString(2),execute.getString(3),execute.getDouble(4));
            tmlist.add(mealDTO);
        }
        return tmlist;
    }
}
