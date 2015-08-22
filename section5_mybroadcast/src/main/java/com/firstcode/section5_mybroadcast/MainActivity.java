package com.firstcode.section5_mybroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Button btn_send;
    private IntentFilter intentFilter;
    private LocalBroadcast localBroadcast;
    private LocalBroadcastManager lbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lbm = LocalBroadcastManager.getInstance(this);//初始化本地广播发送管理器

        btn_send = (Button) findViewById(R.id.btn_sendMessage);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.morningsky.LOCAL_CAST");
                lbm.sendBroadcast(i);
            }
        });

        intentFilter = new IntentFilter();
        localBroadcast = new LocalBroadcast();
        intentFilter.addAction("com.morningsky.LOCAL_CAST");
        lbm.registerReceiver(localBroadcast,intentFilter); //注册广播
    }

    //发送本地广播测试
    class LocalBroadcast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"这是一条本地广播",Toast.LENGTH_SHORT).show();
        }
    }
}

