package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.EmployeeEntity;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<EmployeeEntity, String> {

    int loadAllEmployee() throws SQLException, ClassNotFoundException;
}
