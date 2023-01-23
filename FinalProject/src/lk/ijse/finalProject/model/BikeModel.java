package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.to.Bike;
import lk.ijse.finalProject.to.Customer;
import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BikeModel {
    public static boolean addBike(Bike bike) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO rentbike VALUES (?,?,?,?)";
        return CrudUtill.execute(sql,bike.getRegNo(),bike.getModel(),bike.getAvailability(),bike.getPricePerDay());
    }

    public static boolean deleteBike(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM rentbike WHERE regNo=?";
        return CrudUtill.execute(sql,id);
    }

    public static ArrayList<String> loadBikeId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT regNo FROM rentbike";
        ResultSet execute = CrudUtill.execute(sql);
        ArrayList<String> addRegNo = new ArrayList<>();

        while (execute.next()){
            addRegNo.add(execute.getString(1));
        }
        return addRegNo;
    }

    public static Bike searchBike(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM rentbike WHERE regNo=?";
        ResultSet execute = CrudUtill.execute(sql, id);

        if (execute.next()){
            return new Bike(
              execute.getString(1),
              execute.getString(2),
              execute.getString(3),
              execute.getDouble(4)
            );
        }
        return null;
    }

    public static Bike searchBikeTbl(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM rentbike WHERE regNo=?";
        ResultSet execute = CrudUtill.execute(sql, id);
        if (execute.next()){
           return new Bike(
                execute.getString(1),
                execute.getString(2),
                execute.getString(3),
                execute.getDouble(4)
           );
        }
        return null;
    }

    public static boolean updateBike(Bike bike) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE rentbike SET model=?,availability=?,pricePerDay=? WHERE regNo=?";
        return CrudUtill.execute(sql,bike.getModel(),bike.getAvailability(),bike.getPricePerDay(),bike.getRegNo());
    }

    public static int bikeCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM rentbike WHERE availability ='YES' || 'yes'";
        ResultSet execute = CrudUtill.execute(sql);
        int cusCount = 0;
        if (execute.next()){
            cusCount = execute.getInt(1);
        }
        return cusCount;
    }

    public static boolean addValueRentBikeDetail(String id,String regNo) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO rentbikedetail VALUES (?,?)";
        return CrudUtill.execute(sql,regNo,id);
    }

    public static boolean updateBikeAvailability(String regNo) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE rentbike SET availability = 'no' WHERE regNo = ?";
        return CrudUtill.execute(sql,regNo);
    }

    public static boolean rentBike(Customer customer,String regNo) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean isAdded = CusromerModel.addCustomer(customer);
            if (isAdded) {
                boolean isBikeDetailAdded = addBikeDetails(customer.getId(), regNo);
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
        return CrudUtill.execute(sql,regNo,cusId);
    }
}
