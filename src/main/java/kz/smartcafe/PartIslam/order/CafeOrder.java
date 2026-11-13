package kz.smartcafe.PartIslam.order;

import java.util.List;

public final class CafeOrder {
    private final List<OrderLine> items;
    private final String customerName;
    private final String note;
    private final long subtotal;
    private final long discount;

    CafeOrder(List<OrderLine> items, String customerName, String note, Discount discountStrategy) {
        this.items = List.copyOf(items);
        this.customerName = customerName;
        this.note = note;
        this.subtotal = this.items.stream().mapToLong(OrderLine::subtotal).sum();
        this.discount = discountStrategy.calculateDiscount(subtotal);
    }

    public List<OrderLine> getItems() {
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
