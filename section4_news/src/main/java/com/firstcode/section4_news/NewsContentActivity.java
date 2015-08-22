package com.firstcode.section4_news;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by sky on 2015/8/21.
 */
public class NewsContentActivity extends Activity {
    private NewsContentFragment contentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newscontent);
        contentFragment = new NewsContentFragment();
        String title = getIntent().getStringExtra("news_title");
        String content = getIntent().getStringExtra("news_content");
//       contentFragment.refreshData(title,content);

//        FragmentManager fm = getFragmentManager();
//        fm.beginTransaction ()
//                .add(R.id.fragment_newsContent,contentFragment)
//                .commit();
        //add的应该是占位视图frameLayout的布局id
        NewsContentFragment fragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.fragment_newsContent);
        fragment.refreshData(title,content);
    }
}
