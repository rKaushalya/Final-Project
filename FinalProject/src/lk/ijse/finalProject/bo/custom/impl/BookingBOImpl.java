package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.BookingBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dao.custom.*;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.BikeDTO;
import lk.ijse.finalProject.dto.CustomerDTO;
import lk.ijse.finalProject.dto.OrderDetailDTO;
import lk.ijse.finalProject.dto.PackagesDTO;
import lk.ijse.finalProject.entity.*;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBOImpl implements BookingBO {

    private final OrdersDAO orderDAO = (OrdersDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    private final CustomerDAO cusDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    private final RentBikeDetailDAO rentBikeDetailDAO = (RentBikeDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RENTBIKEDETAIL);
    private final RentBikeDAO rentBikeDAO = (RentBikeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RENTBIKE);
    private final RoomDetailDAO roomDetailDAO = (RoomDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOMDETAIL);

    public static ArrayList<String> loadRegNo() throws SQLException, ClassNotFoundException {
        String sql = "SELECT regNo FROM rentbike WHERE availability='YES' || 'yes'";
        ResultSet execute = CrudUtil.execute(sql);
        ArrayList<String> regNo = new ArrayList<>();

        while (execute.next()) {
            regNo.add(execute.getString(1));
        }
        return regNo;
    }

    public static ArrayList<String> loadPkgId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT pkgId FROM package";
        ResultSet execute = CrudUtil.execute(sql);
        ArrayList<String> pkgId = new ArrayList<>();

        while (execute.next()) {
            pkgId.add(execute.getString(1));
        }
        return pkgId;
    }

    public static PackagesDTO searchPkg(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM package WHERE pkgId=?";
        ResultSet execute = CrudUtil.execute(sql, id);

        if (execute.next()) {
            return new PackagesDTO(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getDouble(3),
                    execute.getString(4)
            );
        }
        return null;
    }

    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        //Refactor
        ResultSet resultSet = orderDAO.generateNextOrderId();
        if (resultSet.next()) {
            return newOrderID(resultSet.getString(1));
        }
        return newOrderID(resultSet.getString(null));
    }

    public String newOrderID(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >= 10) {
                return "D0" + id;
            }
            return "D00" + id;
        }
        return "D001";
    }

    public String generateNextCusID() throws SQLException, ClassNotFoundException {
        //Refactor
        ResultSet resultSet = orderDAO.generateNextCusID();
        if (resultSet.next()) {
            return nextCusID(resultSet.getString(1));
        }
        return nextCusID(resultSet.getString(null));
    }

    public String nextCusID(String currentCusId) {
        if (currentCusId != null) {
            String[] split = currentCusId.split("C0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >= 10) {
                return "C0" + id;
            }
            return "C00" + id;
        }
        return "C001";
    }

    public boolean placeOrder(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            //Refactor
            boolean isAdded = cusDAO.add(new CustomerEntity(customerDTO.getId(),customerDTO.getName(),
                    customerDTO.getAddress(),customerDTO.getContact(),customerDTO.getEmail()));
            if (isAdded) {
                //Refactor
                boolean isOrderAdded = orderDAO.addOrder(new OrdersEntity(orderDetailDTO.getOrderId(),
                        orderDetailDTO.getDate(), orderDetailDTO.getRoomDayCount(), orderDetailDTO.getrId(),
                        orderDetailDTO.getPkgId(), orderDetailDTO.getRegNo(), orderDetailDTO.getBikeDayCount()));
                if (isOrderAdded) {
//                    boolean isPaymentDetailAdded = OrderBOImpl.addPayment(orderDetailDTO);
                    boolean isPaymentDetailAdded = paymentDAO.addPayment(new PaymentDetailEntity(orderDetailDTO.getOrderId(),
                            orderDetailDTO.getDate(),orderDetailDTO.getReceverdAmount(),orderDetailDTO.getBalance(),orderDetailDTO.getTotal()));
                    if (isPaymentDetailAdded) {
                        //Refactor
                        boolean isBikeDetailAdded = rentBikeDetailDAO.add(customerDTO.getId(), orderDetailDTO.getRegNo());
                        if (isBikeDetailAdded) {
                            //Refactor
                            boolean isOrderDetailAdded = orderDetailDAO.addOrderDetails(customerDTO.getId(), orderDetailDTO.getOrderId());
                            if (isOrderDetailAdded) {
                                boolean isRoomUpdate = RoomBOImpl.updateRoomAvailability(orderDetailDTO.getrId());
                                if (isRoomUpdate) {
                                    //Refactor
                                    boolean isUpdateBikeAvailability = rentBikeDAO.updateAvailability(orderDetailDTO.getRegNo());
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
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }

    public boolean rentRoom(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            //Refactor
            boolean isCustomerAdded = cusDAO.add(new CustomerEntity(customerDTO.getId(),customerDTO.getName(),
                    customerDTO.getAddress(),customerDTO.getContact(),customerDTO.getEmail()));
            if (isCustomerAdded) {
                //Refactor
                boolean isRoomDetailAdded = roomDetailDAO.addRoomDetails(new RoomDetailEntity(customerDTO.getId(), orderDetailDTO.getrId(),
                        orderDetailDTO.getDate(), orderDetailDTO.getRoomDayCount()));
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
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public BikeDTO searchAllBikes(String regNo) throws SQLException, ClassNotFoundException {
        RentBikeEntity search = rentBikeDAO.search(regNo);
        return new BikeDTO(search.getRegNo(),search.getModel(),search.getAvailability(),search.getPricePerDay());
    }
}
