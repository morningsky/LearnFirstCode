package com.firstcode.section3_easychat;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends ActionBarActivity {
    private String APIKEY ="d47d916113299d5eec8655e592450048";
    private Button btn_send;
    private EditText edt_content;
    private String content;
    private ListView lv_msgList;
    private List<Message> messages = new ArrayList<Message>();//收发消息列表的集合
    private MsgAdapter adapter;
    private HttpURLConnection connection;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what)
            {
                //因为子线程不能对UI进行操作
                case 0:
                    content = (String) msg.obj;
                    parseData(content);//解析返回的JSON数据
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_msgList = (ListView) findViewById(R.id.lv_msgList);
        Message message0 = new Message("你好啊",1);
        messages.add(message0);
        btn_send = (Button) findViewById(R.id.btn_sendMsg);
        edt_content = (EditText) findViewById(R.id.edt_content);
        //初始化适配器
        adapter= new MsgAdapter(this,R.layout.lv_item_msg,messages);
        lv_msgList.setAdapter(adapter);

        //发送消息
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = edt_content.getText().toString();
                if (!"".equals(content))  //输入不能为空
                {
                    sendMessage();
                    edt_content.clearFocus();
                }else {
                    Toast.makeText(MainActivity.this,"你什么都没说，我怎么懂你",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void sendMessage()
    {
        //如果超过30条记录，则清除10条
        if (messages.size()>=30)
        {
            for (int i =0;i<10;i++)
            {
                messages.remove(i);
            }
            adapter.notifyDataSetChanged();
        }
        Message message = new Message(content, 2);
        messages.add(message);
        adapter.notifyDataSetChanged();//动态添加listView的条目
        lv_msgList.setSelection(messages.size());//将listView定位到最后一行
        edt_content.setText("");//发送完成后清空编辑框文字
        //开启子线程请求网络数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                getAnswer(content);
            }
        }).start();
    }

    public void getAnswer(String send){
        StringBuffer sb = new StringBuffer();
        try {
            String INFO = URLEncoder.encode(send,"utf-8");//重编码要发送的消息
            String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;//将网址拼起来
            URL getUrl = new URL(getURL);//得到要访问的网址
            connection = (HttpURLConnection) getUrl.openConnection();
            connection.setConnectTimeout(8000);
            connection.connect();

            //JAVA IO流读取回复的消息
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line = "";
            while ((line=reader.readLine())!=null)
            {
                sb.append(line);
            }
            reader.close();

            //启用消息机制 将数据返回主线程更改listView数据
            android.os.Message message = new android.os.Message();
            message.what = 0;
            message.obj = sb.toString();
            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null)
                connection.disconnect();//断开连接
        }
    }

    public void parseData(String data)
    {
        try {
            JSONObject myJson = new JSONObject(data);
            String code = myJson.getString("code");
            if (code.equals("100000"))
            {
                String text = myJson.getString("text");
                Message message = new Message(text, 1);
                messages.add(message);
                adapter.notifyDataSetChanged();//动态添加listView的条目
                lv_msgList.setSelection(messages.size());//将listView定位到最后一行
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
