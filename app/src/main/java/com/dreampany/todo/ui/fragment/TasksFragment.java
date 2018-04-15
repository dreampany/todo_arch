package com.dreampany.todo.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dreampany.frame.data.util.ViewUtil;
import com.dreampany.frame.injector.ActivityScoped;
import com.dreampany.frame.ui.fragment.BaseMenuFragment;
import com.dreampany.todo.R;
import com.dreampany.todo.contract.TaskContract;
import com.dreampany.todo.databinding.FragmentTasksBinding;

import javax.inject.Inject;

@ActivityScoped
public class TasksFragment extends BaseMenuFragment implements
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        TaskContract.View {

    private FragmentTasksBinding binding;

    @Inject
    public TasksFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tasks;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_fragment_tasks;
    }

    @Override
    protected void onStartUi(Bundle state) {
        setTitle(R.string.title_home);
        // presenter.takeView(this);
        binding = (FragmentTasksBinding) super.binding;
        binding.fab.setOnClickListener(this);
        ViewUtil.setSwipe(binding.swipeRefresh, this);
    }

    @Override
    protected void onMenuCreated(Menu menu) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_filter:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStopUi() {
        //presenter.dropView();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void setLoadIndicator(boolean active) {

    }


}
