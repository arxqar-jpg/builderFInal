package kz.smartcafe.observer.contract;

import kz.smartcafe.observer.subject.OrderRecord;
import kz.smartcafe.observer.subject.OrderStatus;

public interface OrderObserver {
    void onStatusChanged(
            OrderRecord order,
            OrderStatus oldStatus,
            OrderStatus newStatus
    );
}