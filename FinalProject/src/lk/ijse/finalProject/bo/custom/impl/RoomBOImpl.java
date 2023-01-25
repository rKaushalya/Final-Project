package lk.ijse.finalProject.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.RoomDTO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl {
    public static boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO room VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql, roomDTO.getId(), roomDTO.getType(), roomDTO.getAc(), roomDTO.getPrice(), roomDTO.getAvailability());
    }

    public static RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM room WHERE rId=?";
        ResultSet execute = CrudUtil.execute(sql, id);

        if (execute.next()){
            return new RoomDTO(
              execute.getString(1),
              execute.getString(2),
              execute.getString(3),
              execute.getDouble(4),
              execute.getString(5)
            );
        }
        return null;
    }

    public static boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM room WHERE rId=?";
        return CrudUtil.execute(sql, id);
    }

    public static boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE room SET type=?,acNonAc=?,price=?,availability=? WHERE rId=?";
        return CrudUtil.execute(sql, roomDTO.getType(), roomDTO.getAc(), roomDTO.getPrice(), roomDTO.getAvailability(), roomDTO.getId());
    }

    public static ArrayList<String> loadRoomId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT rId FROM room WHERE availability='YES' || 'yes'";
        ResultSet execute = CrudUtil.execute(sql);
        ArrayList<String> addRoom = new ArrayList<>();

        while (execute.next()){
            addRoom.add(execute.getString(1));
        }
        return addRoom;
    }

    public static ObservableList<RoomDTO> searchAvailableRoom() throws SQLException, ClassNotFoundException {
        ObservableList<RoomDTO> tmlist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM room";
        ResultSet execute = CrudUtil.execute(sql);
        while (execute.next()){
            RoomDTO roomDTO = new RoomDTO(execute.getString(1),execute.getString(2),execute.getString(3),execute.getDouble(4),execute.getString(5));
            tmlist.add(roomDTO);
        }
        return tmlist;
    }

    public static int loadRoomCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM room WHERE availability='YES' || 'yes'";
        ResultSet execute = CrudUtil.execute(sql);
        int roomCount = 0;
        if (execute.next()){
            roomCount = execute.getInt(1);
        }
        return roomCount;
    }

    public static int loadNotAvailableRoomCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM room WHERE availability='No' || 'no'";
        ResultSet execute = CrudUtil.execute(sql);
        int roomCount = 0;
        if (execute.next()){
            roomCount = execute.getInt(1);
        }
        return roomCount;
    }

    public static boolean updateRoomAvailability(String rId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE room SET availability = 'no' WHERE rId = ?";
        return CrudUtil.execute(sql,rId);
    }
}
