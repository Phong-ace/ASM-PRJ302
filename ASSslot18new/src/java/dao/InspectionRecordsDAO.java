package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import model.*;
import dao.*;

public class InspectionRecordsDAO {

    public static InspectionRecords getInspectionRecordsByVehicleId(int vehiclesId) {
        DBContext db = DBContext.getInstance();
        ArrayList<InspectionRecords> ir = new ArrayList<InspectionRecords>();

        try {
            String sql = """
                         select * from InspectionRecords
                         where VehicleID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, vehiclesId);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                InspectionRecords record = new InspectionRecords(
                        rs.getInt("RecordID"),
                        rs.getInt("VehicleID"),
                        rs.getInt("StationID"),
                        rs.getInt("InspectorID"),
                        rs.getDate("RegistrationDate"),
                        rs.getDate("InspectionDate"),
                        rs.getString("Result"),
                        rs.getDouble("CO2Emission"),
                        rs.getDouble("HCEmission"),
                        rs.getString("Comments")
                );
                ir.add(record);
            }
        } catch (Exception e) {
            return null;
        }

        if (ir.isEmpty()) {
            return null;
        } else {
            return ir.get(0);
        }
    }

    public static ArrayList<InspectionRecords> getListRecordByVehicleId(int vehiclesId) {
        DBContext db = DBContext.getInstance();
        ArrayList<InspectionRecords> ir = new ArrayList<InspectionRecords>();
        try {
            String sql = """
                         select * from InspectionRecords
                         where VehicleID = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, vehiclesId);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                InspectionRecords record = new InspectionRecords(
                        rs.getInt("RecordID"),
                        rs.getInt("VehicleID"),
                        rs.getInt("StationID"),
                        rs.getInt("InspectorID"),
                        rs.getDate("RegistrationDate"),
                        rs.getDate("InspectionDate"),
                        rs.getString("Result"),
                        rs.getDouble("CO2Emission"),
                        rs.getDouble("HCEmission"),
                        rs.getString("Comments")
                );
                ir.add(record);
            }
        } catch (Exception e) {
            return ir;
        }

        return ir;
    }

    public static InspectionRecords addRecords(InspectionRecords record) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         INSERT INTO InspectionRecords (VehicleID, StationID, InspectorID, RegistrationDate, InspectionDate, Result, CO2Emission, HCEmission, Comments) VALUES
                         (?, ?, ?, ?, ?, ?, ?, ?, ?)
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setInt(1, record.getVehicleID());
            statment.setInt(2, record.getStationID());
            statment.setInt(3, record.getInspectorID());
            statment.setDate(4, record.getRegistrationDate());
            statment.setDate(5, record.getInspectionDate());
            statment.setString(6, record.getResult());
            statment.setDouble(7, record.getCO2Emission());
            statment.setDouble(8, record.getHCEmission());
            statment.setString(9, record.getComments());
            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return record;
        }
    }

    public static InspectionRecords addRecordsforOwner(InspectionRecords record) {
        DBContext db = DBContext.getInstance();
        int rs = 0;

        try {
            String sql = """
                     INSERT INTO InspectionRecords (VehicleID, StationID, InspectorID, RegistrationDate, Result, CO2Emission, HCEmission) 
                     VALUES (?, ?, ?, ?, ?, ?, ?)
                     """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);

            statment.setInt(1, record.getVehicleID());
            statment.setInt(2, record.getStationID());
            
            statment.setInt(3, record.getInspectorID());

            statment.setDate(4, record.getRegistrationDate());

            statment.setString(5, record.getResult());
            statment.setDouble(6, record.getCO2Emission());    
            statment.setDouble(7, record.getHCEmission());

            rs = statment.executeUpdate();
        } catch (Exception e) {
            return null;
        }

        if (rs == 0) {
            return null;
        } else {
            return record;
        }
    }

    public static ArrayList<InspectionRecords> getInspectionRecords() {
        DBContext db = DBContext.getInstance();
        ArrayList<InspectionRecords> ir = new ArrayList<InspectionRecords>();

        try {
            String sql = """
                         Select * from InspectionRecords
                         order by RecordID desc
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                InspectionRecords record = new InspectionRecords(
                        rs.getInt("RecordID"),
                        rs.getInt("VehicleID"),
                        rs.getInt("StationID"),
                        rs.getInt("InspectorID"),
                        rs.getDate("RegistrationDate"),
                        rs.getDate("InspectionDate"),
                        rs.getString("Result"),
                        rs.getDouble("CO2Emission"),
                        rs.getDouble("HCEmission"),
                        rs.getString("Comments")
                );
                ir.add(record);
            }
        } catch (Exception e) {
            return null;
        }

        if (ir.isEmpty()) {
            return null;
        } else {
            return ir;
        }
    }

    public static void main(String[] args) {
//        ArrayList<InspectionRecords> re = InspectionRecordsDAO.getInspectionRecords();
//        for (InspectionRecords inspectionRecords : re) {
//            System.out.println(inspectionRecords.toString());
//
//        }
//        System.out.println("new");
//        Date registrationDate = java.sql.Date.valueOf("2025-01-23");
//        InspectionRecords record = new InspectionRecords(5, 2, 0, registrationDate, null, null, 0, 0, null);
//        re.add(record);
//        for (InspectionRecords inspectionRecords : re) {
//            System.out.println(inspectionRecords.toString());
//
//        }
    }

}
