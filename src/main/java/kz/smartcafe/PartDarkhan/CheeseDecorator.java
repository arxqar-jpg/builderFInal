package kz.smartcafe.PartDarkhan;

public class CheeseDecorator extends MenuDecorator {
    public static final int PRICE = 300;

    public CheeseDecorator(Menu item) {
        super(item);
    }

    @Override
    public String getName() {
        return item.getName() + " + Cheese";
    }

    @Override
    public int getPrice() {
        return item.getPrice() + PRICE;
    }
}