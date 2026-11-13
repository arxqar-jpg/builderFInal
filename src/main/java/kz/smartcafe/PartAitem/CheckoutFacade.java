package kz.smartcafe.PartAitem;

import kz.smartcafe.PartIslam.order.CafeOrder;
import kz.smartcafe.PartIslam.order.OrderLine;

public class CheckoutFacade {

    private final OrderManager orderManager;

    public CheckoutFacade(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public OrderRecord placeOrder(CafeOrder order) {
        validateOrder(order);

        OrderRecord saved = orderManager.registerOrder(order);

        System.out.println("CheckoutFacade: order successfully registered.");

        return saved;
    }

    private void validateOrder(CafeOrder order) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order cannot be empty.");
        }

        if (order.getCustomerName() == null
                || order.getCustomerName().isBlank()) {
            throw new IllegalArgumentException("Customer name is required.");
        }

        for (OrderLine item : order.getItems()) {
            if (item.name() == null
                    || item.name().isBlank()
                    || item.unitPrice() <= 0
                    || item.quantity() < 1) {
                throw new IllegalArgumentException("Invalid order item.");
            }
        }

        if (order.getTotal() < 0) {
            throw new IllegalArgumentException("Invalid total.");
        }
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }
}