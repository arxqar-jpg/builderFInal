package kz.smartcafe.factory.product;

public class Burger implements Menu {

    @Override
    public String getName() {
        return "Burger";
    }
    @Override
    public int getPrice() {
        return 1800;
    }
    @Override
    public boolean supportsToppings() {
        return true;
    }
}
