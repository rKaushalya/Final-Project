package lk.ijse.finalProject.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {

    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    ObservableList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
}
