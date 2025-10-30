package com.nrmyw.hud_data_event_lib;

import android.app.Notification;
import android.content.Context;

import com.nrmyw.hud_data_event_lib.service.HudEventService;
import com.nrmyw.hud_data_event_lib.service.HudEventServiceDao;

public class HudEventManager {
    private static HudEventManager hudManager;
    private HudEventServiceDao hudEventServiceDao;

    private HudEventManager(){}

    public static HudEventManager getInstance(){
        if(null==hudManager){
            synchronized (HudEventManager.class){
                if(null==hudManager){
                    hudManager=new HudEventManager();
                }
            }
        }
        return hudManager;
    }

    public void init(Context context){
        hudEventServiceDao=new HudEventServiceDao(context);
        hudEventServiceDao.startServiceIsBind();
    }

    public void close(){
        if(null!=hudEventServiceDao){
            hudEventServiceDao.stopServiceIsBind();
            hudEventServiceDao=null;
        }
    }


    public HudEventImp getHudEvent(){
        return  HudEvent.getInstance();
    }


//    private Notification notification;
//
//    public void setNotification(Notification notification){
//        this.notification=notification;
//    }
//
//    public Notification getNotification(){
//        return notification;
//    }
//
//    private int notificationId;
//
//    public int getNotificationId() {
//        return notificationId;
//    }
//
//    public void setNotificationId(int notificationId) {
//        this.notificationId = notificationId;
//    }
}
