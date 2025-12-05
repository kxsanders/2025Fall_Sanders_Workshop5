package org.example.DAO;

import org.example.SalesContract;
import java.sql.*;

public class SalesContractDAO {

    private String url,user,pass;

    public SalesContractDAO(String url,String user,String pass){
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public void insertSale(SalesContract s){
        String sql="INSERT INTO sales_contracts (VIN,Buyer_name,Sale_price,Sale_date,Dealer_id) VALUES (?,?,?,?,?)";

        try(Connection conn= DriverManager.getConnection(url,user,pass);
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,s.getVehicle().getVin());
            ps.setString(2,s.getCustomerName());
            ps.setDouble(3,s.getTotalPrice());
            ps.setString(4,s.getDate());
            ps.setInt(5,1); //or dynamic dealer_id
            ps.executeUpdate();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

