## Docker

Docker Kafka configuration:

https://docs.confluent.io/current/installation/docker/config-reference.html

Running Zookeeper in Production:

https://docs.confluent.io/current/zookeeper/deployment.html

## Kafka

````
kafka-console-producer --broker-list localhost:9092 --topic current_cart --property parse.key=true --property key.separator=:
2222:{"clientId":"2222","item":{"sku":"AAA111","quantity":1}} 
````


