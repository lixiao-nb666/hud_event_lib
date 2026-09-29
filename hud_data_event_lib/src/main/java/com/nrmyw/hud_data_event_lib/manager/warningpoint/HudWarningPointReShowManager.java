package com.nrmyw.hud_data_event_lib.manager.warningpoint;

import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.manager.image.HudImageManeger;
import com.nrmyw.hud_data_event_lib.manager.intervalspeed.HudIntervalSpeedManager;
import com.nrmyw.hud_data_event_lib.manager.notifiction.HudNotifictionManager;
import com.nrmyw.hud_data_event_lib.manager.yellowstatu.HudYellowStatuManager;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.image.HudImageShowType;
import com.nrmyw.hud_data_lib.type.notification.HudNotificationIconType;
import com.nrmyw.hud_data_lib.type.warningproint.HudWarningPointType;
import com.nrmyw.hud_data_lib.type.yellow_statu.HudYellowStatuBjType;

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


    public void nowNeedReshow(ReShowType reShowType){
        if(!HudSetConfig.getInstance().getHudSetBean().isNeedReShowWarningPoint()){
            return;
        }
        boolean nowOlnyCanShowOne=HudImageManeger.getInstance().isImageCanShow()|| HudIntervalSpeedManager.getInstance().isNowIsShow()|| HudNotifictionManager.getInstance().isNotifictionIsShow();
        if(nowOlnyCanShowOne==beforeIsShowOne){
            return;
        }
        beforeIsShowOne=nowOlnyCanShowOne;
        if(!nowOlnyCanShowOne){
            //现在可以显示2个
            //隐藏图片
            switch (reShowType){
                case HIDE_INTERVAL_SPEED:
                    HudSendManager.getInstance().sendCmd(HudCmdType. SHOW_IMAGE, HudImageShowType.HIDE);
                    //隐藏通知栏
                    HudSendManager.getInstance().sendCmd(HudCmdType.NOTIFICATION_ICON, HudNotificationIconType.HIDE,HudNotificationIconType.HIDE);
                    HudSendManager.getInstance().sendCmd(HudCmdType.NOTIFICATION,"",0,"",0);
                    break;
                case HIDE_EXIT:
                    HudSendManager.getInstance().sendCmd(HudCmdType. SHOW_IMAGE, HudImageShowType.HIDE);
                    //隐藏区间限速
                    HudSendManager.getInstance().sendCmd(HudCmdType.HIDE_INTERVAL_SPEED);
                    break;
                case HIDE_IMAGE:
                    //隐藏通知栏
                    HudSendManager.getInstance().sendCmd(HudCmdType.NOTIFICATION_ICON, HudNotificationIconType.HIDE,HudNotificationIconType.HIDE);
                    HudSendManager.getInstance().sendCmd(HudCmdType.NOTIFICATION,"",0,"",0);
                    //隐藏区间限速
                    HudSendManager.getInstance().sendCmd(HudCmdType.HIDE_INTERVAL_SPEED);
                    break;
            }
            if(HudSetConfig.getInstance().isNeedBigWarningPoint()){
                HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT, HudWarningPointType.none,0);
            }
            //隐藏黄色状态栏
        }else {
            switch (reShowType){
                case SHOW_EXIT:
                case SHOW_IMAGE:
                case SHOW_INTERVAL_SPEED:
                    HudWarningPointManager.getInstance().reShow();
                    break;
            }
//
        }
//        HudYellowStatuManager.getInstance().reShow(nowOlnyCanShowOne);
//



    }

    public boolean getNowCanShowWpIsOne(){
        return HudImageManeger.getInstance().isImageCanShow()|| HudIntervalSpeedManager.getInstance().isNowIsShow()|| HudNotifictionManager.getInstance().isNotifictionIsShow();
    }



    public enum ReShowType{
        SHOW_IMAGE,
        HIDE_IMAGE,
        SHOW_INTERVAL_SPEED,
        HIDE_INTERVAL_SPEED,
        SHOW_EXIT,
        HIDE_EXIT,
    }

}
