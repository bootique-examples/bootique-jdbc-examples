package io.bootique.jdbc.demo;

import io.bootique.BQRuntime;
import io.bootique.Bootique;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.jdbc.junit5.DbTester;
import io.bootique.jdbc.junit5.tc.TcDbTester;
import io.bootique.junit5.BQApp;
import io.bootique.junit5.BQTest;
import io.bootique.junit5.BQTestScope;
import io.bootique.junit5.BQTestTool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@BQTest
public class InsertCommandTest {

    @BQTestTool(BQTestScope.GLOBAL)
    static final DbTester db = TcDbTester.db("jdbc:tc:postgresql:11:///")
            .initDB("sql/postgres-schema.sql")
            .deleteBeforeEachTest("test");

    @BQApp(skipRun = true)
    static BQRuntime app = Bootique
            .app()
            .autoLoadModules()

            // need to specify the App module explicitly, as it is not setup for autoloading
            .module(App.class)

            // connect the test DB with the test app
            .module(db.moduleWithTestDataSource(App.POSTGRES_DATASOURCE))
            .createRuntime();

    @Test
    void run() {

        // run insert
        CommandOutcome o = app.getInstance(InsertCommand.class).run(app.getInstance(Cli.class));
        
        // was it successful?
        assertTrue(o.isSuccess(), () -> o.getMessage());

        // was the data created?
        db.getTable("test").matcher().assertMatches(10);
    }
}
