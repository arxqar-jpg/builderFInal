package kz.smartcafe.factory.creator;

import kz.smartcafe.factory.product.Menu;
import kz.smartcafe.factory.product.Pizza;

public class PizzaFactory implements MenuFactory {

    @Override
    public Menu createItem() {
        return new Pizza();
    }
}