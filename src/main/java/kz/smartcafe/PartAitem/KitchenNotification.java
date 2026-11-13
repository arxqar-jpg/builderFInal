package kz.smartcafe.PartAitem;

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