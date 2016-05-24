package com.fivefire.app.gdutcontacts;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.test.ApplicationTestCase;

import com.fivefire.app.gdutcontacts.utils.DataOperate;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        DataOperate operate = new DataOperate();

        operate.querycontains(getContext(),"Tag","2",new Handler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(msg.obj);
            }
        });
    }
}