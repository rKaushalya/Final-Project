package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.EmployeeDTO;

import java.sql.SQLException;

public interface EmployeeBO extends SuperBO {

    boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
}
