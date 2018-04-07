package com.dreampany.frame.ui.activity;

import android.support.v7.view.ActionMode;
import android.view.Menu;

public abstract class BaseMenuActivity extends BaseActivity {

    protected int getMenuId() {
        return 0;
    }

    protected int getContextualMenuId() {
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuId = getMenuId();
        if (menuId != 0) { //this need clear
            menu.clear();
            getMenuInflater().inflate(menuId, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
}
