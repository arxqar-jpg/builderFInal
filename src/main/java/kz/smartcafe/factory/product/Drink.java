package kz.smartcafe.factory.product;

public class Drink implements Menu {

    @Override
    public String getName() {
        return "Drink";
    }

    @Override
    public int getPrice() {
        return 500;
    }

    @Override
    public boolean supportsToppings() {
        return false;
    }
}