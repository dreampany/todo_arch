package com.dreampany.frame.data.util;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.dreampany.frame.R;
import com.dreampany.frame.ui.adapter.SmartAdapter;

public final class ViewUtil {

    private ViewUtil() {}

    public static void setSwipe(SwipeRefreshLayout swipe, SwipeRefreshLayout.OnRefreshListener listener) {
        if (swipe != null) {
            swipe.setColorSchemeResources(
                    R.color.colorPrimary,
                    R.color.colorAccent,
                    R.color.colorPrimaryDark);
            swipe.setOnRefreshListener(listener);
        }
    }

    public static void setRecycler(RecyclerView recycler, SmartAdapter adapter, RecyclerView.LayoutManager layout, RecyclerView.ItemAnimator animator, RecyclerView.ItemDecoration decoration) {
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layout);
        recycler.setHasFixedSize(true);
        adapter.setAnimationOnForwardScrolling(true);

        if (animator != null) {
            recycler.setItemAnimator(animator);
        } else {
            ((DefaultItemAnimator) recycler.getItemAnimator()).setSupportsChangeAnimations(false);
        }

        if (decoration != null) {
            recycler.addItemDecoration(decoration);
        }
    }
}
