package com.firstcode.section2_useintent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by sky on 2015/8/20.
 */
public class BaseActivity extends Activity {
    private static final String TAG = "BaseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,this.getClass().getSimpleName());//获取当前页面对应的activity
        ActivityCollector.addActivity(this);//每次创建都添加到列表里
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
