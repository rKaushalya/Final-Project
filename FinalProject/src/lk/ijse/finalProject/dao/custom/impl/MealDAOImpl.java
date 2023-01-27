package lk.ijse.finalProject.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.custom.MealDAO;
import lk.ijse.finalProject.entity.MealEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealDAOImpl implements MealDAO {
    @Override
    public boolean add(MealEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO meal VALUES (?,?,?,?)", entity.getmId(), entity.getName(),
                entity.getAvailableTime(), entity.getPrice());
    }

    @Override
    public boolean update(MealEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE meal SET name=?, availableTime=?,price=? WHERE mId=?",
                entity.getName(), entity.getAvailableTime(), entity.getPrice(), entity.getmId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM meal WHERE mId=?", id);
    }

    @Override
    public MealEntity search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM meal WHERE mId=?";
        ResultSet execute = CrudUtil.execute(sql, id);

        if (execute.next()) {
            return new MealEntity(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public ObservableList<MealEntity> searchAllMeal() throws SQLException, ClassNotFoundException {
        ObservableList<MealEntity> tmlist = FXCollections.observableArrayList();
        ResultSet execute = CrudUtil.execute("SELECT * FROM meal");
        while (execute.next()) {
            MealEntity mealEntity = new MealEntity(execute.getString(1), execute.getString(2), execute.getString(3), execute.getDouble(4));
            tmlist.add(mealEntity);
        }
        return tmlist;
    }
}
