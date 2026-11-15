package kz.smartcafe.builder.order;

import kz.smartcafe.factory.product.Menu;
import kz.smartcafe.strategy.Discount;
import kz.smartcafe.strategy.concrete.RegularPrice;

import java.util.ArrayList;
import java.util.List;

public class CafeOrderBuilder {
    private final List<OrderLine> items = new ArrayList<>();
    private String customerName = "";
    private String note = "";
    private Discount discountStrategy = new RegularPrice();

    public CafeOrderBuilder addItem(Menu menuItem, int quantity) {
        if (menuItem == null) {
            throw new IllegalArgumentException("Choose an item first");
        }
        OrderLine newItem = new OrderLine(menuItem.getName(), menuItem.getPrice(), quantity);
        for (int i = 0; i < items.size(); i++) {
            OrderLine item = items.get(i);
            if (item.getName().equals(newItem.getName()) && item.getUnitPrice() == newItem.getUnitPrice()) {
                if (quantity > Integer.MAX_VALUE - item.getQuantity()) {
                    throw new IllegalArgumentException("Quantity is too large");
                }
                return changeQuantity(i, item.getQuantity() + quantity);
            }
        }
        items.add(newItem);
        return this;
    }

    public CafeOrderBuilder changeQuantity(int index, int quantity) {
        OrderLine item = items.get(index);
        items.set(index, new OrderLine(item.getName(), item.getUnitPrice(), quantity));
        return this;
    }

    public CafeOrderBuilder removeItem(int index) {
        items.remove(index);
        return this;
    }

    public CafeOrderBuilder setCustomerName(String customerName) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Please enter the customer name");
        }
        this.customerName = customerName.strip();
        return this;
    }

    public CafeOrderBuilder setNote(String note) {
        this.note = note == null ? "" : note.strip();
        return this;
    }

    public CafeOrderBuilder setDiscountStrategy(Discount discountStrategy) {
        if (discountStrategy == null) {
            throw new IllegalArgumentException("Discount strategy is required");
        }
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
