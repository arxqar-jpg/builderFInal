package kz.smartcafe.observer.notification;

import kz.smartcafe.observer.contract.OrderObserver;
import kz.smartcafe.observer.subject.OrderRecord;
import kz.smartcafe.observer.subject.OrderStatus;

public class ClientNotification implements OrderObserver {

    @Override
    public void onStatusChanged(
            OrderRecord order,
            OrderStatus oldStatus,
            OrderStatus newStatus
    ) {
        System.out.println(
                "[CLIENT NOTIFICATION] Hello, "
                        + order.getCafeOrder().getCustomerName()
                        + "! Order #"
                        + order.getId()
                        + " status: "
                        + newStatus
        );
    }
}