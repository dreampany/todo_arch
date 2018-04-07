package com.dreampany.todo.di;

import com.dreampany.frame.di.ActivityScoped;
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
    @ContributesAndroidInjector
    abstract NavigationActivity navigationActivity();
}
