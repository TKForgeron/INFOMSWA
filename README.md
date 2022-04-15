## Ports

4000 Eureka DiscoveryServer
4900 Subscription Service
5000 Routes Service
6000 Service Terminal
7100 Accounts Service
7200 Event Service
7300 Billing Service
7800 Collection Service
8080 NFC Reader
9000 Local Station Broker (Local Account Broker)
9100 Local EventStore Broker

Please do the following things before running:
    - Create the following databases:
        - AccountDB 
        - EventQueue
        - AccountDB_Broker
        - AccountDB_Service
        - Subscriptions
        - EventStore
        - Routes
        - PassengerBillsDB

    - For each of the Serviceses, add your username and password in the application.properties file in the corresponding microservice.

To add an account:
    - Start:
        - Service Terminal
        - Local Station Broker
        - NFC Reader
        - Account Service
        - Eureka DiscoveryServer
    - Example POST:
        POST http://localhost:6000/api/v1/serviceterminal/bankcard/add
        Content-Type: application/json

        {
        "uuid":7,
        "expiryDate": "2030-01-01",
        "nfcId": 123910,
        "iban": "NL51RABO012310943891"
        } 

To get all accounts: GET http://localhost:7100/accounts

For checking in and out, and calculating the price afterwards:
    - Start:
        - NFC Reader
        - Local EventStore Broker
        - Event Service
        - Billing Service
        - Collection Service
        - Route Service
        - Account Service
        - Subscription Service
        - Eureka DiscoveryServer
    - First, POST the following two messages:
        POST http://localhost:8080/api/v1/nfcreader/eventstore/Apeldoorn
        Content-Type: application/json

        {"uuid":1,"expiryDate":"2050-04-05","nfcId":12345}

        POST http://localhost:8080/api/v1/nfcreader/eventstore/Barneveld
        Content-Type: application/json
        
        {"uuid":1,"expiryDate":"2050-04-05","nfcId":12345}

    - Afterwards, POST the following: POST http://localhost:7300/build_routes
    - The route, including the price, will now be printed in the Billing Service logs.
