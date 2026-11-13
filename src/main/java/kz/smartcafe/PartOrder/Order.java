package kz.smartcafe.PartOrder;

import java.util.List;

public final class Order {
    private final List<OrderItem> items;
    private final String customerName;
    private final String note;
    private final long subtotal;
    private final long discount;

    Order(List<OrderItem> items, String customerName, String note, DiscountStrategy discountStrategy) {
        this.items = List.copyOf(items);
        this.customerName = customerName;
        this.note = note;
        this.subtotal = this.items.stream().mapToLong(OrderItem::subtotal).sum();
        this.discount = discountStrategy.calculateDiscount(subtotal);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getNote() {
        return note;
    }

    public long getSubtotal() {
        return subtotal;
    }

    public long getDiscount() {
        return discount;
    }

    public long getTotal() {
        return subtotal - discount;
    }
}
