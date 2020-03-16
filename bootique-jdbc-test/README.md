[![Build Status](https://travis-ci.org/bootique-examples/bootique-jdbc-demo.svg)](https://travis-ci.org/bootique-examples/bootique-jdbc-demo)
# bootique-jdbc-test-demo

How to work with JDBC data stores integrated for [Bootique](http://bootique.io) app. 
   
*For additional help/questions about this example send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*
   
## Prerequisites
      
    * Java 1.8 or newer.
    * Apache Maven.
    * Docker
      
## Run the Bootique-jdbc-test Demo

You can run tests in bootique-jdbc-test demo module using standard Derby DB:
```bash  
cd bootique-jdbc-test && mvn test
```

All tests passed successfully:   
```    
...
[INFO] Results:
[INFO] 
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

```
## Bootique-jdbc-test can work with testcontainers
If you want to switch test database from derby to another, then only change "jdbcUrl" field in dataSource.yml
to testcontainers url and add maven dependencies. For example:
```
jdbc:
  datasource:
    jdbcUrl: jdbc:tc:postgresql://localhost/test
```
pom.xml
```
...
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <version>1.13.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.10</version>
        </dependency>
...
```

Then run maven tests
```
mvn test
```
You can see how docker starts container, and run tests
```
...
INFO  [2020-03-16 08:59:32,998] main o.t.DockerClientFactory: Connected to docker: 
  Server Version: 19.03.8
  API Version: 1.40
  Operating System: Docker Desktop
  Total Memory: 1989 MB
INFO  [2020-03-16 08:59:33,119] main o.t.u.RegistryAuthLocator: Credential helper/store (docker-credential-desktop) does not have credentials for quay.io
INFO  [2020-03-16 08:59:33,619] main o.t.DockerClientFactory: Ryuk started - will monitor and terminate Testcontainers containers on JVM exit
INFO  [2020-03-16 08:59:33,619] main o.t.DockerClientFactory: Checking the system...
INFO  [2020-03-16 08:59:33,620] main o.t.DockerClientFactory: ?? Docker server version should be at least 1.6.0
INFO  [2020-03-16 08:59:33,724] main o.t.DockerClientFactory: ?? Docker environment should have more than 2GB free disk space
INFO  [2020-03-16 08:59:33,741] main d.6.12]: Creating container for image: postgres:9.6.12
...
```
All tests passed successfully:   
```    
...
[INFO] Results:
[INFO] 
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
...
```


