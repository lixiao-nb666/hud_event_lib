package com.nrmyw.hud_data_event_lib.config;

import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;

public class HudSetConfig {

    private static HudSetConfig hudSetConfig;
    private int imageMaxW=200;
    private int imageMaxH=260;
    private BleSendBitmapQualityType bleSendBitmapQualityType=BleSendBitmapQualityType.ULTRA_LOW;
    private long timeDifference;
    private HudSetConfig(){}

    public static HudSetConfig getInstance(){
        if(null==hudSetConfig){
            synchronized (HudSetConfig.class){
                if(null==hudSetConfig){
                    hudSetConfig=new HudSetConfig();
                }
            }
        }
        return hudSetConfig;
    }



    public void setTimeDifference(long timeDifference) {
        this.timeDifference=timeDifference;
    }

    public long getTimeDifference() {
        return timeDifference;
    }

    public int getImageMaxW() {
        return imageMaxW;
    }

    public void setImageMaxW(int imageMaxW) {
        this.imageMaxW = imageMaxW;
    }

    public int getImageMaxH() {
        return imageMaxH;
    }

    public void setImageMaxH(int imageMaxH) {
        this.imageMaxH = imageMaxH;
    }

    public BleSendBitmapQualityType getBleSendBitmapQualityType() {
        return bleSendBitmapQualityType;
    }

    public void setBleSendBitmapQualityType(BleSendBitmapQualityType bleSendBitmapQualityType) {
        this.bleSendBitmapQualityType = bleSendBitmapQualityType;
    }

    @Override
    public String toString() {
        return "HudSetConfig{" +
                "imageMaxW=" + imageMaxW +
                ", imageMaxH=" + imageMaxH +
                ", bleSendBitmapQualityType=" + bleSendBitmapQualityType +
                '}';
    }
}
