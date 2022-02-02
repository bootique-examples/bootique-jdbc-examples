# jdbc-app-demo

Provides a runnable app that demonstrates how to work with Bootique JDBC APIs.

## Prerequisites
      
* Java 11 or newer
* Apache Maven
      
## Build the Demo App
      
```bash           
git clone git@github.com:bootique-examples/bootique-jdbc-demo.git
cd jdbc-app-demo
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

Run the `--insert` command to create a table with the provided configuration file:
```bash
java -jar target/jdbc-app-demo-X.XX.jar -c config.yml -i
```

Check the data using the `--select` command:

```bash    
java -jar target/jdbc-app-demo-X.XX.jar -c classpath:config.yml -s
```