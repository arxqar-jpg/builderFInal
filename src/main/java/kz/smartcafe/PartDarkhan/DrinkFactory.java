package kz.smartcafe.PartDarkhan;

public class DrinkFactory implements MenuFactory {

    @Override
    public Menu createItem() {
        return new Drink();
    }
}