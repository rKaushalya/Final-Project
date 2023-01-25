package lk.ijse.finalProject.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.custom.MealBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.MealDAO;
import lk.ijse.finalProject.dto.MealDTO;
import lk.ijse.finalProject.entity.MealEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealBOImpl implements MealBO {
    //apply DI and Loos Coupling
    //Factory Design Pattern
    MealDAO mealDAO = (MealDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MEAL);

    public boolean addMeal(MealDTO mealDTO) throws SQLException, ClassNotFoundException {
        return mealDAO.add(new MealEntity(mealDTO.getId(),mealDTO.getName(),mealDTO.getAvailableTime(),mealDTO.getPrice()));
    }

    public MealDTO searchMeal(String id) throws SQLException, ClassNotFoundException {
        MealEntity mealEntity = mealDAO.search(id);
        return new MealDTO(mealEntity.getmId(),mealEntity.getName(),mealEntity.getAvailableTime(),mealEntity.getPrice());
    }

    public boolean updateMeal(MealDTO mealDTO) throws SQLException, ClassNotFoundException {
       return mealDAO.update(new MealEntity(mealDTO.getId(),mealDTO.getName(), mealDTO.getAvailableTime(), mealDTO.getPrice()));
    }

    public boolean deleteMeal(String id) throws SQLException, ClassNotFoundException {
        return mealDAO.delete(id);
    }

    //remove soon
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
