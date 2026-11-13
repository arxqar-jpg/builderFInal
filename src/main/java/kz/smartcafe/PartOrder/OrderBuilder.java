package kz.smartcafe.PartOrder;

import kz.smartcafe.PartDarkhan.Menu;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    private final List<OrderItem> items = new ArrayList<>();
    private String customerName = "";
    private String note = "";
    private DiscountStrategy discountStrategy = new NoDiscount();

    public OrderBuilder addItem(Menu menuItem, int quantity) {
        OrderItem newItem = new OrderItem(menuItem.getName(), menuItem.getPrice(), quantity);
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
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

    public OrderBuilder changeQuantity(int index, int quantity) {
        OrderItem item = items.get(index);
        items.set(index, new OrderItem(item.name(), item.unitPrice(), quantity));
        return this;
    }

    public OrderBuilder removeItem(int index) {
        items.remove(index);
        return this;
    }

    public OrderBuilder setCustomerName(String customerName) {
        if (customerName.isBlank()) {
            throw new IllegalArgumentException("Please enter the customer name");
        }
        this.customerName = customerName.strip();
        return this;
    }

    public OrderBuilder setNote(String note) {
        this.note = note.strip();
        return this;
    }

    public OrderBuilder setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
        return this;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    public Order build() {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Add at least one item before checkout");
        }
        if (customerName.isEmpty()) {
            throw new IllegalArgumentException("Please enter the customer name");
        }
        return new Order(items, customerName, note, discountStrategy);
    }
}
