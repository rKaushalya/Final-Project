package lk.ijse.finalProject.model;

import lk.ijse.finalProject.to.Employee;
import lk.ijse.finalProject.utill.CrudUtill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    public static boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee VALUES (?,?,?,?,?)";
        return CrudUtill.execute(sql,employee.getId(),employee.getName(),employee.getAddress(),employee.getContact(),employee.getNic());
    }

    public static Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE eId=?";
        ResultSet execute = CrudUtill.execute(sql, id);

        if (execute.next()){
            return new Employee(
              execute.getString(1),
              execute.getString(2),
              execute.getString(3),
              execute.getString(4),
              execute.getString(5)
            );
        }
        return null;
    }

    public static boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employee SET  name=?,address=?,contact=?, nic=? WHERE eId=?";
        return CrudUtill.execute(sql,employee.getName(),employee.getAddress(),employee.getContact(),employee.getNic(),employee.getId());
    }

    public static boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM employee WHERE eId=?";
        return CrudUtill.execute(sql,id);
    }

    public static int loadAllEmployee() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM employee";
        ResultSet execute = CrudUtill.execute(sql);
        int empCount = 0;
        if (execute.next()){
            empCount = execute.getInt(1);
        }
        return empCount;
    }
}
