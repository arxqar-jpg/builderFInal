package kz.smartcafe.observer.notification;

import kz.smartcafe.observer.contract.OrderObserver;
import kz.smartcafe.observer.subject.OrderRecord;
import kz.smartcafe.observer.subject.OrderStatus;

public class KitchenNotification implements OrderObserver {

    @Override
    public void onStatusChanged(
            OrderRecord order,
            OrderStatus oldStatus,
            OrderStatus newStatus
    ) {
        System.out.println(
                "[KITCHEN NOTIFICATION] Order #"
                        + order.getId()
                        + " changed: "
                        + oldStatus
                        + " -> "
                        + newStatus
        );
    }
}