package com.dreampany.frame.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.dreampany.frame.util.BarUtil;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity implements LifecycleOwner {

    protected ViewDataBinding binding;

    protected int getLayoutId() {
        return 0;
    }

    protected int getToolbarId() {
        return 0;
    }

    protected boolean isFullScreen() {
        return false;
    }

    protected abstract void onStartUi(Bundle state);

    protected abstract void onStopUi();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getLayoutId();
        if (layoutId != 0) {
            if (isFullScreen()) {
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                BarUtil.hide(this);
            } else {
                BarUtil.show(this);
            }

            binding = DataBindingUtil.setContentView(this, layoutId);

            Toolbar toolbar = findViewById(getToolbarId());
            if (toolbar != null) {
                if (isFullScreen()) {
                    if (toolbar.isShown()) {
                        toolbar.setVisibility(View.GONE);
                    }
                } else {
                    if (!toolbar.isShown()) {
                        toolbar.setVisibility(View.VISIBLE);
                    }
                    setSupportActionBar(toolbar);
                    ActionBar actionBar = getSupportActionBar();
                    if (actionBar != null) {
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        actionBar.setHomeButtonEnabled(true);
                    }
                }
            }
        }

        // inform child about initialization
        onStartUi(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        onStopUi();
        super.onDestroy();
    }
}
