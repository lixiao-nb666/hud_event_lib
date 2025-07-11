package com.nrmyw.hud_data_event.app;

import android.app.Application;

import com.nrmyw.hud_data_event_lib.HudEventManager;


public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        HudEventManager.getInstance().init(getApplicationContext());
    }
}
