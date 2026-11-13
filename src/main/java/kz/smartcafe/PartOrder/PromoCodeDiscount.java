package kz.smartcafe.PartOrder;

public class PromoCodeDiscount implements DiscountStrategy {
    private final int percentage;

    public PromoCodeDiscount(String enteredCode, String validCode, int percentage) {
        if (!validCode.equalsIgnoreCase(enteredCode.trim())) {
            throw new IllegalArgumentException("Invalid promo code");
        }
        this.percentage = DiscountStrategy.validatePercentage(percentage);
    }

    @Override
    public long calculateDiscount(long subtotal) {
        return DiscountStrategy.percentageOf(subtotal, percentage);
    }
}
