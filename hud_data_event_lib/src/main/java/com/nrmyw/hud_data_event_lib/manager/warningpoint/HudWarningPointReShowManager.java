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


    public void nowNeedReshow(){
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
            HudSendManager.getInstance().sendCmd(HudCmdType. SHOW_IMAGE, HudImageShowType.HIDE);
            //隐藏通知栏
            HudSendManager.getInstance().sendCmd(HudCmdType.NOTIFICATION,"",0,"",0);
            HudSendManager.getInstance().sendCmd(HudCmdType.NOTIFICATION_ICON, HudNotificationIconType.HIDE,HudNotificationIconType.HIDE);
            //隐藏区间限速
            HudSendManager.getInstance().sendCmd(HudCmdType.HIDE_INTERVAL_SPEED);
            if(HudSetConfig.getInstance().isNeedBigWarningPoint()){
                HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT, HudWarningPointType.none,0);
            }
            //隐藏黄色状态栏

        }else {
            HudWarningPointManager.getInstance().reShow();
//
        }
        HudYellowStatuManager.getInstance().reShow(nowOlnyCanShowOne);
//



    }
}
