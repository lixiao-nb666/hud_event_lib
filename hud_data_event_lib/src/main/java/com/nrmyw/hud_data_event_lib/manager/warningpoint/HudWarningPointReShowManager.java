package com.nrmyw.hud_data_event_lib.manager.warningpoint;

import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_event_lib.manager.image.HudImageManeger;
import com.nrmyw.hud_data_event_lib.manager.intervalspeed.HudIntervalSpeedManager;
import com.nrmyw.hud_data_event_lib.manager.notifiction.HudNotifictionManager;
import com.nrmyw.hud_data_event_lib.manager.yellowstatu.HudYellowStatuManager;

public class HudWarningPointReShowManager {

    private static HudWarningPointReShowManager hudWarningPointReShowManager;
    private HudWarningPointReShowManager(){}

    public static HudWarningPointReShowManager getInstance(){
        if(null==hudWarningPointReShowManager){
            synchronized (HudWarningPointReShowManager.class){
                if(null==hudWarningPointReShowManager){
                    hudWarningPointReShowManager=new HudWarningPointReShowManager();
                }
            }
        }
        return hudWarningPointReShowManager;
    }
    private boolean beforeIsShowOne;


    public void nowNeedReshow(){
        if(!HudSetConfig.getInstance().getHudSetBean().isNeedReShowWarningPoint()){
            return;
        }
        boolean nowOlnyCanShowOne=HudImageManeger.getInstance().isImageCanShow()|| HudIntervalSpeedManager.getInstance().isNowIsShow()|| HudNotifictionManager.getInstance().isNotifictionIsShow();
        if(nowOlnyCanShowOne==beforeIsShowOne){
            return;
        }
        beforeIsShowOne=nowOlnyCanShowOne;
        HudWarningPointManager.getInstance().reShow();
        HudYellowStatuManager.getInstance().reShow(nowOlnyCanShowOne);


    }
}
