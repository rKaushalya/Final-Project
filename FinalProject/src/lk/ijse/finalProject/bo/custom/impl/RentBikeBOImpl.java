package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.RentBikeBO;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.BikeDTO;
import lk.ijse.finalProject.dto.CustomerDTO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentBikeBOImpl implements RentBikeBO {
    public static boolean addBike(BikeDTO bikeDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO rentbike VALUES (?,?,?,?)";
        return CrudUtil.execute(sql, bikeDTO.getRegNo(), bikeDTO.getModel(), bikeDTO.getAvailability(), bikeDTO.getPricePerDay());
    }

    public static boolean deleteBike(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM rentbike WHERE regNo=?";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<String> loadBikeId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT regNo FROM rentbike";
        ResultSet execute = CrudUtil.execute(sql);
        ArrayList<String> addRegNo = new ArrayList<>();

        while (execute.next()){
            addRegNo.add(execute.getString(1));
        }
        return addRegNo;
    }

    public static BikeDTO searchBike(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM rentbike WHERE regNo=?";
        ResultSet execute = CrudUtil.execute(sql, id);

        if (execute.next()){
            return new BikeDTO(
              execute.getString(1),
              execute.getString(2),
              execute.getString(3),
              execute.getDouble(4)
            );
        }
        return null;
    }

    public static BikeDTO searchBikeTbl(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM rentbike WHERE regNo=?";
        ResultSet execute = CrudUtil.execute(sql, id);
        if (execute.next()){
           return new BikeDTO(
                execute.getString(1),
                execute.getString(2),
                execute.getString(3),
                execute.getDouble(4)
           );
        }
        return null;
    }

    public static boolean updateBike(BikeDTO bikeDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE rentbike SET bo=?,availability=?,pricePerDay=? WHERE regNo=?";
        return CrudUtil.execute(sql, bikeDTO.getModel(), bikeDTO.getAvailability(), bikeDTO.getPricePerDay(), bikeDTO.getRegNo());
    }


    public static boolean addValueRentBikeDetail(String id,String regNo) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO rentbikedetail VALUES (?,?)";
        return CrudUtil.execute(sql,regNo,id);
    }

    public static boolean updateBikeAvailability(String regNo) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE rentbike SET availability = 'no' WHERE regNo = ?";
        return CrudUtil.execute(sql,regNo);
    }

    public static boolean rentBike(CustomerDTO customerDTO, String regNo) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean isAdded = CustomerBOImpl.addCustomer(customerDTO);
            if (isAdded) {
                boolean isBikeDetailAdded = addBikeDetails(customerDTO.getId(), regNo);
                if (isBikeDetailAdded) {
                    boolean isAvailabilityChange = updateBikeAvailability(regNo);
                    if (isAvailabilityChange) {
                        DBConnection.getDbConnection().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getDbConnection().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }

    private static boolean addBikeDetails(String cusId,String regNo) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO rentbikedetail VALUES (?,?)";
        return CrudUtil.execute(sql,regNo,cusId);
    }
}
