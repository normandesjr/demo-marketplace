### First Json Stream

````json
{
  "id": 10,
  "name": "Pedro Silva",
  "age": 25
}
````

````
kafka-console-producer --broker-list localhost:9092 --topic first-json-input --property parse.key=true --property key.separator=:
1:{"name":"Pedro Souza","age":25,"id":1}
````

### First Cart Example Stream

````json
{
  "customerId": 1111,
  "item": {
    "sku": "1010",
    "quantity": 1
  }
}
````

````
kafka-console-producer --broker-list localhost:9092 --topic first-cart-example-input --property parse.key=true --property key.separator=:
1111:{"customerId":1111,"item":{"sku":"1010","quantity":1}}
2222:{"customerId":2222,"item":{"sku":"1010","quantity":1}}
````


###  Real Application

The REST payload will be:

````json
{
  "customerId": 1111,
  "item": {
    "sku": "1010",
    "quantity": 1
  }
}
````

Then we could generate the UUID, get the product price and save it as topic:

topic: cart_topic
key: 14ab-48fc-49d4
value: {
         "customerId": 1111,
         "item": {
            "sku": "1010",
            "quantity": 1,
            "total": 20000
          }
       }

I guess I could add the item from cart to items_topic to make my life easier during aggregate.

Add some item after create the cart:

topic: item_topic
key: 14ab-48fc-49d4
value: {
         "item": {
            "sku": "1010",
            "quantity": 3,
            "total": 20000
          }
       }


key: 14ab-48fc-49d4
value: {
         "item": {
            "sku": "2020",
            "quantity": 2,
            "total": 20000
          }
       }

DynamoDB;
  PK                 SK                     DATA                     ATTRIBUTES
 CUSTOMER_1111    CART_14ab-48fc-49d4      PENDING_<TIME>          TOTAL: 2000
 CUSTOMER_1111    CART_9898-AFGG-3849      DONE_<TIME>             TOTAL: 3500
 ITEM_<SKU>       CART_14ab-48fc-49d4      ITEM_<SKU>              QUANTITY: 2     UNIT_VALUE: 1000
 ORDER_8989       CUSTOMER_1111            CART_14ab-48fc-49d4     

GSI:
      SK                           DATA
  CART_14ab-48fc-49d4        PENDING_<TIME>
  CART_9898-AFGG-3849        DONE_<TIME>
  CART_14ab-48fc-49d4        ITEM_<SKU>
  CUSTOMER_1111              CART_14ab-48fc-49d4
  

-- Checkout

/carts/<id>/checkout

To topic I will send:

Key: <cart_id> : 14ab-48fc-49d4
Value: { "paymentMethod": "CREDIT_CARD" }


-- order_topic



