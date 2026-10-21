package com.scc.dao;

import com.scc.model.PharmacyEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PharmacyDao {

    public boolean addMedicine(String brandName, String category, double price, int stock) {
        String sql = "INSERT INTO medicines (brand_name, category, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, brandName);
            pstmt.setString(2, category);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stock);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting medicine: " + e.getMessage());
            return false;
        }
    }

    public List<PharmacyEntity> getAllMedicines() {
        List<PharmacyEntity> list = new ArrayList<>();
        String sql = "SELECT id, brand_name, category, price, stock FROM medicines ORDER BY id ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PharmacyEntity entity = new PharmacyEntity(
                        rs.getInt("id"),
                        rs.getString("brand_name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                list.add(entity);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching inventory: " + e.getMessage());
        }

        return list;
    }
}