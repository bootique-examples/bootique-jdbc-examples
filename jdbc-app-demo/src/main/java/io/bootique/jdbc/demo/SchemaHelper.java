package io.bootique.jdbc.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaHelper.class);

    static void createSchemaIfMissing(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            if (!schemaPresent(connection)) {
                createSchema(connection);
            }
        }
    }

    private static boolean schemaPresent(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getTables(null, null, "TEST", null)) {
            return rs.next();
        }
    }

    private static void createSchema(Connection connection) throws SQLException {

        LOGGER.info("Creating test schema...");

        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE TEST (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, NAME VARCHAR(100))");
        }
    }
}
