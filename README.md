# CSCI318 - Project B - Group 7 - README
## Members

- **Myunggyun Yu - 7539629**
- **De Hou Kok - 7753986**
- **Jared Juangco - 7912249**

## Overview

This README file shows all the commands for each use case that is implemented in the three implemented microservices.

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