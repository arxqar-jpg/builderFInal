package kz.smartcafe.PartAitem;

import kz.smartcafe.PartIslam.order.CafeOrder;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private final List<OrderRecord> orders = new ArrayList<>();
    private final List<OrderObserver> observers = new ArrayList<>();
    private int nextId = 1;

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public OrderRecord registerOrder(CafeOrder order) {
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
            System.out.println("Order not found.");
            return false;
        }

        OrderStatus old = found.getStatus();

        boolean allowed =
                (old == OrderStatus.ACCEPTED && next == OrderStatus.PREPARING)
                        || (old == OrderStatus.PREPARING && next == OrderStatus.READY);

        if (!allowed) {
            System.out.println("Invalid status change.");
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