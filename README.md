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
