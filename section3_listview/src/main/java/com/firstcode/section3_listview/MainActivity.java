package com.firstcode.section3_listview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private List<FixEvent> fixEvents = new ArrayList<FixEvent>();
    private ListView lv_listItem;
    private FixEventListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv_fixItem = (ListView) findViewById(R.id.lv_fixItem);
        //让适配器将数据绑定listView
        adapter = new FixEventListAdapter(MainActivity.this,R.layout.lv_item_fixevent,fixEvents);
        lv_fixItem.setAdapter(adapter);
    }

    protected String getTime()
    {
        Date date = new Date();
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        return time;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_listItem)
        {
            FixEvent fixEvent = new FixEvent();
            fixEvent.setTitle("你还好吗");
            fixEvent.setStateImageId(R.drawable.ok);
            fixEvent.setTypeImageId(R.drawable.book);
            fixEvent.setTime(getTime());
            fixEvents.add(fixEvent);

            adapter.notifyDataSetChanged();     //动态添加ListView的item
        }
        return true;
    }
}
