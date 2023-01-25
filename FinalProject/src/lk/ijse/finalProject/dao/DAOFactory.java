package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.dao.custom.RentBikeDetailDAO;
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
        CUSTOMER,EMPLOYEE,MEAL,ORDERDETAIL,ORDERS,PACKAGE,PAYMENT,RENTBIKE,RENTBIKEDETAIL,ROOM,ROOMDETAIL,USER,QUERY
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerDAOImpl();
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
                return new RentBikeDetailDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
