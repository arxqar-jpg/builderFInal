package kz.smartcafe.PartOrder;

public class StudentDiscount implements DiscountStrategy {
    private final int percentage;

    public StudentDiscount(int percentage) {
        this.percentage = DiscountStrategy.validatePercentage(percentage);
    }

    @Override
    public long calculateDiscount(long subtotal) {
        return DiscountStrategy.percentageOf(subtotal, percentage);
    }
}
