## Ports

- 4000 Eureka DiscoveryServer
- 4900 Subscription Service
- 5000 Routes Service
- 6000 Service Terminal
- 7100 Accounts Service
- 7200 Event Service
<<<<<<< HEAD
- 7300 Invoicing Service
=======
- 7300 Billing Service
>>>>>>> 12a1a29b98173231f598197a4201cdec66f3f8a9
- 7800 Collection Service
- 8080 NFC Reader
- 9000 Local Station Broker (Local Account Broker)
- 9100 Local EventStore Broker

## Setup
<<<<<<< HEAD

Please do the following things before running:

1. Create the following databases:
   - AccountDB
   - EventQueue
   - AccountDB_Broker
   - AccountDB_Service
   - Subscriptions
   - EventStore
   - Routes
   - PassengerBillsDB
2. For each of the Services, add your username and password in the application.properties file in the corresponding microservice.

## Eavesdropping

To get all accounts: GET http://localhost:7100/accounts

To get all eventstores: GET http://localhost:7200/eventstores

To get all invoices: GET http://localhost:7300/invoicing/invoices/all

To get all subscriptions: GET http://localhost:4900/subscription/all

## Happy path

For checking in and out, and calculating the price afterwards:

1. Start:

   - NFC Reader
   - Local EventStore Broker
   - Event Service
   - Invoicing Service
   - Collection Service
   - Route Service
   - Account Service
   - Subscription Service
   - Eureka DiscoveryServer

   2. First, POST the following two messages:
      POST http://localhost:8080/api/v1/nfcreader/eventstore/Apeldoorn

   Content-Type: application/json

   ```json
   {
     "uuid": 1,
     "expiryDate": "2025-04-05",
     "nfcId": 12345
   }
   ```

   POST http://localhost:8080/api/v1/nfcreader/eventstore/Barneveld

   Content-Type: application/json

   ```json
   {
     "uuid": 1,
     "expiryDate": "2025-04-05",
     "nfcId": 12345
   }
   ```

2. Afterwards, POST the following: POST http://localhost:7300/invoicing/build_routes
3. The route, including the price, will now be printed in the Invoicing Service logs.
=======
Please do the following things before running:
    1. Create the following databases:
        - AccountDB 
        - EventQueue
        - AccountDB_Broker
        - AccountDB_Service
        - Subscriptions
        - EventStore
        - Routes
        - PassengerBillsDB
    2. For each of the Services, add your username and password in the application.properties file in the corresponding microservice.

## Eavesdropping
To get all accounts: GET http://localhost:7100/accounts
To get all eventstores: GET http://localhost:7200/eventstores

## Happy path
For checking in and out, and calculating the price afterwards:
    1. Start:
        - NFC Reader
        - Local EventStore Broker
        - Event Service
        - Billing Service
        - Collection Service
        - Route Service
        - Account Service
        - Subscription Service
        - Eureka DiscoveryServer
     2. First, POST the following two messages:
        POST http://localhost:8080/api/v1/nfcreader/eventstore/Apeldoorn
        
        Content-Type: application/json
```json
        {"uuid":1,"expiryDate":"2050-04-05","nfcId":12345}
       ``` 
        POST http://localhost:8080/api/v1/nfcreader/eventstore/Barneveld
        
        Content-Type: application/json
        ```json
        {"uuid":1,"expiryDate":"2050-04-05","nfcId":12345}
        ```
    3. Afterwards, POST the following: POST http://localhost:7300/build_routes
    4. The route, including the price, will now be printed in the Billing Service logs.
>>>>>>> 12a1a29b98173231f598197a4201cdec66f3f8a9
