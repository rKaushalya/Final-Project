package lk.ijse.finalProject.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.RoomDTO;

import java.sql.SQLException;

public interface RoomBo extends SuperBO {

    boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException;

    RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException;

    boolean deleteRoom(String id) throws SQLException, ClassNotFoundException;

    boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException;

    ObservableList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException;
}
