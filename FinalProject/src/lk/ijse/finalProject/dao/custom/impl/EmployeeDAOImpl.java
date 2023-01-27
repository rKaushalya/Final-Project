package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.EmployeeDAO;
import lk.ijse.finalProject.entity.EmployeeEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean add(EmployeeEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?)", entity.geteId(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getNic());
    }

    @Override
    public boolean update(EmployeeEntity entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE employee SET  name=?,address=?,contact=?, nic=? WHERE eId=?",
                entity.getName(), entity.getAddress(), entity.getContact(), entity.getNic(), entity.geteId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM employee WHERE eId=?", id);
    }

    @Override
    public EmployeeEntity search(String id) throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT * FROM employee WHERE eId=?", id);
        if (execute.next()) {
            return new EmployeeEntity(
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
    public int loadAllEmployee() throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT COUNT(*) FROM employee");
        int empCount = 0;
        if (execute.next()) {
            empCount = execute.getInt(1);
        }
        return empCount;
    }
}
