package Controller;

import Model.TradingsData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TradingsController {
    public static void uploadAllTradingData(JTable table) {
        ResultSet resultSet = TradingsData.getAllTrading();
        showHistoryTradingPanel(table, resultSet);
    }

    public static void uploadTradingByType(JTable table, String type) {
        if (type.equals("Tất Cả")) {
            uploadAllTradingData(table);
            return;
        }
        ResultSet resultSet = TradingsData.getTradedByType(type);
        showHistoryTradingPanel(table, resultSet);
    }

    private static void showHistoryTradingPanel(JTable table, ResultSet resultSet) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        Object[] dataObjects = new Object[5];
        try {
            while (resultSet.next()) {
                dataObjects[0] = resultSet.getString("LoaiGD");
                dataObjects[1] = resultSet.getString("NgayGD");
                dataObjects[2] = resultSet.getString("TenKH");
                dataObjects[3] = resultSet.getString("GhiChu");
                dataObjects[4] = resultSet.getString("SoTien");
                tableModel.addRow(dataObjects);
            }
        } catch (SQLException e) {
            System.out.println("Load Data Fail");
        }
    }

    public static void uploadTradingDataOverview(JTable table){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        Object[] dataObjects = new Object[3];
        ResultSet resultSet = TradingsData.getAllTrading();
        try{
            while (resultSet.next()){
                dataObjects[0] = resultSet.getString("TenKH");
                dataObjects[1] = resultSet.getString("GhiChu");
                dataObjects[2] = resultSet.getString("SoTien");
                tableModel.addRow(dataObjects);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

}