package io.bootique.jdbc.demo;

import io.bootique.BQCoreModule;
import io.bootique.BaseModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;

public class Application extends BaseModule {

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(Application.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder)
                .addCommand(InsertCommand.class)
                .addCommand(SelectCommand.class);
    }
}
