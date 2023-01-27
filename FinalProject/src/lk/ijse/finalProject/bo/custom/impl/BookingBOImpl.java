package lk.ijse.finalProject.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.custom.BookingBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.*;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.*;
import lk.ijse.finalProject.entity.*;
import lk.ijse.finalProject.view.tdm.BikeTDM;
import lk.ijse.finalProject.view.tdm.MealTDM;
import lk.ijse.finalProject.view.tdm.PackageTDM;

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
    private final PackageDAO packageDAO = (PackageDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PACKAGE);
    private final MealDAO mealDAO = (MealDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MEAL);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public ArrayList<String> loadAllRegNo() throws SQLException, ClassNotFoundException {
        return rentBikeDAO.loadRegNo();
    }

    @Override
    public ArrayList<String> loadAllPackagesIDS() throws SQLException, ClassNotFoundException {
        return packageDAO.loadPkgId();
    }

    @Override
    public PackageTDM searchAllPackages(String id) throws SQLException, ClassNotFoundException {
        PackageEntity packageEntity = packageDAO.searchPkg(id);
        return new PackageTDM(packageEntity.getPkgId(), packageEntity.getPkgName(), packageEntity.getPrice(), packageEntity.getInclude());
    }

    @Override
    public ObservableList<MealTDM> GetAllMeals() throws SQLException, ClassNotFoundException {
        ObservableList<MealTDM> meals = FXCollections.observableArrayList();
        ObservableList<MealEntity> mealEntities = mealDAO.searchAllMeal();
        for (MealEntity mealEntity : mealEntities) {
            meals.add(new MealTDM(mealEntity.getmId(), mealEntity.getName(), mealEntity.getAvailableTime(), mealEntity.getPrice()));
        }
        return meals;
    }

    @Override
    public ArrayList<String> getAllRoomIDS() throws SQLException, ClassNotFoundException {
        return roomDAO.loadRoomId();
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        //Refactor
        ResultSet resultSet = orderDAO.generateNextOrderId();
        if (resultSet.next()) {
            return newOrderID(resultSet.getString(1));
        }
        return newOrderID(resultSet.getString(null));
    }

    @Override
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

    @Override
    public String generateNextCusID() throws SQLException, ClassNotFoundException {
        //Refactor
        ResultSet resultSet = orderDAO.generateNextCusID();
        if (resultSet.next()) {
            return nextCusID(resultSet.getString(1));
        }
        return nextCusID(resultSet.getString(null));
    }

    @Override
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

    @Override
    public boolean placeOrder(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            //Refactor
            boolean isAdded = cusDAO.add(new CustomerEntity(customerDTO.getId(), customerDTO.getName(),
                    customerDTO.getAddress(), customerDTO.getContact(), customerDTO.getEmail()));
            if (isAdded) {
                //Refactor
                boolean isOrderAdded = orderDAO.addOrder(new OrdersEntity(orderDetailDTO.getOrderId(),
                        orderDetailDTO.getDate(), orderDetailDTO.getRoomDayCount(), orderDetailDTO.getrId(),
                        orderDetailDTO.getPkgId(), orderDetailDTO.getRegNo(), orderDetailDTO.getBikeDayCount()));
                if (isOrderAdded) {
                    //Refactor
                    boolean isPaymentDetailAdded = paymentDAO.addPayment(new PaymentDetailEntity(orderDetailDTO.getOrderId(),
                            orderDetailDTO.getDate(), orderDetailDTO.getReceverdAmount(), orderDetailDTO.getBalance(), orderDetailDTO.getTotal()));
                    if (isPaymentDetailAdded) {
                        //Refactor
                        boolean isBikeDetailAdded = rentBikeDetailDAO.add(customerDTO.getId(), orderDetailDTO.getRegNo());
                        if (isBikeDetailAdded) {
                            //Refactor
                            boolean isOrderDetailAdded = orderDetailDAO.addOrderDetails(customerDTO.getId(), orderDetailDTO.getOrderId());
                            if (isOrderDetailAdded) {
                                //Refactor
                                boolean isRoomUpdate = roomDAO.updateRoomAvailability(orderDetailDTO.getrId());
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

    @Override
    public boolean rentRoom(CustomerDTO customerDTO, OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            //Refactor
            boolean isCustomerAdded = cusDAO.add(new CustomerEntity(customerDTO.getId(), customerDTO.getName(),
                    customerDTO.getAddress(), customerDTO.getContact(), customerDTO.getEmail()));
            if (isCustomerAdded) {
                //Refactor
                boolean isRoomDetailAdded = roomDetailDAO.addRoomDetails(new RoomDetailEntity(customerDTO.getId(), orderDetailDTO.getrId(),
                        orderDetailDTO.getDate(), orderDetailDTO.getRoomDayCount()));
                if (isRoomDetailAdded) {
                    //Refactor
                    boolean isAvailabilityUpdated = roomDAO.updateRoomAvailability(orderDetailDTO.getrId());
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
    public BikeTDM searchAllBikes(String regNo) throws SQLException, ClassNotFoundException {
        RentBikeEntity search = rentBikeDAO.search(regNo);
        return new BikeTDM(search.getRegNo(), search.getModel(), search.getAvailability(), search.getPricePerDay());
    }
}
