h1. Super simple stocks

Super simple stocks is an application to manage trades on a set of stocks and it's a technical test as part of the hiring process for a very important company.

h2. How to run:
Build the application with maven: mvn install
run app:
* cd RestApi
* mvn spring-boot:run

Application will start on http://localhost:8080
example urls:
* add trade: http://localhost:8080/addTrade?stockSymbol=GIN&tradeDirection=BUY&price=12&quantity=2
* get stock information for a given stock: http://localhost:8080/calculateStockInformation?stockSymbol=GIN
* get stock index information: http://localhost:8080/calculateStockIndexInformation

Supported stockSymbols and related values can be found in the exercise document.

h2. A few words about the project in this application
The main design principle was that create code which is flexible any part can be replaced easily. For this interfaces and implementations has been placed in separate projects. By proof of concept DataCache and DataProvider has been created.
The project is flexible enough to implement new stock types.

The Rest API project can be build with DataCache and DataProvider project as well. (DataProvider can be enabled with dbProfile no Stock will be initialized)

h2. Testing
Every business logic has been tested by unit test. Some integration tests has been written for the database connection.

h2. Improvement possibilities
* Logging should be added
* notification chain can be created when a trade arrived.
* new layer can be created which will add trade to database and to the cache 
* HTML GUI can be create with some template engine or with some JavaScript framework.
 