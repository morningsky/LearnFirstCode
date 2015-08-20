package com.firstcode.section3_easychat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private Button btn_send;
    private EditText edt_content;
    private String content;
    private List<Message> messages = new ArrayList<Message>();//收发消息列表的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv_msgList = (ListView) findViewById(R.id.lv_msgList);
        Message message0 = new Message("你好啊",1);
        messages.add(message0);
        btn_send = (Button) findViewById(R.id.btn_sendMsg);
        edt_content = (EditText) findViewById(R.id.edt_content);
        //初始化适配器
        final MsgAdapter adapter= new MsgAdapter(this,R.layout.lv_item_msg,messages);
        //发送消息
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = edt_content.getText().toString();
                Message message = new Message(content,2);
                messages.add(message);
                adapter.notifyDataSetChanged();//动态添加listView的条目
            }
        });
        lv_msgList.setAdapter(adapter);

    }
}
