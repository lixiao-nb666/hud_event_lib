package com.nrmyw.hud_data_event_lib;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.nrmyw.hud_data_event_lib.manager.HudImageManeger;
import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.util.HudSendDataCheckUtil;
import com.nrmyw.hud_data_lib.bean.HudLaneCountBean;
import com.nrmyw.hud_data_lib.bean.HudLaneHiPassCountBean;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.display.HudSetDisplayDirectionType;
import com.nrmyw.hud_data_lib.type.image.HudImageShowType;
import com.nrmyw.hud_data_lib.type.image.HudImageType;
import com.nrmyw.hud_data_lib.type.lane.HudLaneInformationType;
import com.nrmyw.hud_data_lib.type.lane.HudNowLaneStrType;
import com.nrmyw.hud_data_lib.type.reach.HudReachType;
import com.nrmyw.hud_data_lib.type.set.HudBrightnessMoldType;
import com.nrmyw.hud_data_lib.type.set.HudGpsStatuType;
import com.nrmyw.hud_data_lib.type.speed.HudSpeedingShowBJType;
import com.nrmyw.hud_data_lib.type.speed.HudSpeedingTextType;
import com.nrmyw.hud_data_lib.type.speed.SpeedType;
import com.nrmyw.hud_data_lib.type.turn.HudTurnBjType;
import com.nrmyw.hud_data_lib.type.turn.HudTurnType;
import com.nrmyw.hud_data_lib.type.type.HudStatuType;
import com.nrmyw.hud_data_lib.type.ui.HudUiType;
import com.nrmyw.hud_data_lib.type.warningproint.HudWarningPointType;
import com.nrmyw.hud_data_lib.type.yellow_statu.HudYellowStatuBjType;

import java.nio.charset.StandardCharsets;

public class HudEvent implements HudEventImp {
    private static HudEvent hudEvent;

    public static HudEvent getInstance(){
        if(null==hudEvent){
            synchronized (HudEvent.class){
                if(null==hudEvent){
                    hudEvent=new HudEvent();
                }
            }
        }
        return hudEvent;
    }


    private HudEvent(){}

//    public void addListen(HudCmdListen hudCmdListen){
//        HudSendManager.getInstance().setListen(hudCmdListen);
//    }

    @Override
    public void sendBytes(byte[] bytes) {
        if(null==bytes||bytes.length==0){
            return;
        }
        HudSendManager.getInstance().sendCmdByte(bytes);
    }

    @Override
    public void sendTime() {
        HudSendManager.getInstance().sendCmd(HudCmdType.TIME);
    }



    @Override
    public void sendNowSpeed(int nowSpeed) {
        nowSpeed= HudSendDataCheckUtil.getSpeed(nowSpeed);
        HudSendManager.getInstance().sendCmd(HudCmdType.SPEED,nowSpeed);
    }

    @Override
    public void sendNowSpeed(int nowSpeed, int limitedSpeed1, int limitedSpeed2) {
        nowSpeed= HudSendDataCheckUtil.getSpeed(nowSpeed);
        limitedSpeed1= HudSendDataCheckUtil.getSpeed(limitedSpeed1);
        limitedSpeed2= HudSendDataCheckUtil.getSpeed(limitedSpeed2);
        HudSendManager.getInstance().sendCmd(HudCmdType.SPEED,nowSpeed,limitedSpeed1,limitedSpeed2);
    }

    @Override
    public void sendNowSpeed(int nowSpeed, SpeedType speedType) {
        nowSpeed= HudSendDataCheckUtil.getSpeed(nowSpeed);
        if(null==speedType){
            HudSendManager.getInstance().sendCmd(HudCmdType.SPEED,nowSpeed);
        }else {
            HudSendManager.getInstance().sendCmd(HudCmdType.SEND_SPEED,nowSpeed,speedType);
        }
    }

    @Override
    public void sendSpeeding(HudSpeedingTextType textColorStyle, HudSpeedingShowBJType speedingShowBJType) {
        if(null==textColorStyle||null==speedingShowBJType){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.SPEEDING,textColorStyle,speedingShowBJType);
    }


    @Override
    public void sendIntervalSpeed(int intervalSpeed, int interval, int averageSpeed, int timeHours, int timeMin) {
        intervalSpeed= HudSendDataCheckUtil.getSpeed(intervalSpeed);
        interval=HudSendDataCheckUtil.getDis(interval);
        averageSpeed= HudSendDataCheckUtil.getSpeed(averageSpeed);
        timeHours= HudSendDataCheckUtil.getSpeed(timeHours);
        timeMin= HudSendDataCheckUtil.getSpeed(timeMin);
        HudSendManager.getInstance().sendCmd(HudCmdType.INTERVAL_SPEED,intervalSpeed,interval,averageSpeed,timeHours,timeMin);
    }

    @Override
    public void hideIntervalSpeed() {
        HudSendManager.getInstance().sendCmd(HudCmdType.HIDE_INTERVAL_SPEED);
    }

    @Override
    public void sendWarningPointLimitedSpeed(int limitedSpeed1, int limitedSpeed2) {
        limitedSpeed1= HudSendDataCheckUtil.getSpeed(limitedSpeed1);
        limitedSpeed2= HudSendDataCheckUtil.getSpeed(limitedSpeed2);
        HudSendManager.getInstance().sendCmd(HudCmdType.SEND_WARNING_POINT_SPEED,limitedSpeed1,limitedSpeed2);
    }

    @Override
    public void sendWarningPoint(HudWarningPointType type1, int distance1) {
        if(null==type1){
            return;
        }
        distance1=HudSendDataCheckUtil.getDis(distance1);
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,type1,distance1);
    }

    @Override
    public void sendWarningPoint(HudWarningPointType type1, int distance1, HudWarningPointType type2, int distance2) {
        if(null==type1||null==type2){
            return;
        }
        distance1=HudSendDataCheckUtil.getDis(distance1);
        distance2=HudSendDataCheckUtil.getDis(distance2);
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT,type1,distance1,type2,distance2);
    }

    @Override
    public void sendBigWarningPoint(HudWarningPointType type1, int distance1) {
        if(null==type1){
            return;
        }
        distance1=HudSendDataCheckUtil.getDis(distance1);
        HudSendManager.getInstance().sendCmd(HudCmdType.BIG_WARNING_POINT,type1,distance1);
    }

    @Override
    public void sendWarningPoint1TitleStr(String str) {
        if(TextUtils.isEmpty(str)){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT_1_T_STR,str);
    }

    @Override
    public void sendWarningPoint1BodyStr(String str) {
        if(TextUtils.isEmpty(str)){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT_1_B_STR,str);
    }

    @Override
    public void sendWarningPoint2TitleStr(String str) {
        if(TextUtils.isEmpty(str)){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT_2_T_STR,str);
    }

    @Override
    public void sendWarningPoint2BodyStr(String str) {
        if(TextUtils.isEmpty(str)){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT_2_B_STR,str);
    }

    @Override
    public void sendReachInfo(int distance, int hours, int minutes, HudReachType reachType) {
        if(null==reachType){
            return;
        }
        distance=HudSendDataCheckUtil.getDis(distance);
        hours= HudSendDataCheckUtil.getTimeH(hours);
        minutes= HudSendDataCheckUtil.getTimeM(minutes);
        HudSendManager.getInstance().sendCmd(HudCmdType.REACH_INFO,distance,hours,minutes,reachType);
    }



    @Override
    public void sendReachInfo(String reachInfoStr) {
        if(TextUtils.isEmpty(reachInfoStr)){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.REACH_STR,reachInfoStr);
    }

    @Override
    public void sendLaneHide() {
        HudSendManager.getInstance().sendCmd(HudCmdType.LANE_INFORMATION, HudLaneInformationType.HIDE);
    }

    @Override
    public void sendLaneInformation(HudLaneCountBean laneCountBean) {
        if(null==laneCountBean||null==laneCountBean.getLaneList()||laneCountBean.getLaneList().size()==0){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.LANE_INFORMATION,HudLaneInformationType.DEF,laneCountBean);
    }

    @Override
    public void sendLaneHiPass(HudLaneHiPassCountBean laneHiPassCountBean) {
        if(null==laneHiPassCountBean||null==laneHiPassCountBean.getLaneList()||laneHiPassCountBean.getLaneList().size()==0){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.LANE_INFORMATION,HudLaneInformationType.HI_PASS,laneHiPassCountBean,laneHiPassCountBean.getIndex());
    }

    @Override
    public void sendTurnType(HudTurnType type1, int distance1) {
        if(null==type1){
            return;
        }
        distance1=HudSendDataCheckUtil.getDis(distance1);
        HudSendManager.getInstance().sendCmd(HudCmdType.TURN_TYPE,type1,distance1,HudTurnType.none,0);
    }

    @Override
    public void sendTurnType(HudTurnType type1, int distance1, HudTurnType type2, int distance2) {
        if(null==type1||null==type2){
            return;
        }
        distance1=HudSendDataCheckUtil.getDis(distance1);
        distance2=HudSendDataCheckUtil.getDis(distance2);
        HudSendManager.getInstance().sendCmd(HudCmdType.TURN_TYPE,type1,distance1,type2,distance2);
    }

    @Override
    public void setTurnBj(HudTurnBjType turnBj) {
        if(null==turnBj){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_TURN_BJ,turnBj);
    }





    @Override
    public void sendNextLaneName(String laneName) {
        if(TextUtils.isEmpty(laneName)){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.Next_LANE_NAME,laneName);
    }

    @Override
    public void sendNowLaneStr(HudNowLaneStrType nowLaneStrType, String laneName) {
        if(null==nowLaneStrType||TextUtils.isEmpty(laneName)){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.NOW_LANE_STR,nowLaneStrType,laneName);
    }

    @Override
    public void sendGpsStatu(HudGpsStatuType gpsStatuType) {
        if(null==gpsStatuType){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.GPS,gpsStatuType);
    }

    @Override
    public void setGpsSpeed(int gpsSpeed) {
        gpsSpeed=HudSendDataCheckUtil.getGpsSpeed(gpsSpeed);
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
        v=HudSendDataCheckUtil.getBrightnessV(v);
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
        if(TextUtils.isEmpty(sn)){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.REWRITE_SN,sn);
    }

    @Override
    public void queDeviceVersion() {
        HudSendManager.getInstance().sendCmd(HudCmdType.QUERY_DEVICE_VERSION);
    }

    @Override
    public void setSound(int v) {
        v=HudSendDataCheckUtil.getSoundV(v);
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_SOUND,v);
    }

    @Override
    public void queSound() {
        HudSendManager.getInstance().sendCmd(HudCmdType.QUERY_SOUND);
    }

    @Override
    public void sendImage(Bitmap bitmap) {
        if(null==bitmap||bitmap.isRecycled()){
            return;
        }
        HudImageManeger.getInstance().send(HudImageType.IMAGE,bitmap);
//        HudSendManager.getInstance().sendBitmap(bitmap,0);
    }

    @Override
    public void sendImage(Bitmap bitmap, HudImageType hudImageType) {
        if(null==bitmap||bitmap.isRecycled()||null==hudImageType){
            return;
        }
//        HudSendManager.getInstance().sendBitmap(bitmap,hudImageType.getType());
        HudImageManeger.getInstance().send(hudImageType,bitmap);
    }



    @Override
    public void showImage() {
        HudSendManager.getInstance().sendCmd(HudCmdType.SHOW_IMAGE, HudImageShowType.SHOW);
    }

    @Override
    public void hideImage() {
        HudImageManeger.getInstance().cancelImage(HudImageType.IMAGE);
    }

    @Override
    public void hideProgressBar() {
        HudImageManeger.getInstance().cancelImage(HudImageType.PROGRESS_BAR);
    }





    @Override
    public void setYellowStatu(HudYellowStatuBjType hudYellowStatuBjType) {
        if(null==hudYellowStatuBjType){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, hudYellowStatuBjType);
    }




    @Override
    public void sendYellowStatuStr(String yellowStatuStr) {
        if(TextUtils.isEmpty(yellowStatuStr)){
            return;
        }
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

//    @Override
//    public void setShowImageMaxW(int maxShowW) {
//        HudSetConfig.getInstance().setImageMaxW(maxShowW);
//    }
//
//    @Override
//    public void setShowImageMaxH(int maxShowH) {
//        HudSetConfig.getInstance().setImageMaxH(maxShowH);
//    }
//
//    @Override
//    public void setShowImageBitmapQualityType(BleSendBitmapQualityType bitmapQualityType) {
//        if(null==bitmapQualityType){
//            return;
//        }
//        HudSetConfig.getInstance().setBleSendBitmapQualityType(bitmapQualityType);
//    }

    @Override
    public void setBleName(String bleName) {
        if(TextUtils.isEmpty(bleName)){
            return;
        }
        if(bleName.length()>29){
            bleName=bleName.substring(0,29);
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_BLE_NAME,bleName);
    }

    @Override
    public void setTwsName(String twsName) {
        if(TextUtils.isEmpty(twsName)){
            return;
        }
        if(twsName.length()>29){
            twsName=twsName.substring(0,29);
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_TWS_NAME,twsName);
    }

    @Override
    public void setUiType(HudUiType uiType) {
        if(null==uiType){
            return;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_UI,uiType);
    }

    @Override
    public void initDisplayRect(){
        setDisplayRect(HudSetDisplayDirectionType.TOP,0);
    }

    @Override
    public void setDisplayRect(HudSetDisplayDirectionType setDisplayDirectionType, int value){
        value=HudSendDataCheckUtil.getDisplayRectV(value);
        HudSendManager.getInstance().sendCmd(HudCmdType.SET_DISPLAY_RECT_SIZE,setDisplayDirectionType,value);
    }
}
