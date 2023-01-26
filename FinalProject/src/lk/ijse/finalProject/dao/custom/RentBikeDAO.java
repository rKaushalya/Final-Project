package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.RentBikeEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RentBikeDAO extends CrudDAO<RentBikeEntity,String> {

    int bikeCount() throws SQLException, ClassNotFoundException ;

    boolean updateAvailability(String regNo) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadBikeId() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadRegNo() throws SQLException, ClassNotFoundException;
}
