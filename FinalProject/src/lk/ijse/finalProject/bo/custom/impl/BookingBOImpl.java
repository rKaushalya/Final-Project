package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.CustomerDTO;
import lk.ijse.finalProject.dto.OrderDetailDTO;
import lk.ijse.finalProject.dto.PackagesDTO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBOImpl {
    public static ArrayList<String> loadRegNo() throws SQLException, ClassNotFoundException {
        String sql = "SELECT regNo FROM rentbike WHERE availability='YES' || 'yes'";
        ResultSet execute = CrudUtil.execute(sql);
        ArrayList<String> regNo = new ArrayList<>();

        while (execute.next()){
            regNo.add(execute.getString(1));
        }
        return regNo;
    }

    public static ArrayList<String> loadPkgId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT pkgId FROM package";
        ResultSet execute = CrudUtil.execute(sql);
        ArrayList<String> pkgId = new ArrayList<>();

        while (execute.next()){
            pkgId.add(execute.getString(1));
        }
        return pkgId;
    }

    public static PackagesDTO searchPkg(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM package WHERE pkgId=?";
        ResultSet execute = CrudUtil.execute(sql, id);

        if (execute.next()){
           return new PackagesDTO(
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
        ResultSet execute = CrudUtil.execute(sql);

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
        ResultSet execute = CrudUtil.execute(sql);

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

    public static boolean placeOrder(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean isAdded = CustomerBOImpl.addCustomer(customerDTO);
            if (isAdded) {
                boolean isOrderAdded = OrderBOImpl.addOrder(orderDetailDTO);
                if (isOrderAdded) {
                    boolean isPaymentDetailAdded = OrderBOImpl.addPayment(orderDetailDTO);
                    if (isPaymentDetailAdded) {
                        boolean isBikeDetailAdded = RentBikeBOImpl.addValueRentBikeDetail(customerDTO.getId(), orderDetailDTO.getRegNo());
                        if (isBikeDetailAdded) {
                            boolean isOrderDetailAdded = OrderBOImpl.addOrderDetails(customerDTO.getId(), orderDetailDTO.getOrderId());
                            if (isOrderDetailAdded) {
                                boolean isRoomUpdate = RoomBOImpl.updateRoomAvailability(orderDetailDTO.getrId());
                                if (isRoomUpdate) {
                                    boolean isUpdateBikeAvailability = RentBikeBOImpl.updateBikeAvailability(orderDetailDTO.getRegNo());
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

    public static boolean rentRoom(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean isCustomerAdded = CustomerBOImpl.addCustomer(customerDTO);
            if (isCustomerAdded) {
                boolean isRoomDetailAdded = OrderBOImpl.addRoomDetails(orderDetailDTO, customerDTO.getId());
                if (isRoomDetailAdded) {
                    boolean isAvailabilityUpdated = RoomBOImpl.updateRoomAvailability(orderDetailDTO.getrId());
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
