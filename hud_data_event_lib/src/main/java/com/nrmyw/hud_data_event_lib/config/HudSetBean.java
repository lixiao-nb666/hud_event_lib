package com.nrmyw.hud_data_event_lib.config;

import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;

import java.io.Serializable;

public class HudSetBean implements Serializable {

    private boolean isDJ;
    private long timeDifference;
    private int imageMaxW=200;
    private int imageMaxH=260;

    private int progressMaxW=22;
    private int progressMaxH=260;

    private boolean isNeedBigWarningPoint=true;

    private boolean autoHideIntervalSpeed=true;

    private long hideIntervalSpeedTime;

    private boolean hideIntervalSpeedUseWarningPointCmd;

    private boolean autoChangerTrunTypeOldAndNew;

    private boolean isOneShowBigWarningPoint;

    private boolean ifNoneWarningPointOnlyShowFirst=true;

    private boolean canNotShowProgress;

    private int turnTypeStrMinL=6;
    private int turnTypeStrMaxL=16;

    private int laneNameStrMinL=8;

    public boolean isIfNoneWarningPointOnlyShowFirst() {
        return ifNoneWarningPointOnlyShowFirst;
    }

    public void setIfNoneWarningPointOnlyShowFirst(boolean ifNoneWarningPointOnlyShowFirst) {
        this.ifNoneWarningPointOnlyShowFirst = ifNoneWarningPointOnlyShowFirst;
    }

    private int laneNameStrMaxL=16;
    private  int ShowMaxNumb=20;

    private boolean turnStrAddNull=true;
    private boolean notifictionStrAddNull=false;

    private boolean nowLaneStrAddNull=true;

    private boolean needReShowWarningPoint=true;
    private BleSendBitmapQualityType bleSendBitmapQualityType=BleSendBitmapQualityType.ULTRA_LOW;

    private BleSendBitmapQualityType bleSendProgressQualityType=BleSendBitmapQualityType.PROGRESS;

    private boolean canShowBigTurnType;


    public boolean isDJ() {
        return isDJ;
    }

    public void setDJ(boolean DJ) {
        isDJ = DJ;
    }

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

    public int getTurnTypeStrMinL() {
        return turnTypeStrMinL;
    }

    public void setTurnTypeStrMinL(int turnTypeStrMinL) {
        this.turnTypeStrMinL = turnTypeStrMinL;
    }

    public int getLaneNameStrMaxL() {
        return laneNameStrMaxL;
    }

    public void setLaneNameStrMaxL(int laneNameStrMaxL) {
        this.laneNameStrMaxL = laneNameStrMaxL;
    }

    public int getLaneNameStrMinL() {
        return laneNameStrMinL;
    }

    public void setLaneNameStrMinL(int laneNameStrMinL) {
        this.laneNameStrMinL = laneNameStrMinL;
    }

    public int getTurnTypeStrMaxL() {
        return turnTypeStrMaxL;
    }

    public void setTurnTypeStrMaxL(int turnTypeStrMaxL) {
        this.turnTypeStrMaxL = turnTypeStrMaxL;
    }

    public int getShowMaxNumb() {
        return ShowMaxNumb;
    }

    public void setShowMaxNumb(int showMaxNumb) {
        ShowMaxNumb = showMaxNumb;
    }

    public boolean isNotifictionStrAddNull() {
        return notifictionStrAddNull;
    }

    public void setNotifictionStrAddNull(boolean notifictionStrAddNull) {
        this.notifictionStrAddNull = notifictionStrAddNull;
    }

    public boolean isTurnStrAddNull() {
        return turnStrAddNull;
    }

    public void setTurnStrAddNull(boolean turnStrAddNull) {
        this.turnStrAddNull = turnStrAddNull;
    }

    public boolean isNowLaneStrAddNull() {
        return nowLaneStrAddNull;
    }

    public void setNowLaneStrAddNull(boolean nowLaneStrAddNull) {
        this.nowLaneStrAddNull = nowLaneStrAddNull;
    }

    public boolean isNeedReShowWarningPoint() {
        return needReShowWarningPoint;
    }

    public void setNeedReShowWarningPoint(boolean needReShowWarningPoint) {
        this.needReShowWarningPoint = needReShowWarningPoint;
    }

    public boolean isCanShowBigTurnType() {
        return canShowBigTurnType;
    }

    public void setCanShowBigTurnType(boolean canShowBigTurnType) {
        this.canShowBigTurnType = canShowBigTurnType;
    }

    @Override
    public String toString() {
        return "HudSetBean{" +
                "isDJ=" + isDJ +
                ", timeDifference=" + timeDifference +
                ", imageMaxW=" + imageMaxW +
                ", imageMaxH=" + imageMaxH +
                ", progressMaxW=" + progressMaxW +
                ", progressMaxH=" + progressMaxH +
                ", isNeedBigWarningPoint=" + isNeedBigWarningPoint +
                ", autoHideIntervalSpeed=" + autoHideIntervalSpeed +
                ", hideIntervalSpeedTime=" + hideIntervalSpeedTime +
                ", hideIntervalSpeedUseWarningPointCmd=" + hideIntervalSpeedUseWarningPointCmd +
                ", autoChangerTrunTypeOldAndNew=" + autoChangerTrunTypeOldAndNew +
                ", isOneShowBigWarningPoint=" + isOneShowBigWarningPoint +
                ", ifNoneWarningPointOnlyShowFirst=" + ifNoneWarningPointOnlyShowFirst +
                ", canNotShowProgress=" + canNotShowProgress +
                ", turnTypeStrMinL=" + turnTypeStrMinL +
                ", turnTypeStrMaxL=" + turnTypeStrMaxL +
                ", laneNameStrMinL=" + laneNameStrMinL +
                ", laneNameStrMaxL=" + laneNameStrMaxL +
                ", ShowMaxNumb=" + ShowMaxNumb +
                ", turnStrAddNull=" + turnStrAddNull +
                ", notifictionStrAddNull=" + notifictionStrAddNull +
                ", nowLaneStrAddNull=" + nowLaneStrAddNull +
                ", needReShowWarningPoint=" + needReShowWarningPoint +
                ", bleSendBitmapQualityType=" + bleSendBitmapQualityType +
                ", bleSendProgressQualityType=" + bleSendProgressQualityType +
                ", canShowBigTurnType=" + canShowBigTurnType +
                '}';
    }
}
