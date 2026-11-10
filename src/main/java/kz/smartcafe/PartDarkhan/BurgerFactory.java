package kz.smartcafe.PartDarkhan;

public class BurgerFactory implements MenuFactory {

    @Override
    public Menu createItem() {
        return new Burger();
    }
}