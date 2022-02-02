package io.bootique.jdbc.demo;

import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.jdbc.DataSourceFactory;
import io.bootique.meta.application.CommandMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCommand extends CommandWithMetadata {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertCommand.class);

    private Provider<DataSourceFactory> dataSourceProvider;

    @Inject
    public InsertCommand(Provider<DataSourceFactory> dataSourceProvider) {

        super(CommandMetadata.builder(InsertCommand.class)
                .description("Creates a table in the test Derby DB and inserts sample data")
                .build());

        this.dataSourceProvider = dataSourceProvider;
    }

    @Override
    public CommandOutcome run(Cli cli) {

        DataSource dataSource = dataSourceProvider.get().forName("DerbyDatabase");

        try {
            SchemaHelper.createSchemaIfMissing(dataSource);
        } catch (SQLException e) {
            return CommandOutcome.failed(1, "Failed to create test schema", e);
        }

        LOGGER.info("Inserting test data...");

        try (Connection connection = dataSource.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO TEST (NAME) VALUES (?)")) {
                statement.setString(1, "Name_" + System.currentTimeMillis());
                statement.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            return CommandOutcome.failed(1, "Failed to create test data", e);
        }

        return CommandOutcome.succeeded();
    }
}
