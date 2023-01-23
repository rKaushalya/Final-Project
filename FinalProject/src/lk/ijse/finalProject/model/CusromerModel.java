package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.to.Customer;
import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CusromerModel {
    public static Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE cId=?";
        ResultSet execute = CrudUtill.execute(sql, id);

        if (execute.next()){
            return new Customer(
              execute.getString(1),
              execute.getString(2),
              execute.getString(3),
              execute.getString(4),
              execute.getString(5)
            );
        }
        return null;
    }

    public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET  name=?,address=?, contact=?,email=? WHERE cId=?";
        return CrudUtill.execute(sql,customer.getName(),customer.getAddress(),customer.getContact(),customer.getEmail(),customer.getId());
    }

    public static boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE cId=?";
        return CrudUtill.execute(sql,id);
    }

    public static ObservableList<Customer> searchAllCustomer() throws SQLException, ClassNotFoundException {
        ObservableList<Customer> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer";
        ResultSet execute = CrudUtill.execute(sql);
        while (execute.next()){
            Customer customer = new Customer(execute.getString(1),execute.getString(2),execute.getString(3),execute.getString(4),execute.getString(5));
            list.add(customer);
        }
        return list;
    }

    public static boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES (?,?,?,?,?)";
        return CrudUtill.execute(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getContact(),customer.getEmail());
    }
}
