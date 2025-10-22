package com.nrmyw.hud_data_event_lib.util;

public class HudSendDataCheckUtil {

    public static int getSpeed(int nowSpeed){
        if(nowSpeed<0){
            return 0;
        }else if (nowSpeed>255){
            return 255;
        }else {
            return nowSpeed;
        }
    }

    public static int getDis(int  dis){
        if(dis<0){
            return 0;
        }else if(dis>9999000){
            return 9999000;
        }else {
            return dis;
        }
    }




    public static int getGpsSpeed(int nowGpsSpeed){
        if(nowGpsSpeed<0){
            return 0;
        }else if (nowGpsSpeed>10){
            return 10;
        }else {
            return nowGpsSpeed;
        }
    }

    public static int getSoundV(int nowSoundV){
        if(nowSoundV<0){
            return 0;
        }else if (nowSoundV>10){
            return 10;
        }else {
            return nowSoundV;
        }
    }


    public static int getBrightnessV(int nowBrightnessV){
        if(nowBrightnessV<1){
            return 1;
        }else if (nowBrightnessV>10){
            return 10;
        }else {
            return nowBrightnessV;
        }
    }


    public static int getDisplayRectV(int nowDisplayV){
        if(nowDisplayV<0){
            return 0;
        }else if(nowDisplayV>60){
            return 60;
        }else {
            return nowDisplayV;
        }
    }


}
