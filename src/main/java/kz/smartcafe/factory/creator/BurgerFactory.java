package kz.smartcafe.factory.creator;

import kz.smartcafe.factory.product.Burger;
import kz.smartcafe.factory.product.Menu;

public class BurgerFactory implements MenuFactory {

    @Override
    public Menu createItem() {
        return new Burger();
    }
}