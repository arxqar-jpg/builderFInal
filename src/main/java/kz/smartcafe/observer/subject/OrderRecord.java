package kz.smartcafe.observer.subject;

import kz.smartcafe.builder.order.CafeOrder;


public class OrderRecord {
    private final int id;
    private final CafeOrder cafeOrder;
    private OrderStatus status;

    public OrderRecord(int id, CafeOrder cafeOrder) {
        this.id = id;
        this.cafeOrder = cafeOrder;
        this.status = OrderStatus.ACCEPTED;
    }

    public int getId() {
        return id;
    }

    public CafeOrder getCafeOrder() {
        return cafeOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    void setStatus(OrderStatus status) {
        this.status = status;
    }
}
