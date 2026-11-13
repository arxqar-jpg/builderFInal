package kz.smartcafe.PartOrder;

public interface DiscountStrategy {
    long calculateDiscount(long subtotal);

    static int validatePercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100 percent");
        }
        return percentage;
    }

    static long percentageOf(long subtotal, int percentage) {
        return subtotal / 100 * percentage + subtotal % 100 * percentage / 100;
    }
}
