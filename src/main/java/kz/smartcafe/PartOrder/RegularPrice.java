package kz.smartcafe.PartOrder;

public class RegularPrice implements Discount {
    @Override
    public long calculateDiscount(long subtotal) {
        return 0;
    }
}
