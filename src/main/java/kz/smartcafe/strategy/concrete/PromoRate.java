package kz.smartcafe.strategy.concrete;

import kz.smartcafe.strategy.Discount;

public class PromoRate implements Discount {
    private final int percentage;

    public PromoRate(String enteredCode, String validCode, int percentage) {
        if (enteredCode == null || validCode == null
                || !validCode.equalsIgnoreCase(enteredCode.trim())) {
            throw new IllegalArgumentException("Invalid promo code");
        }
        this.percentage = Discount.validatePercentage(percentage);
    }

    @Override
    public long calculateDiscount(long subtotal) {
        return Discount.percentageOf(subtotal, percentage);
    }
}
