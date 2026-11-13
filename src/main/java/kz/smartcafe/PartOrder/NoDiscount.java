package kz.smartcafe.PartOrder;

public class NoDiscount implements DiscountStrategy {
    @Override
    public long calculateDiscount(long subtotal) {
        return 0;
    }
}
