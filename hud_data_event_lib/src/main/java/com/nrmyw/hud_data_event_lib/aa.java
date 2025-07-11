//package com.nrmyw.hud_data_event_lib;
//
//import android.graphics.Bitmap;
//
//import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;
//
//public class aa {
//    public void sendNowSpeed(int nowSpeed,int limitedSpeed1 ,int limitedSpeed2){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SPEED,nowSpeed,limitedSpeed1,limitedSpeed2);
//    }
//
//    public void sendSpeedShow(T800SpeedShowType t800SpeedShowType, T800SpeedShowBJType t800SpeedShowBJType){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SPEEDING,t800SpeedShowType,t800SpeedShowBJType);
//    }
//
//    public void sendIntervalSpeed(int intervalSpeed,int interval ,int averageSpeed,int timeHours,int timeMin){
//        T800SendManager.getInstance().sendCmd(T800CmdType.INTERVAL_SPEED,intervalSpeed,interval,averageSpeed,timeHours,timeMin);
//    }
//
//    public void sendWarningPoint(T800WarningPointType type1	, int distance1){
//        T800SendManager.getInstance().sendCmd(T800CmdType.WARNING_POINT,type1,distance1);
//    }
//    public void sendWarningPoint(T800WarningPointType type1	,int distance1,T800WarningPointType type2	,int distance2){
//        T800SendManager.getInstance().sendCmd(T800CmdType.WARNING_POINT,type1,distance1,type2,distance2);
//    }
//
//    public void sendBigWarningPoint(T800WarningPointType type1	, int distance1){
//        T800SendManager.getInstance().sendCmd(T800CmdType.BIG_WARNING_POINT,type1,distance1);
//    }
//
//    public void sendWarningPoint1TitleStr(String str){
//        T800SendManager.getInstance().sendCmd(T800CmdType.WARNING_POINT_1_T_STR,str);
//    }
//
//    public void sendWarningPoint1BodyStr(String str){
//        T800SendManager.getInstance().sendCmd(T800CmdType.WARNING_POINT_1_B_STR,str);
//    }
//
//    public void sendWarningPoint2TitleStr(String str){
//        T800SendManager.getInstance().sendCmd(T800CmdType.WARNING_POINT_2_T_STR,str);
//    }
//
//    public void sendWarningPoint2BodyStr(String str){
//        T800SendManager.getInstance().sendCmd(T800CmdType.WARNING_POINT_2_B_STR,str);
//    }
//
//
//    public void sendReachInfo(int distance	,int hours, int	minutes){
//        T800SendManager.getInstance().sendCmd(T800CmdType.REACH_INFO,distance,hours,minutes);
//    }
//
//    public void sendReachInfo(int distance	,int hours, int	minutes	,T800TimeType type){
//        T800SendManager.getInstance().sendCmd(T800CmdType.REACH_INFO,distance,hours,minutes,type);
//    }
//
//    public void sendLaneHide(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.LANE_INFORMATION,T800LaneInformationType.NONE);
//    }
//
//    private void clearLaneInfor(){
//        T800LaneCountBean laneCountBean=new T800LaneCountBean();
//        for (int i=0;i<10;i++){
//            laneCountBean.add(T800LaneType.NONE);
//        }
//        T800SendManager.getInstance().sendCmd(T800CmdType.LANE_INFORMATION,T800LaneInformationType.DEF,laneCountBean);
//    }
//
//
//    public void sendLaneInformation(T800LaneCountBean laneCountBean){
//
//        T800SendManager.getInstance().sendCmd(T800CmdType.LANE_INFORMATION,T800LaneInformationType.DEF,laneCountBean);
//    }
//
//    public void sendLaneHiPass(T800LaneHiPassCountBean t800LaneHiPassCountBean,int selectLane){
//        T800SendManager.getInstance().sendCmd(T800CmdType.LANE_INFORMATION,T800LaneInformationType.HI_PASS,t800LaneHiPassCountBean,selectLane);
//    }
//
//    public void sendTrunType(T800TurnType type1	, int distance1){
//        T800SendManager.getInstance().sendCmd(T800CmdType.TURN_TYPE,type1,distance1,T800TurnType.none,0);
//    }
//    public void sendTrunType(T800TurnType type1	, int distance1,T800TurnType type2	, int distance2){
//        T800SendManager.getInstance().sendCmd(T800CmdType.TURN_TYPE,type1,distance1,type2,distance2);
//    }
//
//    public void sendNextLaneName(String laneName){
//        T800SendManager.getInstance().sendCmd(T800CmdType.Next_LANE_NAME,laneName);
//    }
//
//    public void sendNowLaneStr(T800NameType t800NameType,String laneName){
//        T800SendManager.getInstance().sendCmd(T800CmdType.NOW_LANE_STR,t800NameType,laneName);
//    }
//
//    public void sendGps(T800GpsType t800GpsType){
//        T800SendManager.getInstance().sendCmd(T800CmdType.GPS,t800GpsType);
//    }
//
//    public void setGpsSpeed(int gpsSpeed){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SET_GPS_SPEED,gpsSpeed);
//    }
//    public void queGpsSpeed(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.QUERY_GPS_SPEED);
//    }
//
//    public void sendBrightnessAuto(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SET_BRIGHTNESS, T800BrightnessType.AUTO);
//    }
//
//    public void sendBrightnessHand(int v){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SET_BRIGHTNESS, T800BrightnessType.HAND,v);
//    }
//
//
//
//    public void queBrightness(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.QUERY_BRIGHTNESS);
//    }
//
//    public void queSN(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.QUERY_SN);
//    }
//
//    public void rewriteSN(String sn){
//        T800SendManager.getInstance().sendCmd(T800CmdType.REWRITE_SN,sn);
//    }
//
//    public void queDeviceVersion(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.QUERY_DEVICE_VERSION);
//    }
//
//    public void setSound(int v){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SET_SOUND,v);
//    }
//
//    public void queSound(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.QUERY_SOUND);
//    }
//
//
//
//
//    public void sendImage(Bitmap bitmap, BleSendBitmapQualityType qualityType){
//        T800SendManager.getInstance().sendBitmap(bitmap, qualityType);
//    }
//
//    public void showImage(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SHOW_IMAGE, T800ImageShowType.SHOW);
//    }
//
//    public void hideImage(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SHOW_IMAGE, T800ImageShowType.HIDE);
//    }
//
//    public void showYellowStatu(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.YELLOW_STATU, T800StatuType.OPEN);
//    }
//
//    public void hideYellowStatu(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.YELLOW_STATU, T800StatuType.CLOSE);
//    }
//
//    public void sendYellowStatuStr(String yellowStatuStr){
//        T800SendManager.getInstance().sendCmd(T800CmdType.YELLOW_STATU_STR,yellowStatuStr);
//    }
//
//    public void iconFlicherOpen(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.ICON_FLICKER, T800StatuType.OPEN);
//    }
//
//    public void iconFlicherClose(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.ICON_FLICKER, T800StatuType.CLOSE);
//    }
//
//    public void factorySet(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.FACTORY_SET);
//    }
//
//    public void deviceSoundOpen(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SET_DEVICE_SOUND_STATU, T800StatuType.OPEN);
//    }
//
//    public void deviceSoundClose(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SET_DEVICE_SOUND_STATU, T800StatuType.CLOSE);
//    }
//
//    public void queDeviceSound(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.QUERY_DEVICE_SOUND_STATU);
//    }
//
//    public void daylightingStatuOpen(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SET_DAYLIGHTING_SHOW_STATU, T800StatuType.OPEN);
//    }
//
//    public void daylightingStatuClose(){
//        T800SendManager.getInstance().sendCmd(T800CmdType.SET_DAYLIGHTING_SHOW_STATU, T800StatuType.CLOSE);
//    }
//
//}
