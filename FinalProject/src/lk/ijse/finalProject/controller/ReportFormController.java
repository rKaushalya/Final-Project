package lk.ijse.finalProject.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalProject.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;

public class ReportFormController {

    public AnchorPane pane;

    public void getReport(ActionEvent actionEvent) {
        try {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/finalProject/view/report/CustomerDetail.jrxml");

        /*HashMap is something like key-value pairing data storing mechanism*/
        /*HashMap<String, Object> hm = new HashMap<>();
        hm.put("cashier_name", txtCashier.getText());
        hm.put("table_no", Integer.parseInt(txtTblNo.getText()));*/


            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            /*if you haven't any parameters(HashMap reference) to pass, then put null for second argument*/
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getDbConnection().getConnection());

            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getDbConnection().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadEmployeeDetails(ActionEvent actionEvent) {
        try {
            InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/finalProject/view/report/orders.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getDbConnection().getConnection());

            JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
