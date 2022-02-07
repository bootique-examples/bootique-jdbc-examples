# jdbc-app-demo

Provides a runnable app that demonstrates how to work with Bootique JDBC APIs.

## Prerequisites
      
* Java 11 or newer
* Apache Maven
      
## Build the Demo App
      
```bash           
git clone git@github.com:bootique-examples/bootique-jdbc-demo.git
cd bootique-jdbc-demo/jdbc-app-demo
mvn clean package
```
      
## Run the Demo App

Now you can check the options available in your app:
```bash  
java -jar target/jdbc-app-demo-X.XX.jar
```

```  
OPTIONS
      -c yaml_location, --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      -h, --help
           Prints this message.

      -H, --help-config
           Prints information about application modules and their configuration options.

      -i, --insert
           Demo command to insert data.

      -s, --select
           Demo command to select data.

```

Run the `--insert` command to create some test data in the test Derby DB:
```bash
java -jar target/jdbc-app-demo-X.XX.jar -c config.yml -i
```

Check the data in the database using the `--select` command:
```bash    
java -jar target/jdbc-app-demo-X.XX.jar -c config.yml -s
```

Note that per `config.yml` the test database is located under `target/derby/DerbyDatabase`, so running `mvn clean` will 
delete it with all the inserted data.