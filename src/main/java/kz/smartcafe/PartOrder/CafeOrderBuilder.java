package kz.smartcafe.PartOrder;

import kz.smartcafe.PartDarkhan.Menu;

import java.util.ArrayList;
import java.util.List;

public class CafeOrderBuilder {
    private final List<OrderLine> items = new ArrayList<>();
    private String customerName = "";
    private String note = "";
    private Discount discountStrategy = new RegularPrice();

    public CafeOrderBuilder addItem(Menu menuItem, int quantity) {
        OrderLine newItem = new OrderLine(menuItem.getName(), menuItem.getPrice(), quantity);
        for (int i = 0; i < items.size(); i++) {
            OrderLine item = items.get(i);
            if (item.name().equals(newItem.name()) && item.unitPrice() == newItem.unitPrice()) {
                if (quantity > Integer.MAX_VALUE - item.quantity()) {
                    throw new IllegalArgumentException("Quantity is too large");
                }
                return changeQuantity(i, item.quantity() + quantity);
            }
        }
        items.add(newItem);
        return this;
    }

    public CafeOrderBuilder changeQuantity(int index, int quantity) {
        OrderLine item = items.get(index);
        items.set(index, new OrderLine(item.name(), item.unitPrice(), quantity));
        return this;
    }

    public CafeOrderBuilder removeItem(int index) {
        items.remove(index);
        return this;
    }

    public CafeOrderBuilder setCustomerName(String customerName) {
        if (customerName.isBlank()) {
            throw new IllegalArgumentException("Please enter the customer name");
        }
        this.customerName = customerName.strip();
        return this;
    }

    public CafeOrderBuilder setNote(String note) {
        this.note = note.strip();
        return this;
    }

    public CafeOrderBuilder setDiscountStrategy(Discount discountStrategy) {
        this.discountStrategy = discountStrategy;
        return this;
    }

    public List<OrderLine> getItems() {
        return List.copyOf(items);
    }

    public CafeOrder build() {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Add at least one item before checkout");
        }
        if (customerName.isEmpty()) {
            throw new IllegalArgumentException("Please enter the customer name");
        }
        return new CafeOrder(items, customerName, note, discountStrategy);
    }
}
