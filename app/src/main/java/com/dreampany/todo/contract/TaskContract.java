package com.dreampany.todo.contract;

import com.dreampany.frame.presenter.BasePresenter;
import com.dreampany.frame.ui.view.BaseView;

public interface TaskContract {

    interface View extends BaseView<Presenter> {
        void setLoadIndicator(boolean active);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
