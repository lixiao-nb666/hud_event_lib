package com.nrmyw.hud_data_event_lib.manager;

public class HudTimeManager {
    private static HudTimeManager hudTimeManager;

    private Listen listen;
    private boolean timeIsStart;
    private HudTimeManager(){}

    public static HudTimeManager getInstance(){
        if(null==hudTimeManager){
            synchronized (HudTimeManager.class){
                if(null==hudTimeManager){
                    hudTimeManager=new HudTimeManager();
                }
            }
        }
        return hudTimeManager;
    }

    public void setListen(Listen listen){
        this.listen=listen;
    }


    public void setTimeStart(boolean timeIsStart){
        this.timeIsStart=timeIsStart;
        if(null==listen){
            return;
        }
        if(this.timeIsStart){
            listen.initTime();
        }else {
            listen.stopTime();
        }
    }


    public boolean getTimeIsStart(){
        return timeIsStart;
    }

    public interface Listen{

        public void initTime();

        public void stopTime();

    }
}
