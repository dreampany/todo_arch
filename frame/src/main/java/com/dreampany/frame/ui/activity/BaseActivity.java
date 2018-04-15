package com.dreampany.frame.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.afollestad.aesthetic.Aesthetic;
import com.dreampany.frame.R;
import com.dreampany.frame.data.util.BarUtil;
import com.dreampany.frame.data.util.FragmentUtil;
import com.dreampany.frame.ui.fragment.BaseFragment;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;


public abstract class BaseActivity extends DaggerAppCompatActivity implements LifecycleOwner {

    protected ViewDataBinding binding;
    private BaseFragment currentFragment;

    protected int getLayoutId() {
        return 0;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

    protected boolean isFullScreen() {
        return false;
    }

    protected abstract void onStartUi(Bundle state);

    protected abstract void onStopUi();

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        Aesthetic.attach(this);
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

            if (Aesthetic.isFirstTime()) {
                Aesthetic.get()
                        .colorPrimaryRes(R.color.colorPrimary)
                        .colorAccentRes(R.color.colorAccent)
                        .colorStatusBarAuto()
                        .apply();
            }
        }

        binding.getRoot().post(new Runnable() {
            @Override
            public void run() {
                onStartUi(savedInstanceState);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Aesthetic.resume(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        Aesthetic.pause(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        onStopUi();
        super.onDestroy();
    }

    public void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void setSubtitle(String subtitle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(subtitle);
        }
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(BaseFragment fragment) {
        this.currentFragment = fragment;
    }

    protected void openActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

    protected <T extends BaseFragment> T commitFragment(final Class<T> fragmentClass, final int parentId) {
        T currentFragment = FragmentUtil.commitFragment(this, fragmentClass, parentId);
        setCurrentFragment(currentFragment);
        return currentFragment;
    }

    protected <T extends BaseFragment> T commitFragment(Class<T> clazz, Lazy<T> fragmentProvider, final int parentId) {
        T fragment = FragmentUtil.getFragmentByTag(this, clazz.getSimpleName());
        if (fragment == null) {
            fragment = fragmentProvider.get();
        }
        T currentFragment = FragmentUtil.commitFragment(this, fragment, parentId);
        setCurrentFragment(currentFragment);
        return currentFragment;
    }
}
