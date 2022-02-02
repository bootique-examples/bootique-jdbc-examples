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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectCommand extends CommandWithMetadata {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectCommand.class);

    private Provider<DataSourceFactory> dataSourceProvider;

    @Inject
    public SelectCommand(Provider<DataSourceFactory> dataSourceProvider) {
        super(CommandMetadata.builder(SelectCommand.class)
                .description("Selects tests data from the test Derby DB")
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

        LOGGER.info("Selecting test data...");

        try (Connection connection = dataSource.getConnection()) {

            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT ID, NAME FROM TEST")) {
                    while (rs.next()) {
                        System.out.printf("Row: %s, %s\n", rs.getInt(1), rs.getString(2));
                    }
                }
            }
        } catch (SQLException e) {
            return CommandOutcome.failed(1, e);
        }

        return CommandOutcome.succeeded();
    }
}
