package com.nrmyw.hud_data_event_lib;

import android.graphics.Bitmap;

import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;
import com.nrmyw.hud_data_lib.bean.HudLaneCountBean;
import com.nrmyw.hud_data_lib.bean.HudLaneHiPassCountBean;
import com.nrmyw.hud_data_lib.type.image.HudImageType;
import com.nrmyw.hud_data_lib.type.lane.HudNowLaneStrType;
import com.nrmyw.hud_data_lib.type.reach.HudReachType;
import com.nrmyw.hud_data_lib.type.set.HudGpsStatuType;


import com.nrmyw.hud_data_lib.type.speed.HudSpeedingShowBJType;
import com.nrmyw.hud_data_lib.type.speed.HudSpeedingTextType;
import com.nrmyw.hud_data_lib.type.turn.HudTurnBjType;
import com.nrmyw.hud_data_lib.type.turn.HudTurnType;
import com.nrmyw.hud_data_lib.type.type.HudStatuType;

import com.nrmyw.hud_data_lib.type.ui.HudUiType;
import com.nrmyw.hud_data_lib.type.warningproint.HudWarningPointType;
import com.nrmyw.hud_data_lib.type.yellow_statu.HudYellowStatuBjType;

public interface HudEventImp {


    public  void sendBytes(byte[]bytes);

    public void sendTime();


    public void sendNowSpeed(int nowSpeed);

    public void sendNowSpeed(int nowSpeed,int limitedSpeed1 ,int limitedSpeed2);

    public void sendSpeeding(HudSpeedingTextType textColorStyle, HudSpeedingShowBJType speedingShowBJType);



    public void sendIntervalSpeed(int intervalSpeed,int interval ,int averageSpeed,int timeHours,int timeMin);

    public void hideIntervalSpeed();


    public void sendWarningPoint(HudWarningPointType type1	, int distance1);

    public void sendWarningPoint(HudWarningPointType type1	,int distance1,HudWarningPointType type2,int distance2);

    public void sendBigWarningPoint(HudWarningPointType type1	, int distance1);

    public void sendWarningPoint1TitleStr(String str);

    public void sendWarningPoint1BodyStr(String str);

    public void sendWarningPoint2TitleStr(String str);

    public void sendWarningPoint2BodyStr(String str);


    public void sendReachInfo(int distance	, int hours, int	minutes, HudReachType reachType);

//    public void sendReachInfo(int distance	, int hours, int	minutes	, HudTimeType type);

    public void sendReachInfo(String reachInfoStr);

    public void sendLaneHide();

    public void sendLaneInformation(HudLaneCountBean laneCountBean);

    public void sendLaneHiPass(HudLaneHiPassCountBean laneHiPassCountBean);

    public void sendTurnType(HudTurnType type1	, int distance1);
    public void sendTurnType(HudTurnType type1	, int distance1,HudTurnType type2	, int distance2);
    public void setTurnBj(HudTurnBjType turnBj);

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

    public void sendImage(Bitmap bitmap, HudImageType hudImageType);

    public void showImage();

    public void hideImage( );

    public void hideProgressBar();

    public void setYellowStatu(HudYellowStatuBjType hudYellowStatuBjType);

    public void sendYellowStatuStr(String yellowStatuStr);

    public void iconFlicherOpen();

    public void iconFlicherClose();

    public void factorySet();

    public void deviceSoundOpen();

    public void deviceSoundClose();

    public void queDeviceSound();

    public void daylightingStatuOpen();

    public void daylightingStatuClose();



    public void setBleName(String bleName);

    public void setTwsName(String twsName);

    public void setUiType(HudUiType uiType);

}
