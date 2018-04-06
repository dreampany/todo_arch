package com.dreampany.frame.ui.fragment;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment implements LifecycleOwner {

    protected ViewDataBinding binding;
    protected View view;

    protected int getLayoutId() {
        return 0;
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
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        onStopUi();
        super.onDestroyView();
    }
}
