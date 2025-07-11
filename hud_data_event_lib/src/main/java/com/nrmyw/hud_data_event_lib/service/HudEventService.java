package com.nrmyw.hud_data_event_lib.service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import com.nrmyw.ble_event_lib.bean.BleSendImageEndInfoBean;
import com.nrmyw.ble_event_lib.bean.BleSendImageStartInfoBean;
import com.nrmyw.ble_event_lib.send.BleEventSubscriptionSubject;
import com.nrmyw.ble_event_lib.statu.BleStatu;
import com.nrmyw.ble_event_lib.statu.BleStatuEventObserver;
import com.nrmyw.ble_event_lib.statu.BleStatuEventSubscriptionSubject;
import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;
import com.nrmyw.hud_data_event_lib.HudEventImp;
import com.nrmyw.hud_data_event_lib.base.BaseService;
import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.util.HudCmdRetrunDataUtil;
import com.nrmyw.hud_data_lib.bean.HudLaneCountBean;
import com.nrmyw.hud_data_lib.bean.HudLaneHiPassCountBean;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.HudStatuType;
import com.nrmyw.hud_data_lib.type.image.HudImageShowType;
import com.nrmyw.hud_data_lib.type.image.HudSendImageType;
import com.nrmyw.hud_data_lib.type.lane.HudLaneInformationType;
import com.nrmyw.hud_data_lib.type.lane.HudNowLaneStrType;
import com.nrmyw.hud_data_lib.type.set.HudBrightnessMoldType;
import com.nrmyw.hud_data_lib.type.set.HudGpsStatuType;
import com.nrmyw.hud_data_lib.type.speed.HudSpeedingShowBJType;
import com.nrmyw.hud_data_lib.type.speed.HudSpeedingShowType;
import com.nrmyw.hud_data_lib.type.turn.HudTurnType;
import com.nrmyw.hud_data_lib.type.warningproint.HudWarningPointType;

import java.util.Date;

public class HudEventService extends BaseService implements HudEventImp {
//    private BluetoothGattServiceDao bluetoothGattServiceDao;
    private boolean canSendTime;
    private BleStatuEventObserver bleStatuEventObserver=new BleStatuEventObserver() {
        @Override
        public void sendBleStatu(BleStatu bleStatu, Object... objects) {
            switch (bleStatu){
                case CONNECTING:

                    break;
                case CONNECTED:
                    canSendTime=true;
                    initTime();
                    break;
                case DISCONNECTED:
                    canSendTime=false;
                    break;
                case SEND_IMAGE_START:
                    doSendImageStartThing((BleSendImageStartInfoBean) objects[0]);
                    break;
                case SEND_IMAGE_END:
                    doSendImageEndThing((BleSendImageEndInfoBean) objects[0]);
                    break;
                case RUN_ERR:
                    break;
                case RETRUN_BYTES:
                    byte[] retrunBytes= (byte[]) objects[0];
                    HudCmdRetrunDataUtil.doVue(retrunBytes);
                    break;
            }
        }

        private void doSendImageStartThing(BleSendImageStartInfoBean startInfoBean){
            byte[] startBytes= HudSendManager.getInstance().getAllByte(HudCmdType.READY_SEND_IMAGE,startInfoBean.getW(),startInfoBean.getH(),startInfoBean.getSize(), HudSendImageType.START);
            BleEventSubscriptionSubject.getInstance().sendImageIndexCmd(0,startBytes);
        }

        private void doSendImageEndThing(BleSendImageEndInfoBean endInfoBean){
            byte[] endBytes=HudSendManager.getInstance().getAllByte(HudCmdType.READY_SEND_IMAGE,endInfoBean.getW(),endInfoBean.getH(),endInfoBean.getSize(), HudSendImageType.END);
            BleEventSubscriptionSubject.getInstance().sendImageIndexCmd(endInfoBean.getIndex(),endBytes);
            HudEvent.getInstance().showImage();

        }
    };




    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                HudEventServiceMsgType msgType=HudEventServiceMsgType.values()[msg.what];
                switch (msgType){
                    case SEND_TIME:
                        handler.removeMessages(HudEventServiceMsgType.SEND_TIME.ordinal());
                        if(!canSendTime){
                            return;
                        }
                        HudEvent.getInstance().sendTime();
                        long nowTime=System.currentTimeMillis();
                        Date date = new Date(nowTime);
                        int needS=60-date.getSeconds();
                        handler.sendEmptyMessageDelayed(HudEventServiceMsgType.SEND_TIME.ordinal(),needS*1000);
                        break;
                }
            }catch (Exception e){}
        }
    };

    private void initTime(){
        handler.removeMessages(HudEventServiceMsgType.SEND_TIME.ordinal());
        handler.sendEmptyMessageDelayed(HudEventServiceMsgType.SEND_TIME.ordinal(),1000);
    }



    @Override
    public void init() {
        BleStatuEventSubscriptionSubject.getInstance().attach(bleStatuEventObserver);
    }

    @Override
    public void start(Intent intent, int flags, int startId) {

    }

    @Override
    public void close() {
        handler.removeCallbacksAndMessages(null);
        BleStatuEventSubscriptionSubject.getInstance().detach(bleStatuEventObserver);
    }

    @Override
    public void sendBytes(byte[] bytes) {
        HudSendManager.getInstance().sendCmdByte(bytes);
    }

    @Override
    public void sendTime() {
        HudSendManager.getInstance().sendCmd(HudCmdType.TIME);
    }

    @Override
    public void sendNowSpeed(int nowSpeed) {
        HudSendManager.getInstance().sendCmd(HudCmdType.SPEED,nowSpeed);
    }

    @Override
    public void sendNowSpeed(int nowSpeed, int limitedSpeed1, int limitedSpeed2) {
        HudSendManager.getInstance().sendCmd(HudCmdType.SPEED,nowSpeed,limitedSpeed1,limitedSpeed2);
    }

    @Override
    public void sendSpeedShow(HudSpeedingShowType speedingShowType, HudSpeedingShowBJType speedingShowBJType) {
        HudSendManager.getInstance().sendCmd(HudCmdType.SPEEDING,speedingShowType,speedingShowBJType);
    }

    @Override
    public void sendIntervalSpeed(int intervalSpeed, int interval, int averageSpeed, int timeHours, int timeMin) {
        HudSendManager.getInstance().sendCmd(HudCmdType.INTERVAL_SPEED,intervalSpeed,interval,averageSpeed,timeHours,timeMin);
    }

    @Override
    public void sendWarningPoint(HudWarningPointType type1, int distance1) {
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,type1,distance1);
    }

    @Override
    public void sendWarningPoint(HudWarningPointType type1, int distance1, HudWarningPointType type2, int distance2) {
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,type1,distance1,type2,distance2);
    }

    @Override
    public void sendBigWarningPoint(HudWarningPointType type1, int distance1) {
        HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,type1,distance1);
    }

    @Override
    public void sendWarningPoint1TitleStr(String str) {
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT_1_T_STR,str);
    }

    @Override
    public void sendWarningPoint1BodyStr(String str) {
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT_1_B_STR,str);
    }

    @Override
    public void sendWarningPoint2TitleStr(String str) {
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT_2_T_STR,str);
    }

    @Override
    public void sendWarningPoint2BodyStr(String str) {
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT_2_B_STR,str);
    }

    @Override
    public void sendReachInfo(int distance, int hours, int minutes) {
        HudSendManager.getInstance().sendCmd(HudCmdType.REACH_INFO,distance,hours,minutes);
    }

    @Override
    public void sendReachInfo(String reachInfoStr) {
        HudSendManager.getInstance().sendCmd(HudCmdType.REACH_STR,reachInfoStr);
    }

    @Override
    public void sendLaneHide() {
        HudSendManager.getInstance().sendCmd(HudCmdType.LANE_INFORMATION, HudLaneInformationType.HIDE);
    }

    @Override
    public void sendLaneInformation(HudLaneCountBean laneCountBean) {
        HudSendManager.getInstance().sendCmd(HudCmdType.LANE_INFORMATION,HudLaneInformationType.DEF,laneCountBean);
    }

    @Override
    public void sendLaneHiPass(HudLaneHiPassCountBean laneHiPassCountBean, int selectLane) {
        HudSendManager.getInstance().sendCmd(HudCmdType.LANE_INFORMATION,HudLaneInformationType.HI_PASS,laneHiPassCountBean,selectLane);
    }

    @Override
    public void sendTrunType(HudTurnType type1, int distance1) {
        HudSendManager.getInstance().sendCmd(HudCmdType.TURN_TYPE,type1,distance1,HudTurnType.none,0);
    }

    @Override
    public void sendTrunType(HudTurnType type1, int distance1, HudTurnType type2, int distance2) {
        HudSendManager.getInstance().sendCmd(HudCmdType.TURN_TYPE,type1,distance1,type2,distance2);
    }

    @Override
    public void sendNextLaneName(String laneName) {
        HudSendManager.getInstance().sendCmd(HudCmdType.Next_LANE_NAME,laneName);
    }

    @Override
    public void sendNowLaneStr(HudNowLaneStrType nowLaneStrType, String laneName) {
        HudSendManager.getInstance().sendCmd(HudCmdType.NOW_LANE_STR,nowLaneStrType,laneName);
    }

    @Override
    public void sendGpsStatu(HudGpsStatuType gpsStatuType) {
        HudSendManager.getInstance().sendCmd(HudCmdType.GPS,gpsStatuType);
    }

    @Override
    public void setGpsSpeed(int gpsSpeed) {
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_GPS_SPEED,gpsSpeed);
    }

    @Override
    public void queGpsSpeed() {
        HudSendManager.getInstance().sendCmd(HudCmdType.QUERY_GPS_SPEED);
    }

    @Override
    public void sendBrightnessAuto() {
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_BRIGHTNESS, HudBrightnessMoldType.AUTO);
    }

    @Override
    public void sendBrightnessHand(int v) {
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_BRIGHTNESS, HudBrightnessMoldType.HAND,v);
    }

    @Override
    public void queBrightness() {
        HudSendManager.getInstance().sendCmd(HudCmdType.QUERY_BRIGHTNESS);
    }

    @Override
    public void queSN() {
        HudSendManager.getInstance().sendCmd(HudCmdType.QUERY_SN);
    }

    @Override
    public void rewriteSN(String sn) {
        HudSendManager.getInstance().sendCmd(HudCmdType.REWRITE_SN,sn);
    }

    @Override
    public void queDeviceVersion() {
        HudSendManager.getInstance().sendCmd(HudCmdType.QUERY_DEVICE_VERSION);
    }

    @Override
    public void setSound(int v) {
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_SOUND,v);
    }

    @Override
    public void queSound() {
        HudSendManager.getInstance().sendCmd(HudCmdType.QUERY_SOUND);
    }

    @Override
    public void sendImage(Bitmap bitmap) {
        HudSendManager.getInstance().sendBitmap(bitmap);
    }

    @Override
    public void showImage() {
        HudSendManager.getInstance().sendCmd(HudCmdType.SHOW_IMAGE, HudImageShowType.SHOW);
    }

    @Override
    public void hideImage() {
        HudSendManager.getInstance().sendCmd(HudCmdType.SHOW_IMAGE, HudImageShowType.HIDE);
    }

    @Override
    public void showYellowStatu() {
        HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, HudStatuType.OPEN);
    }

    @Override
    public void hideYellowStatu() {
        HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, HudStatuType.CLOSE);
    }

    @Override
    public void sendYellowStatuStr(String yellowStatuStr) {
        HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU_STR,yellowStatuStr);
    }

    @Override
    public void iconFlicherOpen() {
        HudSendManager.getInstance().sendCmd(HudCmdType.ICON_FLICKER, HudStatuType.OPEN);
    }

    @Override
    public void iconFlicherClose() {
        HudSendManager.getInstance().sendCmd(HudCmdType.ICON_FLICKER, HudStatuType.CLOSE);
    }

    @Override
    public void factorySet() {
        HudSendManager.getInstance().sendCmd(HudCmdType.FACTORY_SET);
    }

    @Override
    public void deviceSoundOpen() {
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_DEVICE_SOUND_STATU, HudStatuType.OPEN);
    }

    @Override
    public void deviceSoundClose() {
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_DEVICE_SOUND_STATU, HudStatuType.CLOSE);
    }

    @Override
    public void queDeviceSound() {
        HudSendManager.getInstance().sendCmd(HudCmdType.QUERY_DEVICE_SOUND_STATU);
    }

    @Override
    public void daylightingStatuOpen() {
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_DAYLIGHTING_SHOW_STATU, HudStatuType.OPEN);
    }

    @Override
    public void daylightingStatuClose() {
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_DAYLIGHTING_SHOW_STATU, HudStatuType.CLOSE);
    }

    @Override
    public void setShowImageMaxW(int maxShowW) {
        HudSetConfig.getHudSetConfig().setImageMaxW(maxShowW);
    }

    @Override
    public void setShowImageMaxH(int maxShowH) {
        HudSetConfig.getHudSetConfig().setImageMaxH(maxShowH);
    }

    @Override
    public void setShowImageBitmapQualityType(BleSendBitmapQualityType bitmapQualityType) {
        HudSetConfig.getHudSetConfig().setBleSendBitmapQualityType(bitmapQualityType);
    }
}
