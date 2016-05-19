package com.fivefire.app.gdutcontacts.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.fivefire.app.gdutcontacts.R;
import com.fivefire.app.gdutcontacts.ui.common.BaseActivity;

public class VerifyActivity extends BaseActivity {

    @Override
    protected void initView() {

    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        setupActionBar();
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.verify);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_verify;
    }
}