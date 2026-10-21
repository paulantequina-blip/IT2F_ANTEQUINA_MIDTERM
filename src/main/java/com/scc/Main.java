package com.scc;

import com.scc.dao.DatabaseConnection;
import com.scc.ui.PharmacyMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize SQLite Database and Table
        DatabaseConnection.initializeDatabase();

        // Launch the Pharmacy Console Menu
        PharmacyMenu menu = new PharmacyMenu();
        menu.start();
    }
}