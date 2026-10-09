package com.nrmyw.hud_data_event_lib.manager.intervalspeed;

import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.manager.warningpoint.HudWarningPointReShowManager;
import com.nrmyw.hud_data_event_lib.util.HudSendDataCheckUtil;
import com.nrmyw.hud_data_lib.type.HudCmdType;

public class HudIntervalSpeedSendManager {
    private static HudIntervalSpeedSendManager hudIntervalSpeedSendManager;
    private HudIntervalSpeedSendManager(){

    }

    public static HudIntervalSpeedSendManager getInstance(){
        if(null==hudIntervalSpeedSendManager){
            synchronized (HudIntervalSpeedSendManager.class){
                if(null==hudIntervalSpeedSendManager){
                    hudIntervalSpeedSendManager=new HudIntervalSpeedSendManager();
                }
            }
        }
        return hudIntervalSpeedSendManager;
    }


    private boolean intervalSpeedIsShow;
    public void sendIntervalSpeed(int intervalSpeed, int interval, int averageSpeed, int timeHours, int timeMin){
        intervalSpeed= HudSendDataCheckUtil.getSpeed(intervalSpeed);
        interval=HudSendDataCheckUtil.getDis(interval);
        averageSpeed= HudSendDataCheckUtil.getSpeed(averageSpeed);
        timeHours= HudSendDataCheckUtil.getSpeed(timeHours);
        timeMin= HudSendDataCheckUtil.getSpeed(timeMin);
        HudSendManager.getInstance().sendCmd(HudCmdType.INTERVAL_SPEED,intervalSpeed,interval,averageSpeed,timeHours,timeMin);
        if(!intervalSpeedIsShow){
            intervalSpeedIsShow=true;
//           BleEventSubscriptionSubject.getInstance().sendCmdByKStr(HudSendTwoTimeType.IntervalSpeed.name(), HudSendManager.getInstance().getAllByte(HudCmdType.INTERVAL_SPEED,intervalSpeed,interval,averageSpeed,timeHours,timeMin));
            HudWarningPointReShowManager.getInstance().nowNeedReshow(HudWarningPointReShowManager.ReShowType.SHOW_INTERVAL_SPEED);
        }
        if(HudSetConfig.getInstance().isAutoHideIntervalSpeed()){
            HudIntervalSpeedManager.getInstance().nowShow();
        }
    }

    public void hideIntervalSpeed(){
        intervalSpeedIsShow=false;
        HudIntervalSpeedManager.getInstance().setHide();
        if(HudSetConfig.getInstance().isAutoHideIntervalSpeed()){
            HudIntervalSpeedManager.getInstance().nowIsHide();
        }
        HudWarningPointReShowManager.getInstance().nowNeedReshow(HudWarningPointReShowManager.ReShowType.HIDE_INTERVAL_SPEED);
    }

}
