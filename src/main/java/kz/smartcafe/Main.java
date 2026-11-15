package kz.smartcafe;

import kz.smartcafe.decorator.concrete.CheeseDecorator;
import kz.smartcafe.decorator.concrete.SauceDecorator;
import kz.smartcafe.facade.CheckoutFacade;
import kz.smartcafe.builder.console.OrderConsole;
import kz.smartcafe.facade.console.ConsoleInput;
import kz.smartcafe.facade.console.StaffOrderConsole;
import kz.smartcafe.factory.creator.BurgerFactory;
import kz.smartcafe.factory.creator.DrinkFactory;
import kz.smartcafe.factory.creator.MenuFactory;
import kz.smartcafe.factory.creator.PizzaFactory;
import kz.smartcafe.factory.product.Menu;
import kz.smartcafe.factory.service.MenuService;
import kz.smartcafe.observer.notification.ClientNotification;
import kz.smartcafe.observer.notification.KitchenNotification;
import kz.smartcafe.observer.subject.OrderManager;

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
        ConsoleInput input = new ConsoleInput(scanner);
        OrderManager orderManager = new OrderManager();

        orderManager.addObserver(new ClientNotification());
        orderManager.addObserver(new KitchenNotification());

        CheckoutFacade checkoutFacade =
                new CheckoutFacade(orderManager);

        StaffOrderConsole staffConsole =
                new StaffOrderConsole(checkoutFacade, input);
        OrderConsole orderConsole =
                new OrderConsole(input, checkoutFacade);

        while (true) {
            showWelcome();

            int action = input.readNumber("Choose an option: ", 4);

            if (action <= 0) {
                System.out.println("Thank you for visiting Smart Cafe!");
                break;
            }

            try {
                if (action == 3) {
                    orderConsole.editOrder();
                    continue;
                }
                if (action == 4) {
                    if (orderConsole.checkout()) {
                        staffConsole.showStaffMenu();
                    }

                    continue;
                }

                Menu item;

                if (action == 1) {
                    String name = input.readLine("Enter the item name: ");
                    if (name == null) {
                        break;
                    }

                    item = menuService.createItemByName(name);
                } else if (action == 2) {
                    showMenu(menuService);

                    int number = input.readNumber("Choose item: ", factories.length);

                    if (number <= 0) {
                        continue;
                    }

                    item = menuService.createItem(number);
                } else {
                    System.out.println("Please choose a listed option");
                    continue;
                }

                System.out.println("You chose: " + item.getName());

                item = addToppings(input, item);

                System.out.println("Price per item: " + item.getPrice() + " KZT");
                orderConsole.addItem(item);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void showWelcome() {
        System.out.println("\nWELCOME TO SMART CAFE");
        System.out.println("What would you like to have?");
        System.out.println("1. Order by name");
        System.out.println("2. Show menu");
        System.out.println("3. Edit order");
        System.out.println("4. Checkout");
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

    private static Menu addToppings(ConsoleInput input, Menu item) {
        if (!item.supportsToppings()) {
            return item;
        }

        if (askYesNo(input,
                "Add cheese for " + CheeseDecorator.PRICE + " KZT?")) {

            item = new CheeseDecorator(item);

            System.out.println(
                    "Cheese added. Current price: "
                            + item.getPrice() + " KZT"
            );
        }

        if (askYesNo(input,
                "Add sauce for " + SauceDecorator.PRICE + " KZT?")) {

            item = new SauceDecorator(item);

            System.out.println(
                    "Sauce added. Current price: "
                            + item.getPrice() + " KZT"
            );
        }

        return item;
    }

    private static boolean askYesNo(ConsoleInput input, String question) {
        System.out.println(question);
        System.out.println("1. Yes");
        System.out.println("0. No");

        return input.readNumber("Your choice: ", 1) == 1;
    }
}
