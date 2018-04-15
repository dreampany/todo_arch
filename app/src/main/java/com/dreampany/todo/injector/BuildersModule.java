package com.dreampany.todo.injector;

import com.dreampany.frame.injector.ActivityScoped;
import com.dreampany.todo.ui.activity.LaunchActivity;
import com.dreampany.todo.ui.activity.NavigationActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract LaunchActivity launchActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {TaskModule.class, MoreModule.class})
    abstract NavigationActivity navigationActivity();
}
