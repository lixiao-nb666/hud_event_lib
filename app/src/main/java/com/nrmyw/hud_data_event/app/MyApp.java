package com.nrmyw.hud_data_event.app;

import android.app.Application;
import android.util.Log;

import com.nrmyw.hud_data_event_lib.HudEventManager;


public class MyApp extends Application {
//    private HudCmdListen hudCmdListen=new HudCmdListen() {
//        @Override
//        public void getCmd(byte[] bytes) {
//            Log.i("kankanzhiling","kankanzhiling:"+ BleByteUtil.parseByte2HexStr(bytes));
//        }
//    };



    @Override
    public void onCreate() {
        super.onCreate();
        HudEventManager.getInstance().init(getApplicationContext());
    }
}
