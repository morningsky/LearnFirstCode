package com.firstcode.section2_useintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private TextView tv_back;
    private Button btn_startActivity1;
    private Button btn_startActivity2;
    private Button btn_startActivity3;
    private Button btn_startActivity4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_startActivity1 = (Button) findViewById(R.id.btn_startActivity1);
        btn_startActivity2 = (Button) findViewById(R.id.btn_startActivity2);
        btn_startActivity3 = (Button) findViewById(R.id.btn_startActivity3);
        btn_startActivity4 = (Button) findViewById(R.id.btn_startActivity4);
        tv_back = (TextView) findViewById(R.id.tv_back);

        btn_startActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "你好啊，我是activity1，请问你是？";
                Intent i = new Intent(MainActivity.this,SecondActivity.class);  //设置显式启动的intent
                i.putExtra("extra_data",data); //将数据传给要启动的activity
                startActivityForResult(i,1);
            }
        });

        btn_startActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.firstcode.TEST");//intent的另一种构造函数
                i.addCategory("com.firstcode.MY_CATEGORY");//category与action的字符都匹配时才会启动该intent
                //特别注意：在AndroidManifest文件中除声明自定义的category之外必须声明DEFAULT category;
                startActivity(i);
            }
        });

        btn_startActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.baidu.com"));//启用浏览器打开网页
                startActivity(i);
            }
        });

        btn_startActivity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Intent.ACTION_DIAL);//启用拨号
                i.setData(Uri.parse("tel:10086"));
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if (resultCode == RESULT_OK)
                {
                    String return_data = data.getStringExtra("data_return");
                    tv_back.setText(return_data); //将返回的数据显示出来
                }
                break;
        }
    }
}
