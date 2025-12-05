package org.example.DAO;

import org.example.LeaseContract;
import java.sql.*;

public class LeaseContractDAO {

    private String url;
    private String user;
    private String pass;

    public LeaseContractDAO(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public void insertLease(LeaseContract l) {

        String sql = "INSERT INTO lease_contracts " +
                "(VIN, leasee_name, monthly_payment, months, start_date, dealer_id) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, l.getVehicle().getVin());   // VIN = STRING
            ps.setString(2, l.getCustomerName());
            ps.setDouble(3, l.getMonthlyPayment());
            ps.setInt(4, 36);                           // Lease is always 36 months
            ps.setString(5, l.getDate());
            ps.setInt(6, 1);                            // Temporary dealer_id until UI passes one

            ps.executeUpdate();
            System.out.println("Lease contract saved!");

        } catch (Exception e) {
            System.out.println("Error inserting lease contract.");
            e.printStackTrace();
        }
    }
}





