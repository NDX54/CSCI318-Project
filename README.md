# CSCI318 - Project C - Group 7 - README

# TODO - Stream processor for Analytics Microservice

## Members

- **Myunggyun Yu - 7539629**
- **De Hou Kok - 7753986**
- **Jared Juangco - 7912249**

## Overview

This README file shows all the commands for each use case that is implemented in the three implemented microservices.

## Apache Kafka

To run this project, you need to install and setup Apache Kafka. Guides for the respective OS will be given.

The latest version, Kafka 3.5.1 with Scala version 2.13, works with the project.

### Linux and macOS

Download a binary package of Apache Kafka (e.g., `kafka_2.13-3.5.1.tgz`) from https://kafka.apache.org/downloads and unzip it. In the Terminal, `cd` to the unzip folder, and start Kakfa with the following commands (each in a separate Terminal session):

```shell
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
```

```shell
./bin/kafka-server-start.sh ./config/server.properties
```

### Windows

Download a binary package of Apache Kafka (e.g., `kafka_2.13-2.8.0.tgz`) from https://kafka.apache.org/downloads and unzip it to a directory, e.g., `C:\kafka`—Windows does not like a complex path name (!).

Use the following two commands in the Windows CMD (one in each window) to start Kafka:

```shell
C:\kafka\bin\windows\zookeeper-server-start.bat C:\kafka\config\zookeeper.properties
```

```shell
C:\kafka\bin\windows\kafka-server-start.bat C:\kafka\config\server.properties
```

### Troubleshooting

If you cannot start Kafka, try to clean up data in the Kafka topics to start over. 
For this purpose, in Linux/macOS, delete the folders `/tmp/zookeeper`, `/tmp/kafka-logs` and `/tmp/kafka-streams` (if any). 
In Windows, delete the folders `C:\tmp\zookeeper`, `C:\tmp\kafka-logs` and `C:\kafka\kafka-streams` (if any).

### Viewing Kafka Topics

#### Linux / macOS

```shell
./bin/kafka-topics.sh --bootstrap-server=localhost:9092 --list
```

#### Windows

```shell
C:\kafka\bin\windows\kafka-topics.bat --bootstrap-server=localhost:9092 --list
```

You should see the topic names `customerCreations`, `customerUpdates`, `orderCreations`, `orderDeletions`, `productCreations`, `productDeletions`, and `productUpdates`. You can consume data in any of the topics:

#### Linux / macOS

```shell
./bin/windows/kafka-console-consumer.bat --bootstrap-server=localhost:9092 --topic productCreations --from-beginning
```
#### Windows

```shell
c:\kafka\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic productCreations --from-beginning
```

## Note!
CustomerID, ProductID, and OrderID are randomly generated. Please copy the respective IDs to run the commands!

## Customer Account Service

### Create a Customer

```shell
curl -X POST -H "Content-Type: application/json" -d "{\"companyName\": \"Example Company\",\"address\": {\"streetAddress\": \"123 Main Street\",\"city\": \"Cityville\",\"postalCode\": \"12345\",\"country\": \"Countryland\"},\"email\": {\"address\":\"customer@example.com\"},\"phone\": {\"number\": \"123-456-7890\"}}" http://localhost:8081/customers
```

### Create a Contact

```shell
curl -X POST -H "Content-Type: application/json" -d "{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"phone\": \"999-999-9999\", \"position\": \"Manager\"}" http://localhost:8081/contacts/?customerId=1
```

### Update a Customer

```shell
curl -X PUT -H "Content-Type: application/json" -d "{\"companyName\": \"Example Company\",\"address\": {\"streetAddress\": \"123 Main Street\",\"city\": \"Cityville\",\"postalCode\": \"12345\",\"country\": \"Countryland\"},\"email\": {\"address\":\"customer@example.com\"},\"phone\": {\"number\": \"123-456-7890\"}}" http://localhost:8081/customers/update/CUSTOMER_ID_HERE
```

### Update a Contact

For updating the contact, use the Long ID for both CustomerID and ContactID

```shell
curl -X PUT -H "Content-Type: application/json" -d "{\"name\": \"Mari Doe\", \"email\": \"mardoe@example.com\", \"phone\": \"992349523952539\", \"position\": \"Doggerr\"}" http://localhost:8081/customers/CUSTOMER_ID_HERE/contacts/CONTACT_ID_HERE
```

### View Customers

```
curl http://localhost:8081/customers/CUSTOMER_ID_TO_LOOKUP_HERE
```

## Procurement Service

### Create Product

```shell
curl -X POST -H "Content-Type: application/json" -d "{\"description\":\"Ratatouille\",\"comment\":\"Yun food\",\"productCategory\":\"Foods\",\"name\":\"Ratatoy\",\"price\":9999.99}" http://localhost:8082/products
curl -X POST -H "Content-Type: application/json" -d "{\"description\":\"XLR Microphone\",\"comment\":\"WOW\",\"productCategory\":\"Audio\",\"name\":\"RTMic\",\"price\":1299.99}" http://localhost:8082/products
```

### Update a Product

```shell
curl -X PUT -H "Content-Type: application/json" -d "{\"productCategory\":\"Tools\",\"name\":\"Drill\",\"price\":339.99}" http://localhost:8082/products/update/PRODUCT_ID_TO_UPDATE_HERE
curl -X PUT -H "Content-Type: application/json" -d "{\"description\":\"High-quality drill\",\"comment\":\"Drill it in\",\"productCategory\":\"Tools\",\"name\":\"Drill\",\"price\":339.99}" http://localhost:8082/products/PRODUCT_ID_TO_UPDATE_HERE
```

### Delete a Product

```shell
curl -X DELETE http://localhost:8082/products/delete/PRODUCT_ID_TO_DELETE_HERE
```

### Lookup All Products

```shell
curl http://localhost:8082/products
```

### Lookup Specific Product

```shell
curl http://localhost:8082/products/get/PRODUCT_ID_TO_LOOKUP_HERE
```

## Sales Service

### Create Order

```shell
curl -X POST -H "Content-Type: application/json" -d "{\"supplier\":\"Drill it in\",\"quantity\":300}" http://localhost:8089/sales/create-order/CUSTOMER_ID_HERE/PRODUCT_ID_HERE
```

### Update Order

```shell
CURL -X PUT -H "Content-Type: application/json" -d "{\"supplier\":\"Cereal Killers\",\"quantity\":300}" http://localhost:8089/sales/update-order/ORDER_ID_HERE/product-id/NEW_PRODUCT_ID_HERE
```

### Delete Order

```shell
curl -X DELETE http://localhost:8089/sales/delete/ORDER_ID_TO_DELETE_HERE
```

### Lookup All Orders

```shell
curl -X GET http://localhost:8089
```