package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.entity.PackageEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PackageDAO extends SuperDAO {

    ArrayList<String> loadPkgId() throws SQLException, ClassNotFoundException;

    PackageEntity searchPkg(String id) throws SQLException, ClassNotFoundException;
}
