package com.nrmyw.hud_data_event_lib.manager;

import android.graphics.Bitmap;

import com.nrmyw.ble_event_lib.bean.BleSendImageInfoBean;
import com.nrmyw.ble_event_lib.send.BleEventSubscriptionSubject;

import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;
import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_event_lib.util.HudBleByteUtil;
import com.nrmyw.hud_data_event_lib.util.HudCmdSendDataUtil;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.image.HudImageType;

public class HudSendManager {

    private static HudSendManager sendManager;

    private HudSendManager(){}

    public static HudSendManager getInstance(){
        if(null==sendManager){
            synchronized (HudSendManager.class){
                if(null==sendManager){
                    sendManager=new HudSendManager();
                }
            }
        }
        return sendManager;
    }

//    private HudCmdListen hudCmdListen;
//    public void setListen(HudCmdListen hudCmdListen){
//        this.hudCmdListen=hudCmdListen;
//    }


    public void sendCmdByte(byte[]bytes){
//        if(null!=hudCmdListen){
//            hudCmdListen.getCmd(bytes);
//        }else {
//
//        }
        BleEventSubscriptionSubject.getInstance().sendCmd(bytes);
    }

    public void sendCmd(HudCmdType hudCmdType, Object... objects){
//        CmdType.useObjectSSetBody(objects);
//        useObjectSSetBody(hudCmdType,objects);
////        BleEventSubscriptionSubject.getInstance().sendCmd(CmdType.getAllByte());
        byte[] bytes=getAllByte(hudCmdType,objects);
//        if(null!=hudCmdListen){
//           hudCmdListen.getCmd(bytes);
//        }else {
//
//        }
        BleEventSubscriptionSubject.getInstance().sendCmd(bytes);
    }

    public void sendBitmap(Bitmap bitmap, HudImageType imageType){
        BleSendImageInfoBean bleSendImageInfoBean=new BleSendImageInfoBean();
        bleSendImageInfoBean.setType(imageType.getType());
        bleSendImageInfoBean.setMaxW(HudSetConfig.getInstance().getImageMaxW());
        bleSendImageInfoBean.setMaxH(HudSetConfig.getInstance().getImageMaxH());
        bleSendImageInfoBean.setBitmap(bitmap);
        switch (imageType){
            case PROGRESS_BAR:
                bleSendImageInfoBean.setBitmapQualityType(HudSetConfig.getInstance().getBleSendProgressQualityType());
                break;
            default:
                bleSendImageInfoBean.setBitmapQualityType(HudSetConfig.getInstance().getBleSendBitmapQualityType());
                break;
        }


        BleEventSubscriptionSubject.getInstance().sendImage(bleSendImageInfoBean);
//        if(null!=sendListen){
//            sendListen.sendImage(bitmap,qualityType);
//        }
    }

    public byte[] getAllByte(HudCmdType cmdType,Object... objects){
        byte title=cmdType.getTitle();
        byte[] body=useObjectSSetBody(cmdType,objects);
        return HudBleByteUtil.useTitleAndBodyGetAllCmdBytes(title,body);
    }

    private byte[] useObjectSSetBody(HudCmdType cmdType,Object... objects) {
        byte[] body=null;
        switch (cmdType) {
            case TIME:
                body = HudCmdSendDataUtil.getTime();
                break;
            case SPEED:
                body = HudCmdSendDataUtil.getSpeed(objects);
                break;
            case SEND_SPEED:
                body = HudCmdSendDataUtil.getSendSpeed(objects);
                break;
            case SPEEDING:
                body = HudCmdSendDataUtil.getSpeeding(objects);
                break;
            case INTERVAL_SPEED:
                body = HudCmdSendDataUtil.getIntervalSpeed(objects);
                break;
            case SEND_WARNING_POINT_SPEED:
                body = HudCmdSendDataUtil.getWpLimitedSpeed(objects);
                break;
            case WARNING_POINT:
            case BIG_WARNING_POINT:
                body = HudCmdSendDataUtil.getWarningPoint(objects);
                break;
            case REACH_INFO:
                body = HudCmdSendDataUtil.getReachInfo(objects);
                break;
            case LANE_INFORMATION:
            case NEW_LANE_INFORMATION:
                body = HudCmdSendDataUtil.getLaneInformation(objects);
                break;
            case TURN_TYPE:
            case NEW_TURN_TYPE:
                body = HudCmdSendDataUtil.getTrunType(objects);
                break;
            case SET_TURN_BJ:
                body =HudCmdSendDataUtil.getTurnBjStyle(objects);
                break;
            case Next_LANE_NAME:
            case YELLOW_STATU_STR:
            case REACH_STR:
            case WARNING_POINT_1_T_STR:
            case WARNING_POINT_1_B_STR:
            case WARNING_POINT_2_T_STR:
            case WARNING_POINT_2_B_STR:
            case SET_BLE_NAME:
            case SET_TWS_NAME:
                body = HudCmdSendDataUtil.getStr(objects);
                break;
            case NOW_LANE_STR:
                body = HudCmdSendDataUtil.getNowLaneStr(objects);
                break;
            case GPS:
                body = HudCmdSendDataUtil.getGPS(objects);
                break;
            case SET_GPS_SPEED:
                body = HudCmdSendDataUtil.getSetGpsSpeed(objects);
                break;
            case SET_BRIGHTNESS:
                body = HudCmdSendDataUtil.getSetBrightness(objects);
                break;
            case SET_SOUND:
                body = HudCmdSendDataUtil.getSetSound(objects);
                break;
            case REWRITE_SN:
                body = HudCmdSendDataUtil.getRewriteSN(objects);
                break;
            case READY_SEND_IMAGE:
                body = HudCmdSendDataUtil.getReadySendImage(objects);
                break;
            case SHOW_IMAGE:
                body = HudCmdSendDataUtil.getShowImage(objects);
                break;
            case FACTORY_SET:
                body = HudCmdSendDataUtil.getFactroySet();
                break;
            case YELLOW_STATU:
                body=HudCmdSendDataUtil.getYellowStatu(objects);
                break;
            case ICON_FLICKER:
            case SET_DEVICE_SOUND_STATU:
            case SET_DAYLIGHTING_SHOW_STATU:
            case SET_BIG_TURN_TYPE_HIDE_AND_SHOW:
                body = HudCmdSendDataUtil.getStatu(objects);
                break;
            case SET_UI:
                body = HudCmdSendDataUtil.getUI(objects);
                break;
            case SET_DISPLAY_RECT_SIZE:
                body = HudCmdSendDataUtil.getDisplayRect(objects);
                break;
        }
        return body;
    }

}
