package com.firstcode.section5_mybroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by sky on 2015/8/22.
 */
public class MyBroadCastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"你好哇",Toast.LENGTH_SHORT).show();
    }
}
