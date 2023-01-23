package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.to.Room;
import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomModel {
    public static boolean addRoom(Room room) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO room VALUES (?,?,?,?,?)";
        return CrudUtill.execute(sql,room.getId(),room.getType(),room.getAc(),room.getPrice(),room.getAvailability());
    }

    public static Room searchRoom(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM room WHERE rId=?";
        ResultSet execute = CrudUtill.execute(sql, id);

        if (execute.next()){
            return new Room(
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
        return CrudUtill.execute(sql, id);
    }

    public static boolean updateRoom(Room room) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE room SET type=?,acNonAc=?,price=?,availability=? WHERE rId=?";
        return CrudUtill.execute(sql,room.getType(),room.getAc(),room.getPrice(),room.getAvailability(),room.getId());
    }

    public static ArrayList<String> loadRoomId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT rId FROM room WHERE availability='YES' || 'yes'";
        ResultSet execute = CrudUtill.execute(sql);
        ArrayList<String> addRoom = new ArrayList<>();

        while (execute.next()){
            addRoom.add(execute.getString(1));
        }
        return addRoom;
    }

    public static ObservableList<Room> searchAvailableRoom() throws SQLException, ClassNotFoundException {
        ObservableList<Room> tmlist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM room";
        ResultSet execute = CrudUtill.execute(sql);
        while (execute.next()){
            Room room = new Room(execute.getString(1),execute.getString(2),execute.getString(3),execute.getDouble(4),execute.getString(5));
            tmlist.add(room);
        }
        return tmlist;
    }

    public static int loadRoomCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM room WHERE availability='YES' || 'yes'";
        ResultSet execute = CrudUtill.execute(sql);
        int roomCount = 0;
        if (execute.next()){
            roomCount = execute.getInt(1);
        }
        return roomCount;
    }

    public static int loadNotAvailableRoomCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM room WHERE availability='No' || 'no'";
        ResultSet execute = CrudUtill.execute(sql);
        int roomCount = 0;
        if (execute.next()){
            roomCount = execute.getInt(1);
        }
        return roomCount;
    }

    public static boolean updateRoomAvailability(String rId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE room SET availability = 'no' WHERE rId = ?";
        return CrudUtill.execute(sql,rId);
    }
}
