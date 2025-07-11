package com.nrmyw.hud_data_event_lib;

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
        try {
            if(null!=hudEventServiceDao){
                return (HudEventService) hudEventServiceDao.getService();
            }
        }catch (Exception e){}
        return null;
    }



}
