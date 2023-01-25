package lk.ijse.finalProject.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.RoomEntity;

import java.sql.SQLException;

public interface RoomDAO extends CrudDAO<RoomEntity,String> {

    ObservableList<RoomEntity> getAllRooms() throws SQLException, ClassNotFoundException;
}
