# account-management

## Swagger documentation

The project contains 2 API endpoints:

      get /api/v1/clients/{id}
      post /api/v1/accounts
      
The details of the endpoints can be found in the swagger documentation. To get it, run the app on your local machine and go to the following URL:

      http://localhost:8080/swagger-ui/index.html

Use your favorite API testing tool to make the calls to the API

## Tests

All the tests are located in the folder /src/test/java, you can run them using your favorite IDE

## Bootstrap data

Inside the ClientConfig, I've added some clients to be created when the app is being launched. To test the /api/v1/clients/{id}, you can either use an id of 1, 2 or 3. To test the /api/v1/accounts, you can use the following customerIds as payload: "ASE1234", "ASE4321", "ASE5678"
