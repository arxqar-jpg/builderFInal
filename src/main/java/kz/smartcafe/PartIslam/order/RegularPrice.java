package kz.smartcafe.PartIslam.order;

public class RegularPrice implements Discount {
    @Override
    public long calculateDiscount(long subtotal) {
        return 0;
    }
}
