package org.example.DAO;

import org.example.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    private String url;
    private String user;
    private String pass;

    public VehicleDAO(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    //=================== GET ===================//
    public List<Vehicle> getByPrice(double min, double max){
        return query("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?", min,max);
    }

    public List<Vehicle> getByMakeModel(String make,String model){
        return query("SELECT * FROM vehicles WHERE make=? AND model=?", make,model);
    }

    public List<Vehicle> getByYear(int min,int max){
        return query("SELECT * FROM vehicles WHERE year BETWEEN ? AND ?", min,max);
    }

    public List<Vehicle> getByColor(String color){
        return query("SELECT * FROM vehicles WHERE color=?", color);
    }

    public List<Vehicle> getByMileage(int min,int max){
        return query("SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?", min,max);
    }

    public List<Vehicle> getByType(String type){
        return query("SELECT * FROM vehicles WHERE type=?", type);
    }

    //================ GET ALL ===================
    public List<Vehicle> getAllVehicles() {
        return query("SELECT * FROM vehicles");
    }

    //================ INSERT ====================
    public void insertVehicle(Vehicle v){
        String sql="INSERT INTO vehicles (VIN,Make,Model,Year,Color,Type,Price,Mileage,SOLD) VALUES (?,?,?,?,?,?,?,?,0)";

        try(Connection conn = DriverManager.getConnection(url,user,pass);
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, v.getVin());
            ps.setString(2, v.getMake());
            ps.setString(3, v.getModel());
            ps.setInt(4, v.getYear());
            ps.setString(5, v.getColor());
            ps.setString(6, v.getVehicleType());
            ps.setDouble(7, v.getPrice());
            ps.setInt(8, v.getOdometer());

            ps.executeUpdate();
            System.out.println("Vehicle added to DB!");

        } catch (SQLException e){ e.printStackTrace(); }
    }

    //================ GET SINGLE VIN ===============
    public Vehicle getByVin(String vin) {
        List<Vehicle> list = query("SELECT * FROM vehicles WHERE VIN = ?", vin);
        return list.isEmpty() ? null : list.get(0);
    }

    //================ DELETE =======================
    public void deleteVehicle(String vin) {
        String sql = "DELETE FROM vehicles WHERE VIN = ?";

        try(Connection conn = DriverManager.getConnection(url,user,pass);
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, vin);
            int rows = ps.executeUpdate();

            System.out.println(rows > 0 ? "Vehicle removed." : "Vehicle NOT found.");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //=========== GENERIC QUERY BUILDER + MAPPER ============
    private List<Vehicle> query(String sql,Object... params){
        List<Vehicle> list = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url,user,pass);
            PreparedStatement ps = conn.prepareStatement(sql)) {

            for(int i=0;i<params.length;i++){
                ps.setObject(i+1, params[i]);
            }

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Vehicle(
                        rs.getString("VIN"),
                        rs.getInt("Year"),
                        rs.getString("Make"),
                        rs.getString("Model"),
                        rs.getString("Type"),
                        rs.getString("Color"),
                        rs.getInt("Mileage"),
                        rs.getDouble("Price")
                ));
            }

        } catch(Exception e){ e.printStackTrace(); }

        return list;
    }
}


