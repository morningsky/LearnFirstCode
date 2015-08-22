package com.firstcode.section4_news;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sky on 2015/8/21.
 */
public class NewsListAdapter extends ArrayAdapter<News> {
    private int resourceId;
    private View v;

    public NewsListAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    //每一个条目的视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        if (convertView == null)
        {
            v = LayoutInflater.from(getContext()).inflate(resourceId,null);
        }
        else
        {
            v = convertView; //将缓存视图赋值
        }
        TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText(news.getTitle());
        return v;
    }
}
