package com.kurio.corona.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends NetworkActivity {
    protected Context mContext;
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderScreen();
        mActivity = this;
        mContext = mActivity.getBaseContext();
        initComponent();
        initData();
    }

    /**
     * all the common setting for all the activity screen
     */
    private void renderScreen() {
        setContentView(getLayoutId());
    }

    /**
     * the method for UI component pre-setting
     */
    protected abstract void initComponent();

    /**
     * the method for request or adapt data
     */
    protected abstract void initData();

    /**
     * @return the current layout resource
     */
    protected abstract int getLayoutId();
}
