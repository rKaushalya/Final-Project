package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.entity.RoomDetailEntity;

import java.sql.SQLException;

public interface RoomDetailDAO extends SuperDAO {

    boolean addRoomDetails(RoomDetailEntity roomDetailEntity) throws SQLException, ClassNotFoundException;
}
