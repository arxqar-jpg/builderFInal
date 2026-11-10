package kz.smartcafe.PartDarkhan;

public class PizzaFactory implements MenuFactory {

    @Override
    public Menu createItem() {
        return new Pizza();
    }
}