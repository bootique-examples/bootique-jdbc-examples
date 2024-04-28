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
import java.util.function.Supplier;

public class InsertCommand extends CommandWithMetadata {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertCommand.class);

    private final Supplier<DataSource> dataSourceProvider;

    public InsertCommand(Supplier<DataSource> dataSourceProvider) {

        super(CommandMetadata.builder(InsertCommand.class)
                .description("Inserts sample data to a DB")
                .build());

        this.dataSourceProvider = dataSourceProvider;
    }

    @Override
    public CommandOutcome run(Cli cli) {

        DataSource dataSource = dataSourceProvider.get();

        LOGGER.info("Inserting generated data...");

        try (Connection connection = dataSource.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement st = connection.prepareStatement("INSERT INTO test (name) VALUES (?)")) {
                for (int i = 0; i < 10; i++) {
                    st.setString(1, "user_" + i);
                    st.addBatch();
                }

                st.executeBatch();
            }

            connection.commit();

        } catch (SQLException e) {
            return CommandOutcome.failed(1, "Failed to insert data", e);
        }

        return CommandOutcome.succeeded();
    }
}
