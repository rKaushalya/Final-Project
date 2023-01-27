package lk.ijse.finalProject.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.custom.RoomBo;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.RoomDAO;
import lk.ijse.finalProject.dto.RoomDTO;
import lk.ijse.finalProject.entity.RoomEntity;

import java.sql.SQLException;

public class RoomBOImpl implements RoomBo {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.add(new RoomEntity(roomDTO.getId(), roomDTO.getType(), roomDTO.getAc(), roomDTO.getPrice(),
                roomDTO.getAvailability()));
    }

    @Override
    public RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException {
        RoomEntity search = roomDAO.search(id);
        return new RoomDTO(search.getrId(), search.getType(), search.getAcNonAc(), search.getPrice(), search.getAvailability());
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(id);
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.update(new RoomEntity(roomDTO.getId(), roomDTO.getType(), roomDTO.getAc(), roomDTO.getPrice(),
                roomDTO.getAvailability()));
    }

    @Override
    public ObservableList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        ObservableList<RoomDTO> tmList = FXCollections.observableArrayList();
        ObservableList<RoomEntity> allRooms = roomDAO.getAllRooms();
        for (RoomEntity allRoom : allRooms) {
            tmList.add(new RoomDTO(allRoom.getrId(), allRoom.getType(), allRoom.getAcNonAc(), allRoom.getPrice(), allRoom.getAvailability()));
        }
        return tmList;
    }

}