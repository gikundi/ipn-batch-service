# ipn-batch-service
Service that reads data from the database, updates it and sends notifications to a remote server

## Installation
Run the command below to install maven dependencies

 ```bash
 mvn install -DskipTests.
  ```
## Up and Running

 ```bash
 cd /target.
  ```
  
  ```bash
 java -jar portal-apis-0.0.1-SNAPSHOT.jar
  ```
  
  ## Testing
To test, configure your database credentials on application properties and remote server url, when you run the application it creates a customers table.
Seed some data with status 0 and watch the cron job run in the background and send the updated data in your remote url
