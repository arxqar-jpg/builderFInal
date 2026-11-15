package kz.smartcafe.strategy.concrete;

import kz.smartcafe.strategy.Discount;

public class StudentRate implements Discount {
    private final int percentage;

    public StudentRate(int percentage) {
        this.percentage = Discount.validatePercentage(percentage);
    }

    @Override
    public long calculateDiscount(long subtotal) {
        return Discount.percentageOf(subtotal, percentage);
    }
}
