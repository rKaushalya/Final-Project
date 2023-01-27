package lk.ijse.finalProject.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dao.custom.CustomerDAO;
import lk.ijse.finalProject.entity.CustomerEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(CustomerEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?)", entity.getId(), entity.getName(),
                entity.getAddress(), entity.getContact(), entity.getEmail());
    }

    @Override
    public boolean update(CustomerEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET  name=?,address=?, contact=?,email=? WHERE cId=?",
                entity.getName(), entity.getAddress(), entity.getContact(), entity.getEmail(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE cId=?", id);
    }

    @Override
    public CustomerEntity search(String id) throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT * FROM customer WHERE cId=?", id);

        if (execute.next()) {
            return new CustomerEntity(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getString(4),
                    execute.getString(5)
            );
        }
        return null;
    }

    @Override
    public ObservableList<CustomerEntity> searchAllCustomer() throws SQLException, ClassNotFoundException {
        ObservableList<CustomerEntity> list = FXCollections.observableArrayList();
        ResultSet execute = CrudUtil.execute("SELECT * FROM customer");
        while (execute.next()) {
            CustomerEntity customerEntity = new CustomerEntity(execute.getString(1), execute.getString(2), execute.getString(3), execute.getString(4), execute.getString(5));
            list.add(customerEntity);
        }
        return list;
    }
}
