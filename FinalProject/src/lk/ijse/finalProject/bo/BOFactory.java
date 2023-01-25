package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        if (boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        BOOKING,CUSTOMER,EMPLOYEE,FORGETPASSWORD,MEAL,REGISTER,RENTBIKE,ROOM,USER
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case MEAL:
                return new MealBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case USER:
                return new UserBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case RENTBIKE:
                return new RentBikeBOImpl();
            case BOOKING:
                return new BookingBOImpl();
            case REGISTER:
                return new RegisterBOImpl();
            case FORGETPASSWORD:
                return new ForgotPasswordBOImpl();
            default:
                return null;
        }
    }
}
