package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.PackageDAO;
import lk.ijse.finalProject.dto.PackagesDTO;
import lk.ijse.finalProject.entity.PackageEntity;
import lk.ijse.finalProject.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PackageDAOImpl implements PackageDAO {
    @Override
    public ArrayList<String> loadPkgId() throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT pkgId FROM package");
        ArrayList<String> pkgId = new ArrayList<>();

        while (execute.next()) {
            pkgId.add(execute.getString(1));
        }
        return pkgId;
    }

    @Override
    public PackageEntity searchPkg(String id) throws SQLException, ClassNotFoundException {
        ResultSet execute = CrudUtil.execute("SELECT * FROM package WHERE pkgId=?", id);
        if (execute.next()) {
            return new PackageEntity(
                    execute.getString(1),
                    execute.getString(2),
                    execute.getDouble(3),
                    execute.getString(4)
            );
        }
        return null;
    }
}
