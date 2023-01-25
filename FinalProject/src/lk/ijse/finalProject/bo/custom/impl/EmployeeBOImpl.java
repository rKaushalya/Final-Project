package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.EmployeeBO;
import lk.ijse.finalProject.dto.EmployeeDTO;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeBOImpl implements EmployeeBO {
    public static boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql, employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getContact(), employeeDTO.getNic());
    }

    public static EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE eId=?";
        ResultSet execute = CrudUtil.execute(sql, id);

        if (execute.next()){
            return new EmployeeDTO(
              execute.getString(1),
              execute.getString(2),
              execute.getString(3),
              execute.getString(4),
              execute.getString(5)
            );
        }
        return null;
    }

    public static boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employee SET  name=?,address=?,contact=?, nic=? WHERE eId=?";
        return CrudUtil.execute(sql, employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getContact(), employeeDTO.getNic(), employeeDTO.getId());
    }

    public static boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM employee WHERE eId=?";
        return CrudUtil.execute(sql,id);
    }

    public static int loadAllEmployee() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM employee";
        ResultSet execute = CrudUtil.execute(sql);
        int empCount = 0;
        if (execute.next()){
            empCount = execute.getInt(1);
        }
        return empCount;
    }
}
