package com.firstcode.section2_useintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sky on 2015/8/18.
 */
public class SecondActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv_receive = (TextView) findViewById(R.id.tv_receive);
        String receiveData = getIntent().getStringExtra("extra_data");   //用getIntent方法获取启动这个activity的intent 再传入键值
        tv_receive.setText(receiveData);

        Button btn_backMainActivity = (Button) findViewById(R.id.btn_backMainActivity);
        btn_backMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data_return = "我是第二个activity";
                Intent backMain = new Intent();
                backMain.putExtra("data_return",data_return);
                setResult(RESULT_OK,backMain);   //该方法用于向上一个活动返回数据 将所需要回传的数据用key-value的形式保存在intent中
                finish();
            }
        });
    }
    /*
    功能：设置该activity的创建方式，形参中标明需要传来的数据 便于团队协作（A写了MainActivity' B写了SecondActivity）
    形参：context:启动本activity的activity data1 data2 表示需要传来的数据
     */
    public static void actionStart(Context context,String data1,String data2)
    {
        Intent i = new Intent(context,SecondActivity.class);
        i.putExtra("param1",data1);
        i.putExtra("param2",data2);
        context.startActivity(i);
    }

    @Override
    //当用户返回键时,也将数据传回上一个activity
    public void onBackPressed() {
        String data_return = "我是第二个activity";
        Intent i =new Intent();
        i.putExtra("data_return",data_return);
        setResult(RESULT_OK,i);
        finish();
    }
}
