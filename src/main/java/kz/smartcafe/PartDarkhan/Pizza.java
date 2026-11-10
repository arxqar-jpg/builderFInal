package kz.smartcafe.PartDarkhan;

public class Pizza implements Menu {

    @Override
    public String getName() {
        return "Pizza";
    }

    @Override
    public int getPrice() {
        return 2500;
    }

    @Override
    public boolean supportsToppings() {
        return true;
    }
}