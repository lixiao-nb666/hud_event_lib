package com.nrmyw.hud_data_event_lib.config;

import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;
import com.nrmyw.hud_data_lib.type.send_data.HudDeviceSendDataType;


public class HudSetConfig {

    private static HudSetConfig hudSetConfig;

    private HudDeviceSendDataType deviceSendDataType=HudDeviceSendDataType.T700_GUI3;
    private HudSetBean hudSetBean=new HudSetBean();
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

    public HudDeviceSendDataType getDeviceSendDataType() {
        if(null==this.deviceSendDataType){
            this.deviceSendDataType=HudDeviceSendDataType.T700_GUI3;
        }
        return deviceSendDataType;
    }

    public void setDeviceSendDataType(HudDeviceSendDataType deviceSendDataType) {
        if(null==deviceSendDataType){
            return;
        }
        this.deviceSendDataType = deviceSendDataType;
    }

    public HudSetBean getHudSetBean(){
        return hudSetBean;
    }

    public void getHudSetBean(HudSetBean hudSetBean){
        if(null==hudSetBean){
            return;
        }
        this.hudSetBean=hudSetBean;
    }

    public void setTimeDifference(long timeDifference) {
        hudSetBean.setTimeDifference(timeDifference);

    }

    public long getTimeDifference() {
        return hudSetBean.getTimeDifference();
    }

    public int getImageMaxW() {
        return hudSetBean.getImageMaxW();
    }

    public void setImageMaxW(int imageMaxW) {
        hudSetBean.setImageMaxW(imageMaxW);

    }

    public int getImageMaxH() {
        return hudSetBean.getImageMaxH();
    }

    public void setImageMaxH(int imageMaxH) {

        hudSetBean.setImageMaxH(imageMaxH);
    }

    public BleSendBitmapQualityType getBleSendBitmapQualityType() {
        return hudSetBean.getBleSendBitmapQualityType();
    }

    public void setBleSendBitmapQualityType(BleSendBitmapQualityType bleSendBitmapQualityType) {

        hudSetBean.setBleSendBitmapQualityType(bleSendBitmapQualityType);

    }

    public BleSendBitmapQualityType getBleSendProgressQualityType() {
        return hudSetBean.getBleSendProgressQualityType();
    }

    public void setBleSendProgressQualityType(BleSendBitmapQualityType bleSendProgressQualityType) {
        hudSetBean.setBleSendProgressQualityType(bleSendProgressQualityType);

    }

    public int getProgressMaxW() {
        return hudSetBean.getProgressMaxW();
    }

    public void setProgressMaxW(int progressMaxW) {
        hudSetBean.setProgressMaxW(progressMaxW);

    }

    public int getProgressMaxH() {
        return hudSetBean.getProgressMaxH();
    }

    public void setProgressMaxH(int progressMaxH) {
        this.hudSetBean.setProgressMaxH(progressMaxH);

    }

    public boolean isAutoChangerTrunTypeOldAndNew() {
        return hudSetBean.isAutoChangerTrunTypeOldAndNew();
    }

    public void setAutoChangerTrunTypeOldAndNew(boolean autoChangerTrunTypeOldAndNew) {
        hudSetBean.setAutoChangerTrunTypeOldAndNew(autoChangerTrunTypeOldAndNew);
    }

    public boolean isOneShowBigWarningPoint() {
        return hudSetBean.isOneShowBigWarningPoint();
    }

    public void setOneShowBigWarningPoint(boolean oneShowBigWarningPoint) {
     hudSetBean.setOneShowBigWarningPoint(oneShowBigWarningPoint);
    }





    public boolean isNeedBigWarningPoint() {
        return hudSetBean.isNeedBigWarningPoint();
    }

    public void setNeedBigWarningPoint(boolean needBigWarningPoint) {
        hudSetBean.setNeedBigWarningPoint(needBigWarningPoint);
    }

    public boolean isCanNotShowProgress() {
        return hudSetBean.isCanNotShowProgress();
    }

    public void setCanNotShowProgress(boolean canNotShowProgress) {
        hudSetBean.setCanNotShowProgress(canNotShowProgress);
    }


    public boolean isHideIntervalSpeedUseWarningPointCmd() {
        return hudSetBean.isHideIntervalSpeedUseWarningPointCmd();
    }

    public void setHideIntervalSpeedUseWarningPointCmd(boolean hideIntervalSpeedUseWarningPointCmd) {
        hudSetBean.setHideIntervalSpeedUseWarningPointCmd(hideIntervalSpeedUseWarningPointCmd);
    }

    public boolean isAutoHideIntervalSpeed() {
        return hudSetBean.isAutoHideIntervalSpeed();
    }

    public void setAutoHideIntervalSpeed(boolean autoHideIntervalSpeed) {
        hudSetBean.setAutoHideIntervalSpeed(autoHideIntervalSpeed);
    }

    @Override
    public String toString() {
        return "HudSetConfig{" +
                "deviceSendDataType=" + deviceSendDataType +
                ", hudSetBean=" + hudSetBean +
                '}';
    }
}
