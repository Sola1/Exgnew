package com.credit.exg.exg.base;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;


/**
 * Created by zhangjiaqi on 18/8/28.
 */

public class BaseApplication extends Application{
    public static BaseApplication application;
    public static Context context;
    public static BaseApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = this;
    }
    public static Context getContext() {
        return context;
    }
}
