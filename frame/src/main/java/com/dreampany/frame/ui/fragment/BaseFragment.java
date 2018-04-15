package com.dreampany.frame.ui.fragment;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreampany.frame.data.util.AndroidUtil;
import com.dreampany.frame.data.util.TextUtil;
import com.dreampany.frame.ui.activity.BaseActivity;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment implements LifecycleOwner {

    protected ViewDataBinding binding;
    protected View view;

    protected int getLayoutId() {
        return 0;
    }

    public boolean hasBackPressed() {
        return false;
    }

    protected abstract void onStartUi(Bundle state);

    protected abstract void onStopUi();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layoutId = getLayoutId();

        if (layoutId != 0) {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
            view = binding.getRoot();
        } else {
            view = super.onCreateView(inflater, container, savedInstanceState);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.getRoot().post(new Runnable() {
            @Override
            public void run() {
                onStartUi(savedInstanceState);
            }
        });
    }

    @Override
    public void onDestroyView() {
        onStopUi();
        super.onDestroyView();
    }

    @Override
    public Context getContext() {
        if (AndroidUtil.hasMarshmallow()) {
            return super.getContext();
        }
        View view = getView();
        if (view != null) {
            return view.getContext();
        }
        return getParent();
    }

    protected BaseActivity getParent() {
        Activity activity = getActivity();
        if (!BaseActivity.class.isInstance(activity) || activity.isFinishing() || activity.isDestroyed()) {
            return null;
        }
        return (BaseActivity) activity;
    }

    protected void setTitle(int resId) {
        if (resId <= 0) {
            return;
        }
        setTitle(TextUtil.getString(getContext(), resId));
    }

    protected void setSubtitle(int resId) {
        if (resId <= 0) {
            return;
        }
        setSubtitle(TextUtil.getString(getContext(), resId));
    }

    protected void setTitle(String title) {
        Activity activity = getActivity();
        if (BaseActivity.class.isInstance(activity)) {
            ((BaseActivity) activity).setTitle(title);
        }
    }

    protected void setSubtitle(String subtitle) {
        Activity activity = getActivity();
        if (BaseActivity.class.isInstance(activity)) {
            ((BaseActivity) activity).setSubtitle(subtitle);
        }
    }
}
