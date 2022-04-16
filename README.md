## Ports

- 4000 Eureka DiscoveryServer
- 4900 Subscription Service
- 5000 Routes Service
- 6000 Service Terminal
- 7100 Accounts Service
- 7200 Event Service
- 7300 Invoicing Service
- 7800 Collection Service
- 8080 NFC Reader
- 9000 Local Station Broker (Local Account Broker)
- 9100 Local EventStore Broker

## Setup

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

## Using Postman for API checking

1. Download Postman: https://www.postman.com/downloads/
2. Import collection: ./TrIP.postman_collection.json
3. Use the predefined requests
4. np ;)

## Eavesdropping

To get all accounts: GET http://localhost:7100/accounts

To get all eventstores: GET http://localhost:7200/eventstores

To get all invoices: GET http://localhost:7300/invoicing/invoices/all

To get all subscriptions: GET http://localhost:4900/subscription/all

To get all bankcards on NFC Reader: GET http://localhost:8080/nfcreader

To get all routes: GET http://localhost:4500/api/v1/route/all

## Happy path - Travelling

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

3. POST the following: POST http://localhost:7300/invoicing/build_routes
4. You can now see all invoices for user 1 by : GET http://localhost:7300/invoicing/invoices/1
5. To collect the invoices for user 1: GET http://localhost:7800/collect/1

## Happy path - Registering bank card, adding subscription

1. Start:

   - Station Broker
   - Account Service
   - Subscription Service
   - Service Terminal
   - NFC Reader
   - Eureka DiscoveryServer

2. POST the following:
   POST http://localhost:6000/serviceterminal/bankcard/add

   Content-Type: application/json

   ```json
   {
     "expiryDate": "2030-04-05",
     "nfcId": 11111,
     "iban": "NL00INGB1234567890"
   }
   ```

3. To look at possible subscriptions GET the following: GET http://localhost:6000/serviceterminal/subscription/all
4. To add a subscription to your bank card:
   POST http://localhost:6000/serviceterminal/subscription/add/for_user/11111
   Content-Type: application/json

   ```json
   {
     "id": 1,
     "tycoonId": 3,
     "discountPercentage": 40,
     "description": "Valid for routes of Nederlandse Sportvereniging tycoon, 40% off outside of peak hours"
   }
   ```

5. To check your current subscriptions: GET http://localhost:6000/serviceterminal/subscription/all/for_user/11111
