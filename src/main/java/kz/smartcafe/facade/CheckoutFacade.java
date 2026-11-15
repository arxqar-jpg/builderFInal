package kz.smartcafe.facade;

import java.util.List;

import kz.smartcafe.builder.order.CafeOrder;
import kz.smartcafe.builder.order.CafeOrderBuilder;
import kz.smartcafe.observer.subject.OrderManager;
import kz.smartcafe.observer.subject.OrderRecord;
import kz.smartcafe.observer.subject.OrderStatus;

public class CheckoutFacade {

    private final OrderManager orderManager;

    public CheckoutFacade(OrderManager orderManager) {
        if (orderManager == null) {
            throw new IllegalArgumentException("Order manager is required");
        }
        this.orderManager = orderManager;
    }

    public OrderRecord placeOrder(CafeOrderBuilder builder) {
        if (builder == null) {
            throw new IllegalArgumentException("Order builder is required");
        }

        CafeOrder order = builder.build();
        return orderManager.registerOrder(order);
    }

    public List<OrderRecord> getOrders() {
        return orderManager.getOrders();
    }

    public boolean changeStatus(int id, OrderStatus status) {
        return orderManager.changeStatus(id, status);
    }
}
