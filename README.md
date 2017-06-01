[![Build Status](https://travis-ci.org/bootique-examples/bootique-jdbc-demo.svg)](https://travis-ci.org/bootique-examples/bootique-jdbc-demo)
# bootique-jdbc-demo

How to work with JDBC data stores integrated for [Bootique](http://bootique.io) app. 
   
*For additional help/questions about this example send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*
   
## Prerequisites
      
    * Java 1.8 or newer.
    * Apache Maven.
      
## Build the Demo
      
Here is how to build it:
        
        git clone git@github.com:bootique-examples/bootique-jdbc-demo.git
        cd bootique-jdbc-demo
        mvn package
      
## Run the Demo

Now you can check the options available in your app:
   
    java -jar target/bootique.jdbc.demo-1.0-SNAPSHOT.jar
    
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

Provide required configuration via *config.yml*:
    
    jdbc:
      DerbyDatabase:
        url: jdbc:derby:target/derby/DerbyDatabase;create=true
        driverClassName: org.apache.derby.jdbc.EmbeddedDriver
        initialSize: 1

Run custom command *--insert* to create a table:

    java -jar target/bootique.jdbc.demo-1.0-SNAPSHOT.jar -c classpath:config.yml -i
    
Check data via *--select* command:
    
    java -jar target/bootique.jdbc.demo-1.0-SNAPSHOT.jar -c classpath:config.yml -s

New table "TEST" data is successfully created:   
    
    ...
       The table TEST is successfully created
