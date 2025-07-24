package com.nrmyw.hud_data_event_lib.manager;

import android.graphics.Bitmap;

import com.nrmyw.ble_event_lib.bean.BleSendImageInfoBean;
import com.nrmyw.ble_event_lib.send.BleEventSubscriptionSubject;
import com.nrmyw.ble_event_lib.util.BleByteUtil;
import com.nrmyw.hud_data_event_lib.HudCmdListen;
import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_event_lib.util.HudBleByteUtil;
import com.nrmyw.hud_data_event_lib.util.HudCmdSendDataUtil;
import com.nrmyw.hud_data_lib.type.HudCmdType;

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

    private HudCmdListen hudCmdListen;
    public void setListen(HudCmdListen hudCmdListen){
        this.hudCmdListen=hudCmdListen;
    }


    public void sendCmdByte(byte[]bytes){
        if(null!=hudCmdListen){
            hudCmdListen.getCmd(bytes);
        }else {

        }
        BleEventSubscriptionSubject.getInstance().sendCmd(bytes);
    }

    public void sendCmd(HudCmdType hudCmdType, Object... objects){
//        CmdType.useObjectSSetBody(objects);
//        useObjectSSetBody(hudCmdType,objects);
////        BleEventSubscriptionSubject.getInstance().sendCmd(CmdType.getAllByte());
        byte[] bytes=getAllByte(hudCmdType,objects);
        if(null!=hudCmdListen){
           hudCmdListen.getCmd(bytes);
        }else {
            BleEventSubscriptionSubject.getInstance().sendCmd(bytes);
        }
    }

    public void sendBitmap(Bitmap bitmap){
        BleSendImageInfoBean bleSendImageInfoBean=new BleSendImageInfoBean();
        bleSendImageInfoBean.setMaxW(HudSetConfig.getInstance().getImageMaxW());
        bleSendImageInfoBean.setMaxH(HudSetConfig.getInstance().getImageMaxH());
        bleSendImageInfoBean.setBitmap(bitmap);
        bleSendImageInfoBean.setBitmapQualityType(HudSetConfig.getInstance().getBleSendBitmapQualityType());
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
            case SPEEDING:
                body = HudCmdSendDataUtil.getSpeeding(objects);
                break;
            case INTERVAL_SPEED:
                body = HudCmdSendDataUtil.getIntervalSpeed(objects);
                break;
            case WARNING_POINT:
            case BIG_WARNING_POINT:
                body = HudCmdSendDataUtil.getWarningPoint(objects);
                break;
            case REACH_INFO:
                body = HudCmdSendDataUtil.getReachInfo(objects);
                break;
            case LANE_INFORMATION:
                body = HudCmdSendDataUtil.getLaneInformation(objects);
                break;
            case TURN_TYPE:
                body = HudCmdSendDataUtil.getTrunType(objects);
                break;
            case Next_LANE_NAME:
            case YELLOW_STATU_STR:
            case WARNING_POINT_1_T_STR:
            case WARNING_POINT_1_B_STR:
            case WARNING_POINT_2_T_STR:
            case WARNING_POINT_2_B_STR:
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
            case ICON_FLICKER:
            case SET_DEVICE_SOUND_STATU:
            case SET_DAYLIGHTING_SHOW_STATU:
                body = HudCmdSendDataUtil.getStatu(objects);
                break;
            case SET_SPEEDING_BJ:
            case SET_LANE_BJ:
            case SET_YELLOW_STATU_BJ:
                body=HudCmdSendDataUtil.getStyle(objects);
                break;
        }
        return body;
    }

}
