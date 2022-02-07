# jdbc-test-demo-testcontainer

Provides example JUnit5 tests using Bootique JDBC test API.

## Prerequisites

* Java 11 or newer
* Apache Maven
* Docker

## Run the demo tests

You need to have running Docker then you can run tests in bootique-jdbc-test demo module using TestContainer with Docker:
```bash  
git clone git@github.com:bootique-examples/bootique-jdbc-demo.git
cd jdbc-test-demo-testcontainer
mvn clean test
```