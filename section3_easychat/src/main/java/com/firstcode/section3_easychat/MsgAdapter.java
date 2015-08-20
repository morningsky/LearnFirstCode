package com.firstcode.section3_easychat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sky on 2015/8/20.
 */
public class MsgAdapter extends ArrayAdapter<Message> {
    private int resourceId;
    private View v;
    public MsgAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        Message msg = getItem(position);
        if (convertView == null)
        {
            v = LayoutInflater.from(getContext()).inflate(R.layout.lv_item_msg,null);
            viewHolder.left_layout = (LinearLayout) v.findViewById(R.id.left_layout);
            viewHolder.right_layout = (LinearLayout) v.findViewById(R.id.right_layout);
            viewHolder.leftMsg = (TextView) v.findViewById(R.id.left_msg);
            viewHolder.rightMsg = (TextView) v.findViewById(R.id.right_msg);
            v.setTag(viewHolder);
        }else
        {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }

        if (msg.getType() == Message.TYPE_RECEIVED)
        {
            //收到的消息放在左侧 右侧布局隐藏
            viewHolder.right_layout.setVisibility(View.GONE);
            viewHolder.left_layout.setVisibility(View.VISIBLE);
            viewHolder.leftMsg.setText(msg.getContent());
        }else if (msg.getType() == Message.TYPE_SEND)
        {
            //相反
            viewHolder.left_layout.setVisibility(View.GONE);
            viewHolder.right_layout.setVisibility(View.VISIBLE);
            viewHolder.rightMsg.setText(msg.getContent());
        }
        return v;
    }

    class ViewHolder
    {
        LinearLayout right_layout;
        LinearLayout left_layout;
        TextView rightMsg;
        TextView leftMsg;
    }
}
