package com.firstcode.section2_useintent;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 2015/8/20.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>() ;

    public static void addActivity(Activity activity)
    {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    public static void finishAll()
    {
        for (Activity activity:activities)
        {
            //如果遍历到的activity对象未释放
            if (!activity.isFinishing()){
                activity.finish();
            }

        }
    }
}
