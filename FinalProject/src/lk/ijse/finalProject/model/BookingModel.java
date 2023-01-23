package lk.ijse.finalProject.model;

import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.to.Customer;
import lk.ijse.finalProject.to.OrderDetail;
import lk.ijse.finalProject.to.Packages;
import lk.ijse.finalProject.to.Room;
import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingModel {
    public static ArrayList<String> loadRegNo() throws SQLException, ClassNotFoundException {
        String sql = "SELECT regNo FROM rentbike WHERE availability='YES' || 'yes'";
        ResultSet execute = CrudUtill.execute(sql);
        ArrayList<String> regNo = new ArrayList<>();

        while (execute.next()){
            regNo.add(execute.getString(1));
        }
        return regNo;
    }

    public static ArrayList<String> loadPkgId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT pkgId FROM package";
        ResultSet execute = CrudUtill.execute(sql);
        ArrayList<String> pkgId = new ArrayList<>();

        while (execute.next()){
            pkgId.add(execute.getString(1));
        }
        return pkgId;
    }

    public static Packages searchPkg(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM package WHERE pkgId=?";
        ResultSet execute = CrudUtill.execute(sql, id);

        if (execute.next()){
           return new Packages(
             execute.getString(1),
             execute.getString(2),
             execute.getDouble(3),
             execute.getString(4)
           );
        }
        return null;
    }

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1";
        ResultSet execute = CrudUtill.execute(sql);

        if (execute.next()){
            return ganerateNextOrderId(execute.getString(1));
        }
        return ganerateNextOrderId(execute.getString(null));
    }

    private static String ganerateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >= 10){
                return "D0" + id;
            }
            return "D00" + id;
        }
        return "D001";
    }

    public static String generateNextCusId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cId FROM customer ORDER BY cId DESC LIMIT 1";
        ResultSet execute = CrudUtill.execute(sql);

        if (execute.next()){
            return generateNextCusId(execute.getString(1));
        }
        return generateNextCusId(execute.getString(null));
    }

    private static String generateNextCusId(String currentCusId) {
        if (currentCusId != null){
            String[] split = currentCusId.split("C0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >= 10){
                return "C0" + id;
            }
            return "C00" + id;
        }
        return "C001";
    }

    public static boolean placeOrder(Customer customer, OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean isAdded = CusromerModel.addCustomer(customer);
            if (isAdded) {
                boolean isOrderAdded = OrderModel.addOrder(orderDetail);
                if (isOrderAdded) {
                    boolean isPaymentDetailAdded = OrderModel.addPayment(orderDetail);
                    if (isPaymentDetailAdded) {
                        boolean isBikeDetailAdded = BikeModel.addValueRentBikeDetail(customer.getId(), orderDetail.getRegNo());
                        if (isBikeDetailAdded) {
                            boolean isOrderDetailAdded = OrderModel.addOrderDetails(customer.getId(), orderDetail.getOrderId());
                            if (isOrderDetailAdded) {
                                boolean isRoomUpdate = RoomModel.updateRoomAvailability(orderDetail.getrId());
                                if (isRoomUpdate) {
                                    boolean isUpdateBikeAvailability = BikeModel.updateBikeAvailability(orderDetail.getRegNo());
                                    if (isUpdateBikeAvailability) {
                                        DBConnection.getDbConnection().getConnection().commit();
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            DBConnection.getDbConnection().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }

    public static boolean rentRoom(Customer customer,OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean isCustomerAdded = CusromerModel.addCustomer(customer);
            if (isCustomerAdded) {
                boolean isRoomDetailAdded = OrderModel.addRoomDetails(orderDetail, customer.getId());
                if (isRoomDetailAdded) {
                    boolean isAvailabilityUpdated = RoomModel.updateRoomAvailability(orderDetail.getrId());
                    if (isAvailabilityUpdated) {
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
}
