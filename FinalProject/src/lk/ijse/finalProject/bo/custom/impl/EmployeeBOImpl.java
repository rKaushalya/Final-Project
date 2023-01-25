package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.EmployeeBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.dao.custom.EmployeeDAO;
import lk.ijse.finalProject.dto.EmployeeDTO;
import lk.ijse.finalProject.entity.EmployeeEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeBOImpl implements EmployeeBO {
    //DI
    private final EmployeeDAO empDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return empDAO.add(new EmployeeEntity(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getAddress(),
                employeeDTO.getContact(), employeeDTO.getNic()));
    }

    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        EmployeeEntity search = empDAO.search(id);
        return new EmployeeDTO(search.geteId(),search.getName(),search.getAddress(),search.getContact(),search.getNic());
    }

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return empDAO.update(new EmployeeEntity(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getContact(),
                employeeDTO.getNic()));
    }

    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return empDAO.delete(id);
    }

    //remove soon
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
