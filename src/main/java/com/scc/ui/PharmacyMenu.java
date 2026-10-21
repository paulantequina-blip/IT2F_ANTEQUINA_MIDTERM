package com.scc.ui;

import com.scc.dao.PharmacyDao;
import com.scc.model.PharmacyEntity;

import java.util.List;
import java.util.Scanner;

public class PharmacyMenu {
    private final PharmacyDao dao = new PharmacyDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("==================================================");
            System.out.println("           PHARMACY MEDICINE INVENTORY");
            System.out.println("==================================================");
            System.out.println("[1] Add New Medicine");
            System.out.println("[2] View Medicine Inventory");
            System.out.println("[3] Exit System");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Flush Scanner buffer after integer input
            } else {
                scanner.nextLine(); // Flush invalid input
                System.out.println("Invalid input. Please enter a valid number.\n");
                continue;
            }

            System.out.println();

            switch (choice) {
                case 1 -> addMedicinePrompt();
                case 2 -> viewInventory();
                case 3 -> {
                    System.out.println("Exiting system... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please choose 1, 2, or 3.\n");
            }
        }
    }

    private void addMedicinePrompt() {
        System.out.println("--- ADD NEW MEDICINE ---");
        System.out.print("Enter Brand Name: ");
        String brandName = scanner.nextLine().trim();

        System.out.print("Enter Category (ANTIBIOTIC / PAINKILLER / VITAMIN): ");
        String category = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Stock Quantity: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Flush Scanner buffer after numerical input

        System.out.println();

        boolean success = dao.addMedicine(brandName, category, price, stock);
        if (success) {
            System.out.println("SUCCESS: Medicine item added to inventory!");
        } else {
            System.out.println("ERROR: Failed to add medicine item.");
        }
        System.out.println();
    }

    private void viewInventory() {
        List<PharmacyEntity> list = dao.getAllMedicines();

        System.out.println("==================================================================================");
        System.out.println("                            PHARMACY MEDICINE STOCK");
        System.out.println("==================================================================================");
        System.out.println("+-----+--------------------+------------+------------+------------+");
        System.out.printf("| %-3s | %-18s | %-10s | %-10s | %-10s |\n", "ID", "BRAND NAME", "CATEGORY", "PRICE", "STOCK");
        System.out.println("+-----+--------------------+------------+------------+------------+");

        if (list.isEmpty()) {
            System.out.printf("| %-74s |\n", "No records found.");
        } else {
            for (PharmacyEntity entity : list) {
                System.out.printf("| %-3d | %-18s | %-10s | %-10.2f | %-10d |\n",
                        entity.id(),
                        entity.brandName(),
                        entity.category(),
                        entity.price(),
                        entity.stock());
            }
        }

        System.out.println("+-----+--------------------+------------+------------+------------+");
        System.out.println("Total Records Displayed: " + list.size());
        System.out.println();
    }
}