package com.dreampany.todo.app;

import com.dreampany.frame.app.BaseApp;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends BaseApp {




    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return null;
    }
}
