package lk.ijse.finalProject.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.CustomerEntity;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<CustomerEntity, String> {

    ObservableList<CustomerEntity> searchAllCustomer() throws SQLException, ClassNotFoundException;
}
