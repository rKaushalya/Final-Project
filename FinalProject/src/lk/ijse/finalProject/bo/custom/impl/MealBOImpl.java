package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.MealBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.MealDAO;
import lk.ijse.finalProject.dto.MealDTO;
import lk.ijse.finalProject.entity.MealEntity;

import java.sql.SQLException;

public class MealBOImpl implements MealBO {
    //apply DI and Loos Coupling
    //Factory Design Pattern
    MealDAO mealDAO = (MealDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MEAL);
    @Override
    public boolean addMeal(MealDTO mealDTO) throws SQLException, ClassNotFoundException {
        return mealDAO.add(new MealEntity(mealDTO.getId(),mealDTO.getName(),mealDTO.getAvailableTime(),mealDTO.getPrice()));
    }
    @Override
    public MealDTO searchMeal(String id) throws SQLException, ClassNotFoundException {
        MealEntity mealEntity = mealDAO.search(id);
        return new MealDTO(mealEntity.getmId(),mealEntity.getName(),mealEntity.getAvailableTime(),mealEntity.getPrice());
    }
    @Override
    public boolean updateMeal(MealDTO mealDTO) throws SQLException, ClassNotFoundException {
       return mealDAO.update(new MealEntity(mealDTO.getId(),mealDTO.getName(), mealDTO.getAvailableTime(), mealDTO.getPrice()));
    }
    @Override
    public boolean deleteMeal(String id) throws SQLException, ClassNotFoundException {
        return mealDAO.delete(id);
    }
}
