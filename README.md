[![verify](https://github.com/bootique-examples/bootique-jdbc-demo/actions/workflows/verify.yml/badge.svg)](https://github.com/bootique-examples/bootique-jdbc-demo/actions/workflows/verify.yml)


# Bootique 3.x REST Demo

This is an example [Bootique](http://bootique.io) JDBC app. It shows how to define and use a named DataSource,
and how to write DB-aware unit tests with Bootique and Testcontainers.

Different Git branches contain demo code for different versions of Bootique:

* [3.x](https://github.com/bootique-examples/bootique-jdbc-demo/tree/3.x)
* [2.x](https://github.com/bootique-examples/bootique-jdbc-demo/tree/2.x)
* [1.x](https://github.com/bootique-examples/bootique-jdbc-demo/tree/1.x)

## Prerequisites

To build and run the demo, ensure you have the following installed on your machine:

* Docker
* Java 11 or newer 
* Maven

and then follow these steps:

## Checkout
```
git clone git@github.com:bootique-examples/bootique-jdbc-demo.git
cd bootique-jdbc-demo
```

## Start Postgres DB Locally

This starts a Postgres instance listening on port 15432, with login credentials of `postgres` / `test`, and 
creates a simple test schema:

```bash
docker-compose -f docker-compose.yml up -d
```

## Build, test and package

Run the following command to build the code, run the tests and package the app:
```
mvn clean package
```
This project uses a [runnable jar with lib folder](https://bootique.io/docs/3.x/bootique-docs/#runnable-jar-with-lib)
packaging recipe, so now the app is packaged for distribution as `target/bootique-jdbc-demo-3.0.tar.gz` archive. But
there is also the "unpacked" version in the `target` folder that can be used to run the app.

## Run

The following command prints a help message with supported options:
```bash  
java -jar target/bootique-jdbc-demo-3.0.jar
```

```  
NAME
      bootique-jdbc-demo-3.0.jar

OPTIONS
      -c yaml_location, --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      -h, --help
           Prints this message.

      -H, --help-config
           Prints information about application modules and their configuration options.

      -i, --insert
           Inserts sample data to a DB

      -s, --select
           Selects data from a DB
```

Run the `-i` (or `--insert`) command to create some sample data in the DB. DB location and login credential are 
specified in the provided `config.yml`.
```bash
java -jar target/bootique-jdbc-demo-3.0.jar -c config.yml -i
```

Run the `-s` (or `--select`) command to display the data inserted in the previous step:

```bash    
java -jar target/bootique-jdbc-demo-3.0.jar -c config.yml -s
```
