package lk.ijse.finalProject.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.custom.RoomBo;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dao.custom.RoomDAO;
import lk.ijse.finalProject.dto.RoomDTO;
import lk.ijse.finalProject.entity.RoomEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBo {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    public boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.add(new RoomEntity(roomDTO.getId(), roomDTO.getType(), roomDTO.getAc(), roomDTO.getPrice(),
                roomDTO.getAvailability()));
    }

    public RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException {
        RoomEntity search = roomDAO.search(id);
        return new RoomDTO(search.getrId(), search.getType(), search.getAcNonAc(), search.getPrice(), search.getAvailability());
    }

    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(id);
    }

    public boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.update(new RoomEntity(roomDTO.getId(), roomDTO.getType(), roomDTO.getAc(), roomDTO.getPrice(),
                roomDTO.getAvailability()));
    }

    public ObservableList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        ObservableList<RoomDTO> tmList = FXCollections.observableArrayList();
        ObservableList<RoomEntity> allRooms = roomDAO.getAllRooms();
        for (RoomEntity allRoom : allRooms) {
            tmList.add(new RoomDTO(allRoom.getrId(),allRoom.getType(),allRoom.getAcNonAc(),allRoom.getPrice(),allRoom.getAvailability()));
        }
        return tmList;
    }

    //remove soon
    public static ArrayList<String> loadRoomId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT rId FROM room WHERE availability='YES' || 'yes'";
        ResultSet execute = CrudUtil.execute(sql);
        ArrayList<String> addRoom = new ArrayList<>();

        while (execute.next()) {
            addRoom.add(execute.getString(1));
        }
        return addRoom;
    }

    public static int loadRoomCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM room WHERE availability='YES' || 'yes'";
        ResultSet execute = CrudUtil.execute(sql);
        int roomCount = 0;
        if (execute.next()) {
            roomCount = execute.getInt(1);
        }
        return roomCount;
    }

    public static int loadNotAvailableRoomCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM room WHERE availability='No' || 'no'";
        ResultSet execute = CrudUtil.execute(sql);
        int roomCount = 0;
        if (execute.next()) {
            roomCount = execute.getInt(1);
        }
        return roomCount;
    }

    public static boolean updateRoomAvailability(String rId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE room SET availability = 'no' WHERE rId = ?";
        return CrudUtil.execute(sql, rId);
    }
}