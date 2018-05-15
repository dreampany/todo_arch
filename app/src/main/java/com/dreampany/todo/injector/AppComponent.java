package com.dreampany.todo.injector;


import android.app.Application;

import com.dreampany.frame.injector.AppModule;
import com.dreampany.todo.app.App;
import com.dreampany.todo.data.source.TasksRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        TasksRepositoryModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    TasksRepository getTasksRepository();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
