package com.nrmyw.hud_data_event_lib.manager;

import android.text.TextUtils;

import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.notification.HudNotificationIconType;

public class HudNotifictionManager {
    private static HudNotifictionManager hudNotifictionManager;

    private HudNotifictionManager(){}

    public static HudNotifictionManager getInstance(){
        if(null==hudNotifictionManager){
            synchronized (HudNotifictionManager.class){
                if(null==hudNotifictionManager){
                    hudNotifictionManager=new HudNotifictionManager();
                }
            }
        }
        return hudNotifictionManager;
    }


    public void setMsg(String notifictionStr1, int interval1, String notifictionStr2, int interval2){
        //这里不要加判断为空，因为外面已经判断了
        //下面加个判断，看ICON是否在显示
        boolean needChange=false;
        if(TextUtils.isEmpty(notifictionStr1)){
            if(getIconType1()!=HudNotificationIconType.HIDE){
                iconType1=HudNotificationIconType.HIDE;
                needChange=true;
            }
        }
        if(TextUtils.isEmpty(notifictionStr2)){
            if(getIconType2()!=HudNotificationIconType.HIDE){
                iconType2=HudNotificationIconType.HIDE;
                needChange=true;
            }
        }
        //为了最后一条返回指令不出错，只能先调隐藏
        if(needChange){
            HudSendManager.getInstance().sendCmd(HudCmdType.NOTIFICATION_ICON,this.iconType1,this.iconType2);
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.NOTIFICATION,notifictionStr1,interval1,notifictionStr2,interval2);

    }


    private HudNotificationIconType iconType1; HudNotificationIconType iconType2;


    private HudNotificationIconType getIconType1(){
        if(null==iconType1){
            iconType1=HudNotificationIconType.HIDE;
        }
        return iconType1;
    }


    private HudNotificationIconType getIconType2(){
        if(null==iconType2){
            iconType2=HudNotificationIconType.HIDE;
        }
        return iconType2;
    }
    public void setIcon(HudNotificationIconType iconType1, HudNotificationIconType iconType2){
        if(null==iconType1){
            iconType1=HudNotificationIconType.HIDE;
        }
        if(null==iconType2){
            iconType2=HudNotificationIconType.HIDE;
        }
        this.iconType1=iconType1;
        this.iconType2=iconType2;
        HudSendManager.getInstance().sendCmd(HudCmdType.NOTIFICATION_ICON,this.iconType1,this.iconType2);
    }

}
