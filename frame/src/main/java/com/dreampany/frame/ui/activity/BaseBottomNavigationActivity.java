package com.dreampany.frame.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public abstract class BaseBottomNavigationActivity extends BaseMenuActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private int currentNavId;

    protected int getNavigationViewId() {
        return 0;
    }

    protected abstract void onNavigationItem(int navItemId);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final BottomNavigationView bottomNavigationView = findViewById(getNavigationViewId());
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int targetNavId = item.getItemId();
        if (targetNavId != currentNavId) {
            onNavigationItem(targetNavId);
            currentNavId = targetNavId;
            return true;
        }
        return false;
    }

    public void setSelectedItem(final int navItemId) {
        final BottomNavigationView bottomNavigationView = findViewById(getNavigationViewId());
        if (bottomNavigationView != null) {
            bottomNavigationView.post(new Runnable() {
                @Override
                public void run() {
                    bottomNavigationView.setSelectedItemId(navItemId);
                }
            });
        }
    }
}
