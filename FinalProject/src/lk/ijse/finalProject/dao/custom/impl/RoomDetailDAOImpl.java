package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.RoomDetailDAO;
import lk.ijse.finalProject.entity.RoomDetailEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.SQLException;

public class RoomDetailDAOImpl implements RoomDetailDAO {
    @Override
    public boolean addRoomDetails(RoomDetailEntity roomDetailEntity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO roomorderdetail VALUES (?,?,?,?)";
        return CrudUtil.execute(sql, roomDetailEntity.getrId(),roomDetailEntity.getcId(), roomDetailEntity.getDate(),
                roomDetailEntity.getDayCount());
    }
}
