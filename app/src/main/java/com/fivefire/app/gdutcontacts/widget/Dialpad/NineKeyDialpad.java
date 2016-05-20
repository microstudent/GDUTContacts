package com.fivefire.app.gdutcontacts.widget.dialpad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.fivefire.app.gdutcontacts.R;
import com.fivefire.app.gdutcontacts.widget.dialpad.ninekeybutton.INineKeyButton;
import com.fivefire.app.gdutcontacts.widget.dialpad.ninekeybutton.NineKeyButton;
import com.fivefire.app.gdutcontacts.widget.dialpad.query.IQuery;
import com.fivefire.app.gdutcontacts.widget.dialpad.searchview.DeletableEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MicroStudent on 2016/5/19.
 */
public class NineKeyDialpad extends FrameLayout implements INineKeyDialpad, View.OnClickListener {

    private static final String TAG = "NineKeyDialpad";
    private List<INineKeyButton> mNineKeyButtons;

    private DeletableEditText mSearchView;

    private ImageButton mSendMsgButton;

    private ImageButton mCallButton;

    private ImageButton mHideButton;

    private TableLayout mContainer;

    private OnQueryTextListener mOnQueryTextListener;

    private RecyclerView mRecyclerView;

    public NineKeyDialpad(Context context) {
        this(context, null);
    }

    public NineKeyDialpad(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineKeyDialpad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.layout_ninekey_dialpad, this, true);

        mNineKeyButtons = new ArrayList<>();

        initView();

        setupListener();
    }

    private void initView() {
        mContainer = (TableLayout) findViewById(R.id.container);
        getAllNineKeyButton();
        mSearchView = (DeletableEditText) findViewById(R.id.et);
        mSendMsgButton = (ImageButton) findViewById(R.id.bt_send_msg);
        mCallButton = (ImageButton) findViewById(R.id.bt_call);
        mHideButton = (ImageButton) findViewById(R.id.bt_hide);
    }

    private void setupListener() {
        mHideButton.setOnClickListener(this);
        mSendMsgButton.setOnClickListener(this);
        mCallButton.setOnClickListener(this);
        for (INineKeyButton button : mNineKeyButtons) {
            button.setOnClickListener(this);
        }
    }

    private void getAllNineKeyButton() {
        int count = mContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            TableRow row = (TableRow) mContainer.getChildAt(i);
            for (int j = 0;j<3;j++) {
                View view = row.getChildAt(j);
                if (view instanceof NineKeyButton) {
                    mNineKeyButtons.add((INineKeyButton) view);
                }
            }
        }
    }

    public OnQueryTextListener getOnQueryTextListener() {
        return mOnQueryTextListener;
    }

    public void setOnQueryTextListener(OnQueryTextListener mOnQueryTextListener) {
        this.mOnQueryTextListener = mOnQueryTextListener;
        if (mOnQueryTextListener.getQuery() == null) {
            mOnQueryTextListener.setQuery(new IQuery() {
                @Override
                public List filter(List data, String queryString) {
                    return null;
                }
            });
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void setRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void setQuery(IQuery query) {
        if (mOnQueryTextListener != null) {
            mOnQueryTextListener.setQuery(query);
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof INineKeyButton) {
            handleNineKeyButtonOnClick((INineKeyButton)v);
            return;
        }
        switch (v.getId()) {
            case R.id.bt_call:
                Toast.makeText(getContext(), "test", Toast.LENGTH_LONG).show();
                break;
            case R.id.bt_send_msg:
                break;
            case R.id.bt_hide:
                break;
        }
    }

    private void handleNineKeyButtonOnClick(INineKeyButton button) {
        mSearchView.insertByCursor(button.getNumber());

        if (mOnQueryTextListener != null) {
            mOnQueryTextListener.onQueryTextChange(mSearchView.getText().toString());
        }
    }
}