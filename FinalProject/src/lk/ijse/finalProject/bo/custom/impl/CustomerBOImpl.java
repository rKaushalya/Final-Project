package lk.ijse.finalProject.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.bo.custom.CustomerBO;
import lk.ijse.finalProject.dto.CustomerDTO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    public static CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE cId=?";
        ResultSet execute = CrudUtil.execute(sql, id);

        if (execute.next()){
            return new CustomerDTO(
              execute.getString(1),
              execute.getString(2),
              execute.getString(3),
              execute.getString(4),
              execute.getString(5)
            );
        }
        return null;
    }

    public static boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET  name=?,address=?, contact=?,email=? WHERE cId=?";
        return CrudUtil.execute(sql, customerDTO.getName(), customerDTO.getAddress(), customerDTO.getContact(), customerDTO.getEmail(), customerDTO.getId());
    }

    public static boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE cId=?";
        return CrudUtil.execute(sql,id);
    }

    public static ObservableList<CustomerDTO> searchAllCustomer() throws SQLException, ClassNotFoundException {
        ObservableList<CustomerDTO> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer";
        ResultSet execute = CrudUtil.execute(sql);
        while (execute.next()){
            CustomerDTO customerDTO = new CustomerDTO(execute.getString(1),execute.getString(2),execute.getString(3),execute.getString(4),execute.getString(5));
            list.add(customerDTO);
        }
        return list;
    }

    public static boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql, customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getContact(), customerDTO.getEmail());
    }
}
