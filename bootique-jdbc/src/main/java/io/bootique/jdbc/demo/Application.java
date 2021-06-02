package io.bootique.jdbc.demo;

import io.bootique.BQCoreModule;
import io.bootique.Bootique;
import com.google.inject.Binder;
import com.google.inject.Module;

public class Application implements Module {

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
