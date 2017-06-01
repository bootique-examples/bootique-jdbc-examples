package io.bootique.jdbc.demo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.jdbc.DataSourceFactory;
import io.bootique.meta.application.CommandMetadata;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertCommand extends CommandWithMetadata {

    private Provider<DataSourceFactory> dataSource;

    @Inject
    public InsertCommand(Provider<DataSourceFactory> dataSource) {
        super(CommandMetadata.builder(InsertCommand.class).description("Demo command to insert data.").build());
        this.dataSource = dataSource;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        try (Connection connection = dataSource.get().forName("DerbyDatabase").getConnection()) {

            try (Statement statement = connection.createStatement()) {

                String createTableSQL = "CREATE TABLE TEST\n" +
                        "    (ID INT PRIMARY KEY,\n" +
                        "    NAME VARCHAR(12))";

                statement.execute(createTableSQL);
            }

        } catch (SQLException e) {
            return CommandOutcome.failed(1, e);
        }

        return CommandOutcome.succeeded();
    }
}
