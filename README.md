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

## Example

Choose pizza, add cheese and sauce, set quantity to 2 and check out.
The student discount is 10%: subtotal 5900 KZT, discount 590 KZT,
total 5310 KZT. The promo code `CAFE15` gives a 15% discount instead.

In the staff panel, move the order through ACCEPTED, PREPARING and READY.
Choose Back to start another order with an empty cart.
