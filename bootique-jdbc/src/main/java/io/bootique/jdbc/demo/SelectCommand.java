package io.bootique.jdbc.demo;

import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.jdbc.DataSourceFactory;
import io.bootique.meta.application.CommandMetadata;

import javax.inject.Inject;
import javax.inject.Provider;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectCommand extends CommandWithMetadata {

    private Provider<DataSourceFactory> dataSource;

    @Inject
    public SelectCommand(Provider<DataSourceFactory> dataSource) {
        super(CommandMetadata.builder(SelectCommand.class).description("Demo command to select data.").build());
        this.dataSource = dataSource;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        try (Connection connection
                     = dataSource.get().forName("DerbyDatabase").getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            try (ResultSet rs
                         = metaData.getTables(null, null, "TEST", null)) {
                while (rs.next()) {
                    System.out.println(String.format("The table %s is successfully created", rs.getString(3)));
                }
            }
        } catch (SQLException e) {
            return CommandOutcome.failed(1, e);
        }

        return CommandOutcome.succeeded();
    }
}
