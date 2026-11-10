package kz.smartcafe.PartDarkhan;

public abstract class MenuDecorator implements Menu {
    protected final Menu item;

    public MenuDecorator(Menu item) {
        if (item == null) {
            throw new IllegalArgumentException("Choose an item first");
        }

        if (!item.supportsToppings()) {
            throw new IllegalArgumentException(
                    "Toppings are not available for this item"
            );
        }

        this.item = item;
    }

    @Override
    public String getName() {
        return item.getName();
    }

    @Override
    public int getPrice() {
        return item.getPrice();
    }

    @Override
    public boolean supportsToppings() {
        return item.supportsToppings();
    }
}