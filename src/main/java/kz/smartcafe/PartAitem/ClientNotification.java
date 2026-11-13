package kz.smartcafe.PartAitem;

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