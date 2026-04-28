package com.nrmyw.hud_data_event_lib.config;

import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;

import java.io.Serializable;

public class HudSetBean implements Serializable {

    private long timeDifference;
    private int imageMaxW=200;
    private int imageMaxH=260;

    private int progressMaxW=22;
    private int progressMaxH=260;

    private boolean isNeedBigWarningPoint=true;

    private boolean autoHideIntervalSpeed=true;

    private boolean autoChangerTrunTypeOldAndNew;

    private boolean isOneShowBigWarningPoint;

    private boolean canNotShowProgress;

    private boolean hideIntervalSpeedUseWarningPointCmd;

    private long hideIntervalSpeedTime;

    private BleSendBitmapQualityType bleSendBitmapQualityType=BleSendBitmapQualityType.ULTRA_LOW;

    private BleSendBitmapQualityType bleSendProgressQualityType=BleSendBitmapQualityType.PROGRESS;


    public long getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(long timeDifference) {
        this.timeDifference = timeDifference;
    }

    public BleSendBitmapQualityType getBleSendProgressQualityType() {
        return bleSendProgressQualityType;
    }

    public void setBleSendProgressQualityType(BleSendBitmapQualityType bleSendProgressQualityType) {
        if(null==bleSendProgressQualityType){
            return;
        }
        this.bleSendProgressQualityType = bleSendProgressQualityType;
    }

    public BleSendBitmapQualityType getBleSendBitmapQualityType() {
        return bleSendBitmapQualityType;
    }

    public void setBleSendBitmapQualityType(BleSendBitmapQualityType bleSendBitmapQualityType) {
        if(null==bleSendBitmapQualityType){
            return;
        }
        this.bleSendBitmapQualityType = bleSendBitmapQualityType;
    }

    public boolean isHideIntervalSpeedUseWarningPointCmd() {
        return hideIntervalSpeedUseWarningPointCmd;
    }

    public void setHideIntervalSpeedUseWarningPointCmd(boolean hideIntervalSpeedUseWarningPointCmd) {
        this.hideIntervalSpeedUseWarningPointCmd = hideIntervalSpeedUseWarningPointCmd;
    }

    public boolean isCanNotShowProgress() {
        return canNotShowProgress;
    }

    public void setCanNotShowProgress(boolean canNotShowProgress) {
        this.canNotShowProgress = canNotShowProgress;
    }

    public boolean isOneShowBigWarningPoint() {
        return isOneShowBigWarningPoint;
    }

    public void setOneShowBigWarningPoint(boolean oneShowBigWarningPoint) {
        isOneShowBigWarningPoint = oneShowBigWarningPoint;
    }

    public boolean isAutoChangerTrunTypeOldAndNew() {
        return autoChangerTrunTypeOldAndNew;
    }

    public void setAutoChangerTrunTypeOldAndNew(boolean autoChangerTrunTypeOldAndNew) {
        this.autoChangerTrunTypeOldAndNew = autoChangerTrunTypeOldAndNew;
    }

    public boolean isAutoHideIntervalSpeed() {
        return autoHideIntervalSpeed;
    }

    public void setAutoHideIntervalSpeed(boolean autoHideIntervalSpeed) {
        this.autoHideIntervalSpeed = autoHideIntervalSpeed;
    }

    public boolean isNeedBigWarningPoint() {
        return isNeedBigWarningPoint;
    }

    public void setNeedBigWarningPoint(boolean needBigWarningPoint) {
        isNeedBigWarningPoint = needBigWarningPoint;
    }

    public int getProgressMaxH() {
        return progressMaxH;
    }

    public void setProgressMaxH(int progressMaxH) {
        this.progressMaxH = progressMaxH;
    }

    public int getProgressMaxW() {
        return progressMaxW;
    }

    public void setProgressMaxW(int progressMaxW) {
        this.progressMaxW = progressMaxW;
    }

    public int getImageMaxH() {
        return imageMaxH;
    }

    public void setImageMaxH(int imageMaxH) {
        this.imageMaxH = imageMaxH;
    }

    public int getImageMaxW() {
        return imageMaxW;
    }

    public void setImageMaxW(int imageMaxW) {
        this.imageMaxW = imageMaxW;
    }

    public long getHideIntervalSpeedTime() {
        if(hideIntervalSpeedTime<10*1000){
            hideIntervalSpeedTime=10*1000;
        }
        return hideIntervalSpeedTime;
    }

    public void setHideIntervalSpeedTime(long hideIntervalSpeedTime) {
        if(hideIntervalSpeedTime<10*1000){
            return;
        }
        this.hideIntervalSpeedTime = hideIntervalSpeedTime;
    }

    @Override
    public String toString() {
        return "HudSetBean{" +
                "timeDifference=" + timeDifference +
                ", imageMaxW=" + imageMaxW +
                ", imageMaxH=" + imageMaxH +
                ", progressMaxW=" + progressMaxW +
                ", progressMaxH=" + progressMaxH +
                ", isNeedBigWarningPoint=" + isNeedBigWarningPoint +
                ", autoHideIntervalSpeed=" + autoHideIntervalSpeed +
                ", autoChangerTrunTypeOldAndNew=" + autoChangerTrunTypeOldAndNew +
                ", isOneShowBigWarningPoint=" + isOneShowBigWarningPoint +
                ", canNotShowProgress=" + canNotShowProgress +
                ", hideIntervalSpeedUseWarningPointCmd=" + hideIntervalSpeedUseWarningPointCmd +
                ", hideIntervalSpeedTime=" + hideIntervalSpeedTime +
                ", bleSendBitmapQualityType=" + bleSendBitmapQualityType +
                ", bleSendProgressQualityType=" + bleSendProgressQualityType +
                '}';
    }
}
