package kz.smartcafe.observer.subject;

import kz.smartcafe.builder.order.CafeOrder;
import kz.smartcafe.observer.contract.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private final List<OrderRecord> orders = new ArrayList<>();
    private final List<OrderObserver> observers = new ArrayList<>();
    private int nextId = 1;

    public void addObserver(OrderObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer is required");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public OrderRecord registerOrder(CafeOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order is required");
        }
        OrderRecord record = new OrderRecord(nextId++, order);

        orders.add(record);

        notifyObservers(record, null, OrderStatus.ACCEPTED);

        return record;
    }

    public List<OrderRecord> getOrders() {
        return List.copyOf(orders);
    }

    public boolean changeStatus(int id, OrderStatus next) {
        OrderRecord found = null;

        for (OrderRecord order : orders) {
            if (order.getId() == id) {
                found = order;
                break;
            }
        }

        if (found == null) {
            return false;
        }

        OrderStatus old = found.getStatus();

        boolean allowed =
                (old == OrderStatus.ACCEPTED && next == OrderStatus.PREPARING)
                        || (old == OrderStatus.PREPARING && next == OrderStatus.READY);

        if (!allowed) {
            return false;
        }

        found.setStatus(next);

        notifyObservers(found, old, next);

        return true;
    }

    private void notifyObservers(
            OrderRecord order,
            OrderStatus oldStatus,
            OrderStatus newStatus
    ) {
        for (OrderObserver observer : observers) {
            observer.onStatusChanged(order, oldStatus, newStatus);
        }
    }
}
