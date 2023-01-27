package lk.ijse.finalProject.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.RoomDTO;
import lk.ijse.finalProject.view.tdm.RoomTDM;

import java.sql.SQLException;

public interface RoomBo extends SuperBO {

    boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException;

    RoomTDM searchRoom(String id) throws SQLException, ClassNotFoundException;

    boolean deleteRoom(String id) throws SQLException, ClassNotFoundException;

    boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException;

    ObservableList<RoomTDM> getAllRooms() throws SQLException, ClassNotFoundException;
}
