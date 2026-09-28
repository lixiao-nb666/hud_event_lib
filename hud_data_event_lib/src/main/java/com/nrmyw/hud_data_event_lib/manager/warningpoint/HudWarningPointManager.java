package com.nrmyw.hud_data_event_lib.manager.warningpoint;

import com.nrmyw.ble_event_lib.send.BleEventSubscriptionSubject;
import com.nrmyw.hud_data_event_lib.HudEvent;
import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.manager.yellowstatu.HudYellowStatuManager;
import com.nrmyw.hud_data_event_lib.type.HudSendTwoTimeType;
import com.nrmyw.hud_data_event_lib.util.HudSendDataCheckUtil;
import com.nrmyw.hud_data_lib.config.HudConfig;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.warningproint.HudWarningPointType;

public class HudWarningPointManager {
    private static HudWarningPointManager hudWarningPointManager;
    private DataBean dataBean=new DataBean();
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
    private boolean nowIsShow;
    public void addWarningPoint(HudWarningPointType type1, int distance1){
            addWarningPoint(type1,distance1,HudWarningPointType.none,0);
    }

    public void addWarningPoint(HudWarningPointType type1, int distance1, HudWarningPointType type2, int distance2){
            if(null==type1){
                type1=HudWarningPointType.none;
            }
            if(null==type2){
                type2=HudWarningPointType.none;
            }
            if(type1==HudWarningPointType.none&&type2==HudWarningPointType.none){
                hideWarningPoint();
                return;
            }
            distance1=HudSendDataCheckUtil.getDis(distance1);
            distance2=HudSendDataCheckUtil.getDis(distance2);
            if(type1==HudWarningPointType.none&&HudSetConfig.getInstance().getHudSetBean().isIfNoneWarningPointOnlyShowFirst()){
                dataBean.setLastType1(type2);
                dataBean.setLastDistance1(distance2);
                dataBean.setLastType2(HudWarningPointType.none);
                dataBean.setLastDistance2(0);
            }else {
                dataBean.setLastType1(type1);
                dataBean.setLastDistance1(distance1);
                dataBean.setLastType2(type2);
                dataBean.setLastDistance2(distance2);
            }

            if(HudSetConfig.getInstance().isNeedBigWarningPoint()&&HudSetConfig.getInstance().isOneShowBigWarningPoint()&&lastType2IsNull()){
                HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,dataBean.getLastType1(),dataBean.getLastDistance1());
            }else {
                HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,dataBean.getLastType1(),dataBean.getLastDistance1(),dataBean.getLastType2(),dataBean.getLastDistance2());
            }


    }


    public void addBigWarningPoint(HudWarningPointType type1, int distance1){
            if(null==type1){
                return;
            }
            distance1=HudSendDataCheckUtil.getDis(distance1);
            dataBean.setLastType1(type1);
            dataBean.setLastDistance1(distance1);
            dataBean.setLastType2(HudWarningPointType.none);
            dataBean.setLastDistance2(0);

            if(HudSetConfig.getInstance().isNeedBigWarningPoint()){
                //如果能显示大图标直接发， 不能显示就发普通的
                HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,type1,distance1);
            }else {
                HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,type1,distance1,HudWarningPointType.none,0);
            }
    }


    public void hideBigWarningPoint() {
        if(!HudSetConfig.getInstance().isNeedBigWarningPoint()){
            return;
        }
        dataBean.setLastType1(HudWarningPointType.none);
        dataBean.setLastDistance1(0);
        dataBean.setLastType2(HudWarningPointType.none);
        dataBean.setLastDistance2(0);
        HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,HudWarningPointType.none,0);
    }


    public void hideWarningPoint() {
        dataBean.setLastType1(HudWarningPointType.none);
        dataBean.setLastDistance1(0);
        dataBean.setLastType2(HudWarningPointType.none);
        dataBean.setLastDistance2(0);
        BleEventSubscriptionSubject.getInstance().sendCmdByKStr(HudSendTwoTimeType.HIDE_WP.name(), HudSendManager.getInstance().getAllByte(HudCmdType.WARNING_POINT,HudWarningPointType.none,0,HudWarningPointType.none,0));
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,HudWarningPointType.none,0,HudWarningPointType.none,0);
    }



    public void reShow(){
        if(lastType1IsNull()){

            return;
        }
        if(HudSetConfig.getInstance().isNeedBigWarningPoint()&&HudSetConfig.getInstance().isOneShowBigWarningPoint()&&lastType2IsNull()){
            HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,dataBean.getLastType1(),dataBean.getLastDistance1());
        }else {
            HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,dataBean.getLastType1(),dataBean.getLastDistance1(),dataBean.getLastType2(),dataBean.getLastDistance2());
        }
    }

    private boolean lastType1IsNull(){
       return dataBean.getLastType1()==HudWarningPointType.none ;
    }

    private boolean lastType2IsNull(){
        return dataBean.getLastType2()==HudWarningPointType.none ;
    }

    private class DataBean{
        private HudWarningPointType lastType1=HudWarningPointType.none;
        private int lastDistance1;
        private HudWarningPointType lastType2=HudWarningPointType.none;
        private int lastDistance2;


        public HudWarningPointType getLastType1() {
            if(null==lastType1){
                lastType1=HudWarningPointType.none;
            }
            return lastType1;
        }

        public void setLastType1(HudWarningPointType lastType1) {
            this.lastType1 = lastType1;
        }

        public int getLastDistance2() {
            return lastDistance2;
        }

        public void setLastDistance2(int lastDistance2) {
            this.lastDistance2 = lastDistance2;
        }

        public HudWarningPointType getLastType2() {
            if(null==lastType2){
                lastType2=HudWarningPointType.none;
            }

            return lastType2;
        }

        public void setLastType2(HudWarningPointType lastType2) {
            this.lastType2 = lastType2;
        }

        public int getLastDistance1() {
            return lastDistance1;
        }

        public void setLastDistance1(int lastDistance1) {
            this.lastDistance1 = lastDistance1;
        }

    }

}
