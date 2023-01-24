package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDAOFactory(){
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        BIKE,CUSTOMER,EMPLOYEE,MEAL,ORDERDETAIL,ORDERS,PACKAGE,PAYMENT,RENTBIKE,RENTBIKEDETAIL,ROOM,ROOMDETAIL,USER
    }

   /* public void getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case BIKE:
                return new BikeDAOImpl();
            case MEAL:
                return new MealDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case USER:
                return new UserDAOImpl();
            case ORDERS:
                return new OrdersDAOImpl();
            case PACKAGE:
                return new PackageDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case RENTBIKE:
                return new RentBikeDAOImpl();
            case ROOMDETAIL:
                return new RoomDetailDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case RENTBIKEDETAIL:
                return new RentBikeDAOImpl();
            default:
                return null;
        }
    }*/
}
