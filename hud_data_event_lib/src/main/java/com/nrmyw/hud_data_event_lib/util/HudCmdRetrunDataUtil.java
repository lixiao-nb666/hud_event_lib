package com.nrmyw.hud_data_event_lib.util;


import com.nrmyw.hud_data_lib.event.retrun.HudRetrunEventSubscriptionSubject;
import com.nrmyw.hud_data_lib.type.HudCmdRetrunType;

import java.nio.charset.StandardCharsets;

public class HudCmdRetrunDataUtil {


    private static long dataLen = 1024;
    private static byte[] legalBuf = new byte[1024];

    public static void doVue(byte[] retrunBytes){

        byte[] value = retrunBytes;
        int index=0;
        for (int i = 0; i < value.length; i++) {
            legalBuf[index] = value[i];
            index++;
            if (index == 4) {
                dataLen =  (legalBuf[2] << 8) + legalBuf[3];   //总数据长度
            }
            if (index == dataLen ) {   /*数据检测完成，可使用*/
                doProcessReceiveData(legalBuf, index);
                index = 0;
                dataLen = 1024;
            }
        }
    }

    private static void doProcessReceiveData(byte[] receiveData, int len){
//            int cmd = receiveData[4] & 0xFF;
            //返回参数data
            byte[] data = new byte[len - 5];
            for (int i = 5; i < len; i++) {
                data[i - 5] = receiveData[i];
            }
            byte cmdType=receiveData[4];
            HudCmdRetrunType hudCmdRetrunType=HudCmdRetrunType.useByteGetRetrunType(cmdType);
            switch (hudCmdRetrunType){
                case GET_BRIGHTNESS:
                    int mode=data[0];
                    int autoBright=data[1];
                    int handBright = data[2];
                    HudRetrunEventSubscriptionSubject.getInstence().getBrightnessV(mode,autoBright,handBright);
                    break;
                case GET_SOUND:
                    int sound = data[0];
                    HudRetrunEventSubscriptionSubject.getInstence().getSoundV(sound);
                    break;
                case GET_SN:
                    String sn= new String(data, StandardCharsets.UTF_8);
//                    String sn=BleByteUtil.parseByte2HexStr(data);
                    HudRetrunEventSubscriptionSubject.getInstence().getSN(sn);
                    break;
                case GET_DEVICE_VERSION:
                    String deviceVersion= new String(data, StandardCharsets.UTF_8);
                    HudRetrunEventSubscriptionSubject.getInstence().getDeviceVersion(deviceVersion);
                    break;
                case GET_GPS_SPEED:
                    int gpsSpeed = data[0];
                    HudRetrunEventSubscriptionSubject.getInstence().getGPSSpeed(gpsSpeed);
                    break;
                case GET_ACK_SIZE:
                    break;
                case GET_DEVICE_SOUND_STATU:
                    int deviceSoundType =data[0];
                    HudRetrunEventSubscriptionSubject.getInstence().getDeviceSoundStatu(deviceSoundType);
                    break;
                case NONE:
                default:
                    HudRetrunEventSubscriptionSubject.getInstence().none(receiveData);
                    break;
            }

//            LG.e("接收--->端口解析数据11： " + parseByte2HexStr(data) );
//            if (cmd == 0xa2){
//                //查询声音大小
//                int voice = data[0];
//                Message message = mUIHandler.obtainMessage(REC_VOICE);
//                message.obj = voice;
//                message.sendToTarget();
//            }else if (cmd == 0xa4){
//                //查询亮度大小
//                int bright = data[0];
//                Message message = mUIHandler.obtainMessage(REC_BRIGHT);
//                message.obj = bright;
//                message.sendToTarget();
//            }else if(cmd == 0xa6){
//                //查询开关状态
//                int type  = data[0];
//                int state = data[1];
//                Log.e(TAG, "processReceiveData: type:" + type + "state:" + state );
//                Message message = mUIHandler.obtainMessage(REC_SWITCH_INFO);
//                message.obj = state;
//                message.arg1 = type;
//                message.sendToTarget();
//            }else if(cmd == 0xa7){
//                //查询SN
//                String SNString = parseByte2HexStr(data);
//                Log.e(TAG, "processReceiveData: SN:" + SNString );
//                Message message = mUIHandler.obtainMessage(REC_SN_INFO);
//                message.obj = SNString;
//                message.sendToTarget();
//            }
    }
}
