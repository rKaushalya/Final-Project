package lk.ijse.finalProject.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.RoomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<RoomEntity, String> {

    ObservableList<RoomEntity> getAllRooms() throws SQLException, ClassNotFoundException;

    int loadRoomCount() throws SQLException, ClassNotFoundException;

    int loadNotAvailableRoomCount() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadRoomId() throws SQLException, ClassNotFoundException;

    boolean updateRoomAvailability(String rId) throws SQLException, ClassNotFoundException;
}
