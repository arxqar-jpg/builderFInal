package kz.smartcafe.PartAitem;

import java.util.List;
import java.util.Scanner;

public class StaffOrderConsole {

    private final CheckoutFacade facade;
    private final Scanner scanner;

    public StaffOrderConsole(CheckoutFacade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    public void showStaffMenu() {
        while (true) {
            System.out.println("\n--- STAFF ORDER PANEL ---");
            System.out.println("1. Show all orders");
            System.out.println("2. Change order status");
            System.out.println("0. Back");

            int choice = readNumber("Choose: ");

            if (choice == 0) {
                return;
            } else if (choice == 1) {
                showOrders();
            } else if (choice == 2) {
                changeStatus();
            } else {
                System.out.println("Choose 0, 1 or 2.");
            }
        }
    }

    private void showOrders() {
        List<OrderRecord> orders =
                facade.getOrderManager().getOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }

        System.out.println("\n--- ALL ORDERS ---");

        for (OrderRecord order : orders) {
            System.out.println(
                    "Order #" + order.getId()
                            + " | " + order.getCafeOrder().getCustomerName()
                            + " | " + order.getCafeOrder().getTotal() + " KZT"
                            + " | " + order.getStatus()
            );
        }
    }

    private void changeStatus() {
        showOrders();

        int id = readNumber("Order ID (0 to cancel): ");

        if (id == 0) {
            return;
        }

        System.out.println("1. PREPARING");
        System.out.println("2. READY");

        int choice = readNumber("New status: ");

        if (choice == 1) {
            facade.getOrderManager().changeStatus(
                    id, OrderStatus.PREPARING
            );
        } else if (choice == 2) {
            facade.getOrderManager().changeStatus(
                    id, OrderStatus.READY
            );
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private int readNumber(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }
}