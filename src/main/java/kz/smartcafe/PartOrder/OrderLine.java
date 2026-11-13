package kz.smartcafe.PartOrder;

public record OrderLine(String name, int unitPrice, int quantity) {
    public OrderLine {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
    }

    public long subtotal() {
        return (long) unitPrice * quantity;
    }
}
