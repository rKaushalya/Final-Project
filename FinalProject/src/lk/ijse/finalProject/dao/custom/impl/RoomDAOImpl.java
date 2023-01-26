package lk.ijse.finalProject.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.custom.RoomDAO;
import lk.ijse.finalProject.dto.RoomDTO;
import lk.ijse.finalProject.entity.RoomEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean add(RoomEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO room VALUES (?,?,?,?,?)", entity.getrId(), entity.getType(),
                entity.getAcNonAc(), entity.getPrice(), entity.getAvailability());
    }

    @Override
    public boolean update(RoomEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE room SET type=?,acNonAc=?,price=?,availability=? WHERE rId=?",
                entity.getType(), entity.getAcNonAc(), entity.getPrice(), entity.getAvailability(), entity.getrId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM room WHERE rId=?", id);
    }

    @Override
    public RoomEntity search(String id) throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT * FROM room WHERE rId=?", id);

        if (execute.next()){
            return new RoomEntity(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getDouble(4),
                    execute.getString(5)
            );
        }
        return null;
    }

    @Override
    public ObservableList<RoomEntity> getAllRooms() throws SQLException, ClassNotFoundException {
        ObservableList<RoomEntity> tmlist = FXCollections.observableArrayList();
        ResultSet execute = CrudUtil.execute("SELECT * FROM room");
        while (execute.next()) {
            RoomEntity roomEntity = new RoomEntity(execute.getString(1), execute.getString(2),
                    execute.getString(3), execute.getDouble(4), execute.getString(5));
            tmlist.add(roomEntity);
        }
        return tmlist;
    }

    @Override
    public int loadRoomCount() throws SQLException, ClassNotFoundException {
            ResultSet execute = CrudUtil.execute("SELECT COUNT(*) FROM room WHERE availability='YES' || 'yes'");
            int roomCount = 0;
            if (execute.next()) {
                roomCount = execute.getInt(1);
            }
            return roomCount;
    }

    @Override
    public int loadNotAvailableRoomCount() throws SQLException, ClassNotFoundException {
            ResultSet execute = CrudUtil.execute("SELECT COUNT(*) FROM room WHERE availability='No' || 'no'");
            int roomCount = 0;
            if (execute.next()) {
                roomCount = execute.getInt(1);
            }
            return roomCount;
    }
}
