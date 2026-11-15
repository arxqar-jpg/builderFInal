package kz.smartcafe.facade.console;

import kz.smartcafe.facade.CheckoutFacade;
import kz.smartcafe.builder.order.OrderLine;
import kz.smartcafe.observer.subject.OrderRecord;
import kz.smartcafe.observer.subject.OrderStatus;

import java.util.List;

public class StaffOrderConsole {

    private final CheckoutFacade facade;
    private final ConsoleInput input;

    public StaffOrderConsole(CheckoutFacade facade, ConsoleInput input) {
        this.facade = facade;
        this.input = input;
    }

    public void showStaffMenu() {
        while (true) {
            System.out.println("\n--- STAFF ORDER PANEL ---");
            System.out.println("1. Show all orders");
            System.out.println("2. Change order status");
            System.out.println("0. Back");

            int choice = input.readNumber("Choose: ", 2);

            if (choice <= 0) {
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
        List<OrderRecord> orders = facade.getOrders();

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
            for (OrderLine item : order.getCafeOrder().getItems()) {
                System.out.println("  " + item.getQuantity() + " x " + item.getName());
            }
            String note = order.getCafeOrder().getNote();
            if (!note.isEmpty()) {
                System.out.println("  Note: " + note);
            }
        }
    }

    private void changeStatus() {
        if (facade.getOrders().isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }
        showOrders();

        int id = input.readNumber("Order ID (0 to cancel): ", Integer.MAX_VALUE);

        if (id <= 0) {
            return;
        }

        System.out.println("1. PREPARING");
        System.out.println("2. READY");

        int choice = input.readNumber("New status (0 to cancel): ", 2);
        if (choice <= 0) {
            return;
        }

        OrderStatus status;
        if (choice == 1) {
            status = OrderStatus.PREPARING;
        } else {
            status = OrderStatus.READY;
        }
        if (!facade.changeStatus(id, status)) {
            System.out.println("Order not found or status change is not allowed.");
        }
    }
}
