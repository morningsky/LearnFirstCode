package com.firstcode.section4_news;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sky on 2015/8/21.
 */
public class NewsTitleFragment extends Fragment {
    private boolean isLand; //标志是否水平布局
    private ListView lv_newsTitleList;
    private ArrayList<News> newsList;
    private NewsListAdapter adapter;
    private String[] news_title = new String[]{
      "操举兵讨伐袁绍","备退守江陵","吕布兵败濮阳"
    };
    private String[] news_content = new String[]{
      "袁绍大败，大势已去","亮联吴抗曹","袁术称帝"
    };

    //初始化数据
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = new ArrayList<News>();
        adapter = new NewsListAdapter(getActivity(),R.layout.lv_item_title,newsList);
        for (int i = 0 ;i<news_title.length;i++)
        {
            News news = new News();
            news.setTitle(news_title[i]);
            news.setContent(news_content[i]);
            newsList.add(news);
            //news.get(i).setTitle(news_title[i]);  列表的错误用法！！！先add进去才有值
        }
    }

    //在activity创建时判断是否横屏
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.fragment_newsContent)!=null)
        {
            isLand = true;
        }else
        {
            isLand = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newstitle,container,false);
        lv_newsTitleList = (ListView) v.findViewById(R.id.lv_newsTitleList);
        lv_newsTitleList.setAdapter(adapter);
        lv_newsTitleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = newsList.get(position); //获取点击的news条目
                if (isLand)
                {
                    //横屏模式
                    NewsContentFragment fragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.fragment_newsContent);
                    fragment.refreshData(news.getTitle(),news.getContent());
                }else
                {
                    //竖屏模式
                    Intent i = new Intent(getActivity(),NewsContentActivity.class);
                    i.putExtra("news_title",news.getTitle());
                    i.putExtra("news_content",news.getContent());
                    startActivity(i);
                }

            }
        });
        return v;
    }


}
