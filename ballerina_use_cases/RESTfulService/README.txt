
Creating Service Archive
========================
/Users/kasun/development/deployment/ballerina/ballerina-tools-0.87/bin/ballerina build service RESTfulService/ecommerce -o EComUC.bsz


Service
=======
./ballerina run service /Users/kasun/development/source/git/kasun04/rnd/ballerina_use_cases/RESTfulService/EComUC.bsz



Client
======

curl -X GET "http://localhost:9090/ecommerceservice/products/123000"

curl -X POST -d '{ "Product": { "ID": "123456", "Name": "XYZ", "Description": "Sample product."}}'  "http://localhost:9090/ecommerceservice/products"



curl -X GET "http://localhost:9090/ecommerceservice/orders"

curl -X POST -d '{ "Order": { "ID": "111999", "Name": "XYZ", "Description": "Sample order."}}'  "http://localhost:9090/ecommerceservice/orders"



curl -X GET "http://localhost:9090/ecommerceservice/customers"

curl -X POST -d '{"Customer": {"ID": "987654", "Name": "ABC PQR","Description": "Sample Customer."}}'  "http://localhost:9090/ecommerceservice/customers"