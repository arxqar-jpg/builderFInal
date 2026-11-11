package kz.smartcafe.PartDarkhan;

public class MenuService {
    private final MenuFactory[] factories;

    public MenuService(MenuFactory[] factories) {
        this.factories = factories.clone();
    }

    public Menu[] getMenu() {
        Menu[] menu = new Menu[factories.length];

        for (int i = 0; i < factories.length; i++) {
            menu[i] = factories[i].createItem();
        }

        return menu;
    }

    public Menu createItem(int number) {
        if (number < 1 || number > factories.length) {
            throw new IllegalArgumentException("Invalid menu number");
        }

        return factories[number - 1].createItem();
    }

    public Menu createItemByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter an item name");
        }

        String requestedName = name.trim();

        for (MenuFactory factory : factories) {
            Menu item = factory.createItem();

            if (item.getName().equalsIgnoreCase(requestedName)) {
                return item;
            }
        }

        throw new IllegalArgumentException(
                "Sorry this item is not available"
        );
    }
}