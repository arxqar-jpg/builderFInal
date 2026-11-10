package kz.smartcafe.PartDarkhan;

public class SauceDecorator extends MenuDecorator {
    public static final int PRICE = 150;

    public SauceDecorator(Menu item) {
        super(item);
    }

    @Override
    public String getName() {
        return item.getName() + " + Sauce";
    }

    @Override
    public int getPrice() {
        return item.getPrice() + PRICE;
    }
}