package kz.smartcafe.PartAitem;

public interface OrderObserver {
    void onStatusChanged(
            OrderRecord order,
            OrderStatus oldStatus,
            OrderStatus newStatus
    );
}