package kz.smartcafe.builder.order;

import kz.smartcafe.strategy.Discount;

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
        long sum = 0;
        for (OrderLine item : this.items) {
            sum += item.subtotal();
        }
        this.subtotal = sum;

        long calculatedDiscount = discountStrategy.calculateDiscount(subtotal);
        if (calculatedDiscount < 0 || calculatedDiscount > subtotal) {
            throw new IllegalArgumentException("Invalid discount amount");
        }
        this.discount = calculatedDiscount;
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
