package io.bootique.jdbc.demo;

import io.bootique.BQCoreModule;
import io.bootique.BQModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;
import io.bootique.di.Provides;
import io.bootique.jdbc.DataSourceFactory;

import javax.inject.Provider;
import javax.inject.Singleton;

public class App implements BQModule {

    static final String POSTGRES_DATASOURCE = "postgres";

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(App.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder)
                .addCommand(InsertCommand.class)
                .addCommand(SelectCommand.class);
    }

    @Provides
    @Singleton
    InsertCommand provideInsertCommand(Provider<DataSourceFactory> dataSourceFactoryProvider) {
        // it is common to use dependency "Providers" in commands instead of objects, so that the objects are
        // initialized lazily, and only when a given command is invoked
        return new InsertCommand(() -> dataSourceFactoryProvider.get().forName(POSTGRES_DATASOURCE));
    }

    @Provides
    @Singleton
    SelectCommand provideSelectCommand(Provider<DataSourceFactory> dataSourceFactoryProvider) {
        // it is common to use dependency "Providers" in commands instead of objects, so that the objects are
        // initialized lazily, and only when a given command is invoked
        return new SelectCommand(() -> dataSourceFactoryProvider.get().forName(POSTGRES_DATASOURCE));
    }
}
