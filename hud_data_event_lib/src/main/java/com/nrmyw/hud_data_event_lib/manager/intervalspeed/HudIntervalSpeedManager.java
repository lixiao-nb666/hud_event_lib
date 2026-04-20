package com.nrmyw.hud_data_event_lib.manager.intervalspeed;

import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.warningproint.HudWarningPointType;

public class HudIntervalSpeedManager {
    private static HudIntervalSpeedManager hudIntervalSpeedManager;
    private Listen listen;
    private HudIntervalSpeedManager(){}

    public static HudIntervalSpeedManager getInstance(){
        if(null==hudIntervalSpeedManager){
            synchronized (HudIntervalSpeedManager.class){
                if(null==hudIntervalSpeedManager){
                    hudIntervalSpeedManager=new HudIntervalSpeedManager();
                }
            }
        }
        return hudIntervalSpeedManager;
    }

    public void setListen(Listen listen){
        this.listen=listen;
    }

    private int hideNumb;
    public void nowShow(){
        if(null==listen){
            return;
        }
        hideNumb=3;
        listen.nowIsShow();
    }

    public void nowIsHide(){
        if(null==listen){
            return;
        }
        hideNumb=2;
        listen.nowIsHide();

    }

    public void sendHideCmd(){
        if(HudSetConfig.getInstance().isHideIntervalSpeedUseWarningPointCmd()){
            HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT, HudWarningPointType.none,0);
        }else {
            HudSendManager.getInstance().sendCmd(HudCmdType.HIDE_INTERVAL_SPEED);
        }
    }

    public int getHideNumb(){
        if(hideNumb>0){
            hideNumb--;
        }
        return hideNumb;

    }

    public interface Listen{
        public void nowIsShow();

        public void nowIsHide();
    }
}
