package com.dreampany.todo.presenter;

import com.dreampany.frame.injector.ActivityScoped;
import com.dreampany.todo.contract.TaskContract;
import com.dreampany.todo.data.enums.TaskFilterType;

import javax.annotation.Nullable;
import javax.inject.Inject;

@ActivityScoped
public final class TaskPresenter implements TaskContract.Presenter {

    @Nullable
    private TaskContract.View view;
    private TaskFilterType filter;
    private boolean firstLoad;

    @Inject
    TaskPresenter() {
        filter = TaskFilterType.ALL;
        firstLoad = true;
    }

    @Override
    public void takeView(TaskContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }
}
