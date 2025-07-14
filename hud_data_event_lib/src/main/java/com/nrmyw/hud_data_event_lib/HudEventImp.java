package com.nrmyw.hud_data_event_lib;

import android.graphics.Bitmap;

import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;
import com.nrmyw.hud_data_lib.bean.HudLaneCountBean;
import com.nrmyw.hud_data_lib.bean.HudLaneHiPassCountBean;
import com.nrmyw.hud_data_lib.type.lane.HudNowLaneStrType;
import com.nrmyw.hud_data_lib.type.set.HudGpsStatuType;
import com.nrmyw.hud_data_lib.type.speed.HudSpeedingShowBJType;
import com.nrmyw.hud_data_lib.type.speed.HudSpeedingShowType;
import com.nrmyw.hud_data_lib.type.time.HudTimeType;
import com.nrmyw.hud_data_lib.type.turn.HudTurnType;
import com.nrmyw.hud_data_lib.type.warningproint.HudWarningPointType;

public interface HudEventImp {


    public  void sendBytes(byte[]bytes);

    public void sendTime();

    public void sendNowSpeed(int nowSpeed);

    public void sendNowSpeed(int nowSpeed,int limitedSpeed1 ,int limitedSpeed2);

    public void sendSpeedShow(HudSpeedingShowType speedingShowType, HudSpeedingShowBJType speedingShowBJType);

    public void sendIntervalSpeed(int intervalSpeed,int interval ,int averageSpeed,int timeHours,int timeMin);

    public void sendWarningPoint(HudWarningPointType type1	, int distance1);

    public void sendWarningPoint(HudWarningPointType type1	,int distance1,HudWarningPointType type2	,int distance2);

    public void sendBigWarningPoint(HudWarningPointType type1	, int distance1);

    public void sendWarningPoint1TitleStr(String str);

    public void sendWarningPoint1BodyStr(String str);

    public void sendWarningPoint2TitleStr(String str);

    public void sendWarningPoint2BodyStr(String str);


    public void sendReachInfo(int distance	,int hours, int	minutes);

//    public void sendReachInfo(int distance	, int hours, int	minutes	, HudTimeType type);

    public void sendReachInfo(String reachInfoStr);

    public void sendLaneHide();

    public void sendLaneInformation(HudLaneCountBean laneCountBean);

    public void sendLaneHiPass(HudLaneHiPassCountBean laneHiPassCountBean, int selectLane);
    public void sendTrunType(HudTurnType type1	, int distance1);
    public void sendTrunType(HudTurnType type1	, int distance1,HudTurnType type2	, int distance2);

    public void sendNextLaneName(String laneName);

    public void sendNowLaneStr(HudNowLaneStrType nowLaneStrType, String laneName);

    public void sendGpsStatu(HudGpsStatuType gpsStatuType);

    public void setGpsSpeed(int gpsSpeed);
    public void queGpsSpeed();

    public void sendBrightnessAuto();

    public void sendBrightnessHand(int v);


    public void queBrightness();

    public void queSN();

    public void rewriteSN(String sn);

    public void queDeviceVersion();

    public void setSound(int v);

    public void queSound();

    public void sendImage(Bitmap bitmap);

    public void showImage();

    public void hideImage();

    public void showYellowStatu();

    public void hideYellowStatu();

    public void sendYellowStatuStr(String yellowStatuStr);

    public void iconFlicherOpen();

    public void iconFlicherClose();

    public void factorySet();

    public void deviceSoundOpen();

    public void deviceSoundClose();

    public void queDeviceSound();

    public void daylightingStatuOpen();

    public void daylightingStatuClose();

    public void setShowImageMaxW(int maxShowW);

    public void setShowImageMaxH(int  maxShowH);

    public void setShowImageBitmapQualityType(BleSendBitmapQualityType bitmapQualityType);

}
