package kz.smartcafe.builder.order;

public final class OrderLine {
    private final String name;
    private final int unitPrice;
    private final int quantity;

    public OrderLine(String name, int unitPrice, int quantity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name is required");
        }
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("Item price must be positive");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public long subtotal() {
        return (long) unitPrice * quantity;
    }
}
