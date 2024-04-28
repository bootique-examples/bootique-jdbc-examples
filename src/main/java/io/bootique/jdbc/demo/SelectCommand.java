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
import java.util.function.Supplier;

public class SelectCommand extends CommandWithMetadata {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectCommand.class);

    private final Supplier<DataSource> dataSourceProvider;

    public SelectCommand(Supplier<DataSource> dataSourceProvider) {
        super(CommandMetadata.builder(SelectCommand.class)
                .description("Selects data from a DB")
                .build());

        this.dataSourceProvider = dataSourceProvider;
    }

    @Override
    public CommandOutcome run(Cli cli) {

        DataSource dataSource = dataSourceProvider.get();

        LOGGER.info("Selecting data...");

        try (Connection connection = dataSource.getConnection()) {

            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT id, name FROM test")) {
                    while (rs.next()) {
                        System.out.printf("%s, %s\n", rs.getInt(1), rs.getString(2));
                    }
                }
            }
        } catch (SQLException e) {
            return CommandOutcome.failed(1, e);
        }

        return CommandOutcome.succeeded();
    }
}
