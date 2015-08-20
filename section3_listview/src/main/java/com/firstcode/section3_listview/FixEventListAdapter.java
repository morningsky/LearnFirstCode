package com.firstcode.section3_listview;

import android.content.Context;
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
    private ViewHolder viewHolder;

    public FixEventListAdapter(Context context, int resource, List<FixEvent> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) //如果未曾有过缓存
        {
            v = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_fixType = (ImageView) v.findViewById(R.id.iv_fixType);
            viewHolder.iv_fixState = (ImageView) v.findViewById(R.id.iv_fixState);
            viewHolder.tv_time = (TextView) v.findViewById(R.id.tv_time);
            viewHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);
            v.setTag(viewHolder);//将viewHolder对象保存在view中
        }else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }

        //将数据填充到视图
            FixEvent curFixEvent = getItem(position);

            viewHolder.iv_fixType.setImageResource(curFixEvent.getTypeImageId());
            viewHolder.iv_fixState.setImageResource(curFixEvent.getStateImageId());
            viewHolder.tv_title.setText(curFixEvent.getTitle());
            viewHolder.tv_time.setText(curFixEvent.getTime());
        return v;
    }


    //非常实用的优化listview的方法 将控件的实例进行缓存
    class ViewHolder
    {
        ImageView iv_fixType;
        ImageView iv_fixState;
        TextView tv_time;
        TextView tv_title;
    }
}
