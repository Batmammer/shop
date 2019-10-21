# shop
Simple shop in Java 8, Spring Boot 2.2.0 and H2 Database, but can be easly converted to other SQL Database. Usage: Just build with Maven and use like normal Spring Boot/Java application.

To add a new product use (name and price are mandatory fields in json):
curl -d '{"name":"product 1" , "price":"100"}' -H "Content-Type: application/json" -X POST http://localhost:8080/shop/products

To list all products (this will not show deleted products):
curl http://localhost:8080/shop/products

To get a specific product (it will get deleted product also, 3 is sample sku):
curl http://localhost:8080/shop/products/3

To update a product (you can update name and price): 
curl -d '{"name":"product 3a" , "price":"113"}' -H "Content-Type: application/json" -X PUT http://localhost:8080/shop/products/3

To delete a poroduct:
curl -X DELETE http://localhost:8080/shop/products/3

To get list of orders:
curl 'http://localhost:8080/shop/orders?from=2019-01-01T00:00&to=2019-10-22T00:00'

To create an order:
curl -d '{"email":"marcin@mail.comn","products":[{"sku":1,"name":"product 1","price":111},{"sku":2,"name":"product 2","price":111}]}' -H "Content-Type: application/json" -X POST http://localhost:8080/shop/orders

