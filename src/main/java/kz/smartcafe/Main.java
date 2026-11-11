package kz.smartcafe;

import kz.smartcafe.PartDarkhan.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MenuFactory[] factories = {
                new PizzaFactory(),
                new BurgerFactory(),
                new DrinkFactory()
        };

        MenuService menuService = new MenuService(factories);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showWelcome();

            int action = readNumber(scanner, "Choose an option: ");

            if (action == 0) {
                System.out.println("Thank you for visiting Smart Cafe!");
                break;
            }

            try {
                Menu item;

                if (action == 1) {
                    System.out.print("Enter the item name: ");

                    if (!scanner.hasNextLine()) {
                        break;
                    }

                    item = menuService.createItemByName(
                            scanner.nextLine()
                    );
                } else if (action == 2) {
                    showMenu(menuService);

                    int number = readNumber(scanner, "Choose item: ");

                    if (number == 0) {
                        continue;
                    }

                    item = menuService.createItem(number);
                } else {
                    System.out.println("Please choose 1, 2 or 0");
                    continue;
                }

                System.out.println("You chose: " + item.getName());

                item = addToppings(scanner, item);

                System.out.println("\nYour item is ready!");
                System.out.println("Item: " + item.getName());
                System.out.println("Price: " + item.getPrice() + " KZT");

                // Order Service will receive item here.
                break;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void showWelcome() {
        System.out.println("\nWELCOME TO SMART CAFE");
        System.out.println("What would you like to have?");
        System.out.println("1. Order by name");
        System.out.println("2. Show menu");
        System.out.println("0. Exit");
    }

    private static void showMenu(MenuService service) {
        System.out.println("\nSMART CAFE MENU");

        Menu[] menu = service.getMenu();

        for (int i = 0; i < menu.length; i++) {
            System.out.println(
                    (i + 1) + ". " + menu[i].getName()
                            + " - " + menu[i].getPrice() + " KZT"
            );
        }

        System.out.println("0. Back");
    }

    private static Menu addToppings(Scanner scanner, Menu item) {
        if (!item.supportsToppings()) {
            return item;
        }

        if (askYesNo(scanner,
                "Add cheese for " + CheeseDecorator.PRICE + " KZT?")) {

            item = new CheeseDecorator(item);

            System.out.println(
                    "Cheese added. Current price: "
                            + item.getPrice() + " KZT"
            );
        }

        if (askYesNo(scanner,
                "Add sauce for " + SauceDecorator.PRICE + " KZT?")) {

            item = new SauceDecorator(item);

            System.out.println(
                    "Sauce added. Current price: "
                            + item.getPrice() + " KZT"
            );
        }

        return item;
    }

    private static boolean askYesNo(Scanner scanner, String question) {
        System.out.println(question);
        System.out.println("1. Yes");
        System.out.println("0. No");

        while (true) {
            int choice = readNumber(scanner, "Your choice: ");

            if (choice == 1) {
                return true;
            }

            if (choice == 0) {
                return false;
            }

            System.out.println("Please choose 1 for Yes or 0 for No");
        }
    }

    private static int readNumber(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);

            if (!scanner.hasNextLine()) {
                return 0;
            }

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number");
            }
        }
    }
}