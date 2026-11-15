package kz.smartcafe.factory.creator;

import kz.smartcafe.factory.product.Drink;
import kz.smartcafe.factory.product.Menu;

public class DrinkFactory implements MenuFactory {

    @Override
    public Menu createItem() {
        return new Drink();
    }
}