package kz.smartcafe.PartIslam.order;

import kz.smartcafe.PartDarkhan.Menu;
import kz.smartcafe.PartAitem.CheckoutFacade;
import java.util.List;
import java.util.Scanner;

public class OrderConsole {
    private static final int STUDENT_PERCENTAGE = 10;
    private static final int PROMO_PERCENTAGE = 15;
    private static final String PROMO_CODE = "CAFE15";

    private final Scanner scanner;private final CheckoutFacade checkoutFacade;
    private final CafeOrderBuilder builder = new CafeOrderBuilder();

    public OrderConsole(Scanner scanner, CheckoutFacade checkoutFacade) {
        this.scanner = scanner;
        this.checkoutFacade = checkoutFacade;
    }
    public void addItem(Menu item) {
        int quantity = readNumber("Quantity (0 to cancel): ", Integer.MAX_VALUE);
        if (quantity > 0) {
            builder.addItem(item, quantity);
            System.out.println("Added " + quantity + " x " + item.getName() + " to your order");
        }
    }

    public void editOrder() {
        List<OrderLine> items = builder.getItems();
        if (items.isEmpty()) {
            System.out.println("Your order is empty");
            return;
        }

        showItems(items);
        int itemNumber = readNumber("Choose an item to edit (0 to go back): ", items.size());
        if (itemNumber <= 0) {
            return;
        }
        int quantity = readNumber("New quantity (0 to remove): ", Integer.MAX_VALUE);
        if (quantity < 0) {
            return;
        }
        if (quantity == 0) {
            builder.removeItem(itemNumber - 1);
        } else {
            builder.changeQuantity(itemNumber - 1, quantity);
        }
    }

    public boolean checkout() {
        List<OrderLine> items = builder.getItems();
        if (items.isEmpty()) {
            System.out.println("Add at least one item before checkout");
            return false;
        }

        showItems(items);
        String name = readLine("Customer name: ");
        if (name == null) {
            return false;
        }
        builder.setCustomerName(name);

        String note = readLine("Order note (optional): ");
        if (note == null) {
            return false;
        }
        Discount discount = chooseDiscount();
        if (discount == null) {
            return false;
        }

        CafeOrder order = builder.setNote(note).setDiscountStrategy(discount).build();
        checkoutFacade.placeOrder(order);
        System.out.println("\nORDER CONFIRMED");
        System.out.println("Customer: " + order.getCustomerName());
        if (!order.getNote().isEmpty()) {
            System.out.println("Note: " + order.getNote());
        }
        System.out.println("Subtotal: " + order.getSubtotal() + " KZT");
        System.out.println("Discount: " + order.getDiscount() + " KZT");
        System.out.println("Total: " + order.getTotal() + " KZT");
        return true;
    }

    private Discount chooseDiscount() {
        while (true) {
            System.out.println("0. No discount");
            System.out.println("1. Student (" + STUDENT_PERCENTAGE + "%)");
            System.out.println("2. Promo code (" + PROMO_PERCENTAGE + "%)");
            int choice = readNumber("Choose a discount: ", 2);
            if (choice < 0) {
                return null;
            }
            if (choice == 0) {
                return new RegularPrice();
            }
            if (choice == 1) {
                return new StudentRate(STUDENT_PERCENTAGE);
            }

            String code = readLine("Promo code: ");
            if (code == null) {
                return null;
            }
            try {
                return new PromoRate(code, PROMO_CODE, PROMO_PERCENTAGE);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showItems(List<OrderLine> items) {
        System.out.println("\nYOUR ORDER");
        for (int i = 0; i < items.size(); i++) {
            OrderLine item = items.get(i);
            System.out.println((i + 1) + ". " + item.quantity() + " x " + item.name()
                    + " - " + item.subtotal() + " KZT");
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

    private int readNumber(String prompt, int max) {
        while (true) {
            String line = readLine(prompt);
            if (line == null) {
                return -1;
            }
            try {
                int number = Integer.parseInt(line.trim());
                if (number >= 0 && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // The same prompt covers non-numeric and out-of-range input.
            }
            System.out.println("Enter a whole number between 0 and " + max);
        }
    }
}
