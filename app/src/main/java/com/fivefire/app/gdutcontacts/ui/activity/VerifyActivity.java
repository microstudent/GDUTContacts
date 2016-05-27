package com.fivefire.app.gdutcontacts.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.fivefire.app.gdutcontacts.R;
import com.fivefire.app.gdutcontacts.adapter.VerifyAdapter;
import com.fivefire.app.gdutcontacts.model.User;
import com.fivefire.app.gdutcontacts.ui.common.BaseActivity;
import com.fivefire.app.gdutcontacts.utils.DataOperate;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.bmob.v3.Bmob;

public class VerifyActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "VerifyActivity";
    private RecyclerView mRecyclerView;

    private VerifyAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressWarnings("unchecked")
    private Handler mHandle = new MyHandler(this);

    @Override
    public void onRefresh() {
        getData();
    }


    static class MyHandler extends Handler {
        private final WeakReference<VerifyActivity> mActivity;

        public MyHandler(VerifyActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            VerifyActivity activity = mActivity.get();
            if (activity != null) {
                activity.handleMessage(msg);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void handleMessage(Message msg) {
        if (msg.what == 1) {
            List<User> result = (List<User>) msg.obj;
            mAdapter.setData(result);
        } else {
            Toast.makeText(this, "获取失败，请稍后再试！", Toast.LENGTH_SHORT).show();
        }
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        Bmob.initialize(this, "58d2bb059cc1244e252cea21b4313d0c");
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        setupActionBar();
        mAdapter = new VerifyAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        swipeRefreshLayout.setOnRefreshListener(this);
        getData();
    }

    private void getData() {
        DataOperate dataOperate = new DataOperate();
        dataOperate.queryContains(this, "Tag", "2", mHandle);
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
