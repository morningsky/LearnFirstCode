package com.firstcode.section4_news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by sky on 2015/8/21.
 */
public class NewsContentFragment extends Fragment {
    private TextView tv_title;
    private TextView tv_content;
    private View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_newscontent,container,false);
        tv_title = (TextView) v.findViewById(R.id.tv_title);
        tv_content = (TextView) v.findViewById(R.id.tv_content);
        return v;
    }

    public  void refreshData(String title,String content)
    {
        tv_title.setText(title);
        tv_content.setText(content);
    }

}
