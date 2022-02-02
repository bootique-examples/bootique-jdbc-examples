package io.bootique.jdbc.test;

import io.bootique.BQRuntime;
import io.bootique.Bootique;
import io.bootique.jdbc.junit5.DbTester;
import io.bootique.jdbc.junit5.Table;
import io.bootique.jdbc.junit5.derby.DerbyTester;
import io.bootique.junit5.BQApp;
import io.bootique.junit5.BQTest;
import io.bootique.junit5.BQTestScope;
import io.bootique.junit5.BQTestTool;

@BQTest
public abstract class BasicTest {

    // create a test DB
    @BQTestTool(BQTestScope.GLOBAL)
    static final DbTester db = DerbyTester.db()

            // make sure schema is created
            .initDB("classpath:test-schema.sql")

            // make sure test data is deleted before each test
            .deleteBeforeEachTest("t1");

    // create a global test app object, but do not run any commands
    @BQApp(value = BQTestScope.GLOBAL, skipRun = true)
    static final BQRuntime app = Bootique.app()
            .autoLoadModules()

            // make sure the test app is connected to our test DB
            .module(db.moduleWithTestDataSource("db"))
            .createRuntime();

    protected Table t1() {
        return db.getTable("t1");
    }
}
