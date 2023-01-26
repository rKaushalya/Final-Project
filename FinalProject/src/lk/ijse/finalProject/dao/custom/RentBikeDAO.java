package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.RentBikeEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RentBikeDAO extends CrudDAO<RentBikeEntity,String> {

    int bikeCount() throws SQLException, ClassNotFoundException ;
}
