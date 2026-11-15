package kz.smartcafe.strategy.concrete;

import kz.smartcafe.strategy.Discount;

public class RegularPrice implements Discount {
    @Override
    public long calculateDiscount(long subtotal) {
        return 0;
    }
}
