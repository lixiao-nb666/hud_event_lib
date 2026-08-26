package com.nrmyw.hud_data_event_lib.manager.warningpoint;

import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.util.HudSendDataCheckUtil;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.warningproint.HudWarningPointType;

public class HudWarningPointManager {
    private static HudWarningPointManager hudWarningPointManager;
    private HudWarningPointType lastType1;
    private int lastDistance1;
    private HudWarningPointType lastType2;
    private int lastDistance2;
    private HudWarningPointManager(){}

    public static HudWarningPointManager getInstance(){
        if(null==hudWarningPointManager){
            synchronized (HudWarningPointManager.class){
                if(null==hudWarningPointManager){
                    hudWarningPointManager=new HudWarningPointManager();
                }
            }
        }
        return hudWarningPointManager;
    }

    public void addWarningPoint(HudWarningPointType type1, int distance1){
            if(null==type1){
                return;
            }
            distance1= HudSendDataCheckUtil.getDis(distance1);
            lastType1=type1;
            lastDistance1=distance1;
            lastType2=HudWarningPointType.none;
            lastDistance2=0;
            if(HudSetConfig.getInstance().isNeedBigWarningPoint()&&HudSetConfig.getInstance().isOneShowBigWarningPoint()){
                //如果能够大图标显示被允许并且，如果能够一个图标显示大图标
                HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,type1,distance1);

            }else {

                HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,type1,distance1,HudWarningPointType.none,0);
            }
    }

    public void addWarningPoint(HudWarningPointType type1, int distance1, HudWarningPointType type2, int distance2){
            if(null==type1||null==type2){
                return;
            }
            distance1=HudSendDataCheckUtil.getDis(distance1);
            distance2=HudSendDataCheckUtil.getDis(distance2);
            lastType1=type1;
            lastDistance1=distance1;
            lastType2=type2;
            lastDistance2=distance2;
            if(type2==HudWarningPointType.none&&HudSetConfig.getInstance().isNeedBigWarningPoint()&&HudSetConfig.getInstance().isOneShowBigWarningPoint()){
                //如果能够大图标显示被允许并且，如果能够一个图标显示大图标,并且第二个图标是空或者隐藏
                HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,type1,distance1);
            }else {
                HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,type1,distance1,type2,distance2);
            }
    }

    public void addBigWarningPoint(HudWarningPointType type1, int distance1){
            if(null==type1){
                return;
            }
            distance1=HudSendDataCheckUtil.getDis(distance1);
            lastType1=type1;
            lastDistance1=distance1;
            lastType2=HudWarningPointType.none;
            lastDistance2=0;
            if(HudSetConfig.getInstance().isNeedBigWarningPoint()){
                HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,type1,distance1);
            }else {
                HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,type1,distance1,HudWarningPointType.none,0);
            }
    }


    public void hideBigBigWarningPoint() {
        lastType1=HudWarningPointType.none;
        lastDistance1=0;
        lastType2=HudWarningPointType.none;
        lastDistance2=0;
        HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,HudWarningPointType.none,0);
    }


    public void hideWarningPoint() {
        lastType1=HudWarningPointType.none;
        lastDistance1=0;
        lastType2=HudWarningPointType.none;
        lastDistance2=0;
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,HudWarningPointType.none,0,HudWarningPointType.none,0);
    }

    public void nowNeedReShow(){
        if(!HudSetConfig.getInstance().getHudSetBean().isNeedReShowWarningPoint()){
            return;
        }


        if(null==lastType1||lastType1==HudWarningPointType.none){
            hideWarningPoint();
            return;
        }
        if(null==lastType2||lastType2==HudWarningPointType.none){
            HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,lastType1,lastDistance1,HudWarningPointType.none,0);
        }else {
            HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,lastType1,lastDistance1,lastType2,lastDistance2);
        }

    }



}
