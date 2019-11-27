package com.e.bestmoviesapp;

import android.app.Application;

import com.e.bestmoviesapp.common.utils.ActivityLifecycleListener;


public class BestMovieApplication extends Application {

    private ActivityLifecycleListener activityLifecycleListener = new ActivityLifecycleListener();


        @Override
        public void onCreate() {
            super.onCreate();

        }

    public ActivityLifecycleListener getActivityLifecycleListener() {
        return activityLifecycleListener;
    }

}
