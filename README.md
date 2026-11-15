# Smart Cafe

Java 17 console application for cafe orders.

## Run

Open the project in IntelliJ IDEA, select JDK 17 or newer and run
`kz.smartcafe.Main`.

With Maven installed:

```text
mvn compile
java -cp target/classes kz.smartcafe.Main
```

## Patterns

| Package | Pattern | Purpose |
|---|---|---|
| factory | Factory Method | Create pizza, burger and drink objects |
| builder | Builder | Assemble an order with items, customer and note |
| decorator | Decorator | Add cheese and sauce to food |
| strategy | Strategy | Choose regular, student or promo pricing |
| observer | Observer | Notify customer and kitchen about order status |
| facade | Facade | Build and register orders, list orders and change status |

The three pattern types are covered: creational, structural and behavioral.
`Main` connects the parts and runs the console menu.

## Team responsibilities

| Member | Service | Patterns | Main classes |
|---|---|---|---|
| Darkhan | Menu Service | Factory Method, Decorator | MenuService, MenuFactory and concrete factories, menu products, MenuDecorator and toppings |
| Islam | Order Service | Builder, Strategy | OrderConsole, CafeOrderBuilder, CafeOrder, OrderLine, Discount and discount strategies |
| Aitemir | Checkout & Notification Service | Facade, Observer | CheckoutFacade, StaffOrderConsole, OrderManager, OrderRecord, OrderStatus, OrderObserver and notifications |

Darkhan's menu products are used by Islam's order builder. Islam's service
handles item quantities, removal, customer details and discounts. Aitemir's
checkout service builds and registers the prepared order, shows it to staff
and sends notifications when its status changes.

`OrderConsole` is in `builder.console`; `StaffOrderConsole` is in
`facade.console`. `Main` and `ConsoleInput` connect the services and provide
shared console input.

Original implementation commits:

- Darkhan: [Factory Method](https://github.com/arxqar-jpg/builderFInal/commit/843e59c74454fc4ae091fdf7f2009a40c19325ee), [Decorator](https://github.com/arxqar-jpg/builderFInal/commit/ea09842b7dea1a6b51d706911ab4b98c0e908495).
- Islam: [Builder, Strategy and order console](https://github.com/arxqar-jpg/builderFInal/commit/a47ec0e06f2f88086349593f3866add7c9aa2b83).
- Aitemir: [Facade, Observer and staff panel](https://github.com/arxqar-jpg/builderFInal/commit/be44dc796f0d329e53bece775aeb739359320956).

The later [package and workflow update](https://github.com/arxqar-jpg/builderFInal/commit/261a0bbf303ea2230cce00957e716c0540006c06)
reorganized the existing implementations into six pattern packages and fixed
the order flow. The original implementation history is preserved.

## Example

Choose pizza, add cheese and sauce, set quantity to 2 and check out.
The student discount is 10%: subtotal 5900 KZT, discount 590 KZT,
total 5310 KZT. The promo code `CAFE15` gives a 15% discount instead.

In the staff panel, move the order through ACCEPTED, PREPARING and READY.
Choose Back to start another order with an empty cart.
