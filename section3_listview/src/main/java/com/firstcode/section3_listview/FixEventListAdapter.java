package com.firstcode.section3_listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sky on 2015/8/20.
 */
public class FixEventListAdapter extends ArrayAdapter<FixEvent> {
    private int resourceId;         //item的布局文件

    private View v;
    private ImageView iv_fixType;
    private ImageView iv_fixState;
    private TextView tv_time;
    private TextView tv_title;

    public FixEventListAdapter(Context context, int resource, List<FixEvent> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) //如果未曾有过缓存
        {
            v = LayoutInflater.from(getContext()).inflate(resourceId,null);
        }else {
            v = convertView;
        }
            FixEvent curFixEvent = getItem(position);
            iv_fixType = (ImageView) v.findViewById(R.id.iv_fixType);
            iv_fixState = (ImageView) v.findViewById(R.id.iv_fixState);
            tv_time = (TextView) v.findViewById(R.id.tv_time);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
            iv_fixType.setImageResource(curFixEvent.getTypeImageId());
            iv_fixState.setImageResource(curFixEvent.getStateImageId());

            tv_title.setText(curFixEvent.getTitle());
            tv_time.setText(curFixEvent.getTime());
        return v;
    }
}
