package lk.ijse.finalProject.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.RoomEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RoomDAO extends CrudDAO<RoomEntity,String> {

    ObservableList<RoomEntity> getAllRooms() throws SQLException, ClassNotFoundException;

    int loadRoomCount() throws SQLException, ClassNotFoundException ;

    int loadNotAvailableRoomCount() throws SQLException, ClassNotFoundException ;
}
