package com.nrmyw.hud_data_event_lib.util;



import com.nrmyw.ble_event_lib.util.BleByteUtil;
import com.nrmyw.hud_data_event_lib.config.HudSetConfig;
import com.nrmyw.hud_data_lib.bean.HudLaneCountBean;
import com.nrmyw.hud_data_lib.bean.HudLaneHiPassCountBean;

import com.nrmyw.hud_data_lib.type.image.HudImageShowType;
import com.nrmyw.hud_data_lib.type.image.HudSendImageType;
import com.nrmyw.hud_data_lib.type.lane.HudLaneInformationType;
import com.nrmyw.hud_data_lib.type.lane.HudLaneType;
import com.nrmyw.hud_data_lib.type.lane.HudNowLaneStrType;
import com.nrmyw.hud_data_lib.type.reach.HudReachType;
import com.nrmyw.hud_data_lib.type.set.HudBrightnessMoldType;
import com.nrmyw.hud_data_lib.type.set.HudGpsStatuType;

import com.nrmyw.hud_data_lib.type.speed.HudSpeedingShowBJType;
import com.nrmyw.hud_data_lib.type.speed.HudSpeedingTextType;

import com.nrmyw.hud_data_lib.type.speed.SpeedType;
import com.nrmyw.hud_data_lib.type.turn.HudTurnBjType;
import com.nrmyw.hud_data_lib.type.turn.HudTurnType;

import com.nrmyw.hud_data_lib.type.type.HudStatuType;
import com.nrmyw.hud_data_lib.type.ui.HudUiType;
import com.nrmyw.hud_data_lib.type.warningproint.HudWarningPointType;
import com.nrmyw.hud_data_lib.type.yellow_statu.HudYellowStatuBjType;

import java.nio.charset.StandardCharsets;

public class HudCmdSendDataUtil {


    public static byte[] getTime(){
        int timeStamp = (int)(System.currentTimeMillis()/1000);
        timeStamp+= HudSetConfig.getInstance().getTimeDifference();
        byte[] byteTimeStamp = BleByteUtil.intToByteArray32(timeStamp);
        return byteTimeStamp;
    }


    public static byte[] getSpeed(Object... objects){
        if(null==objects||objects.length==0){
            return null;
        }
        if(objects.length==1){
            int nowSpeed= (int) objects[0];
            return new byte[]{
                    (byte)(nowSpeed & 0xFF)
            };
        }else if (objects.length==3){
            int nowSpeed= (int) objects[0];
            int limitedSpeed1=(int) objects[1];
            int limitedSpeed2=(int) objects[2];
            return new byte[]{
                    (byte)(nowSpeed & 0xFF),
                    BleByteUtil.intToByteArray32only1(limitedSpeed1)[0],
                    BleByteUtil.intToByteArray32only1(limitedSpeed2)[0]
            };
        }
        return null;
    }


    public static byte[] getWpLimitedSpeed(Object... objects){
        if(null==objects||objects.length==0){
            return null;
        }
        if (objects.length==2){
            int limitedSpeed1=(int) objects[0];
            int limitedSpeed2=(int) objects[1];
            return new byte[]{
                    BleByteUtil.intToByteArray32only1(limitedSpeed1)[0],
                    BleByteUtil.intToByteArray32only1(limitedSpeed2)[0]
            };
        }
        return null;
    }

    public static byte[] getSendSpeed(Object... objects){
        if(null==objects||objects.length==0){
            return null;
        }
        if(objects.length==2){
            int nowSpeed= (int) objects[0];
            SpeedType speedType= (SpeedType) objects[1];
            return new byte[]{
                    (byte)(nowSpeed & 0xFF),
                    speedType.getType()
            };
        }
        return null;
    }

    public static byte[] getSpeeding(Object... objects){
        if(null==objects||objects.length<2){
            return null;
        }
        HudSpeedingTextType speedingShowType= (HudSpeedingTextType) objects[0];
        HudSpeedingShowBJType speedingShowBJType= (HudSpeedingShowBJType) objects[1];
        return new byte[]{
                speedingShowType.getType(),
                speedingShowBJType.getType()
        };
    }

    public static byte[] getIntervalSpeed(Object... objects){
        if(null==objects||objects.length<5){
            return null;
        }
        int intervalSpeed= (int) objects[0];
        int interval= (int) objects[1];
        byte[] intervalBytes=BleByteUtil.intToByteArray32only3(interval);
        int averageSpeed=(int) objects[2];
        int timeHours =(int) objects[3];
        int timeMin=(int) objects[4];
        byte[] bytes= new byte[7];
        bytes[0]=(byte)(intervalSpeed & 0xFF);
        bytes[1]=intervalBytes[0];
        bytes[2]=intervalBytes[1];
        bytes[3]=intervalBytes[2];
        bytes[4]= (byte)(averageSpeed & 0xFF);
        bytes[5]=(byte)(timeHours & 0xFF);
        bytes[6]= (byte)(timeMin & 0xFF);
        return bytes;
    }

    public static byte[] getWarningPoint(Object... objects){
        if(null==objects||objects.length<2){
            return null;
        }
        HudWarningPointType type1	= (HudWarningPointType) objects[0];
        int distance1= (int) objects[1];
        byte[] distance1bytes=BleByteUtil.intToByteArray32only3(distance1);
        byte[] bytes=null;
        if(objects.length>=4){
            bytes=new byte[8];
            HudWarningPointType type2	= (HudWarningPointType) objects[2];
            int distance2= (int) objects[3];
            byte[] distance2bytes=BleByteUtil.intToByteArray32only3(distance2);
            bytes[0]=type1.getType();
            bytes[1]=distance1bytes[0];
            bytes[2]=distance1bytes[1];
            bytes[3]=distance1bytes[2];
            bytes[4]=type2.getType();
            bytes[5]=distance2bytes[0];
            bytes[6]=distance2bytes[1];
            bytes[7]=distance2bytes[2];
        }else {
            bytes=new byte[4];
            bytes[0]=type1.getType();
            bytes[1]=distance1bytes[0];
            bytes[2]=distance1bytes[1];
            bytes[3]=distance1bytes[2];
        }
        return bytes;
    }


    public static byte[] getReachInfo(Object... objects){
        if(null==objects||objects.length<3){
            return null;
        }
        byte[] bytes=null;
        HudReachType type=null;
        if(objects.length==3){
            bytes=new byte[5];
        }else {
            bytes=new byte[6];
            type= (HudReachType) objects[3];
            bytes[5]= type.getType();
        }
        int distance= (int) objects[0];
        byte[] distanceBytes= BleByteUtil.intToByteArray32only3(distance);
        int hours= (int) objects[1];
        int	minutes= (int) objects[2];
        bytes[0]=distanceBytes[0];
        bytes[1]=distanceBytes[1];
        bytes[2]=distanceBytes[2];
        bytes[3]= (byte)(hours & 0xFF);
        bytes[4]=(byte)(minutes & 0xFF);
        return bytes;
    }


    public static boolean autoGetHipassL=true;

    public static byte[] getLaneInformation(Object... objects){
        if(null==objects||objects.length<2){
            //发送3个以下就是不显示
            return hideLane();
        }
        HudLaneInformationType laneInformationType= (HudLaneInformationType) objects[0];
        if(laneInformationType==HudLaneInformationType.HI_PASS){
            HudLaneHiPassCountBean laneHiPassCountBean= (HudLaneHiPassCountBean) objects[1];
            int line= (int) objects[2];
            return getHiPassLaneInfo(laneHiPassCountBean,line);
        }else if(laneInformationType==HudLaneInformationType.DEF){
            HudLaneCountBean laneCountBean= (HudLaneCountBean) objects[1];
            return getDefLaneInfo(laneCountBean);
        }else {
            return hideLane();
        }
    }

    private static byte[] hideLane(){
        return new byte[]{
                HudLaneInformationType.HIDE.getType(),
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
        };
    }

    private static byte[] getDefLaneInfo(HudLaneCountBean laneCountBean){
        int count=laneCountBean.getLaneList().size();
        byte[] bytes=new byte[4+count];
        bytes[0]=HudLaneInformationType.DEF.getType();
        bytes[1]=(byte)(count & 0xFF);
        for(int i=0;i<laneCountBean.getLaneList().size();i++) {
            HudLaneType laneType = laneCountBean.getLaneList().get(i);
            bytes[2 + i] = laneType.getType();
        }
        bytes[bytes.length-2]=   (byte) (laneCountBean.getLeftIndex()& 0xFF);
        bytes[bytes.length-1]=   (byte) (laneCountBean.getRightIndex()& 0xFF);
        return bytes;
    }

    private static byte[] getHiPassLaneInfo(HudLaneHiPassCountBean laneHiPassCountBean,int line){
        int byteL;
        if(autoGetHipassL){
            byteL=laneHiPassCountBean.getLaneList().size()+4;
        }else {
            byteL =12;
        }
        byte[] bytes=new byte[byteL];
        bytes[0]=HudLaneInformationType.HI_PASS.getType();
        int count=laneHiPassCountBean.getLaneList().size()+1;
        bytes[1]=(byte)(count & 0xFF);
        bytes[byteL-1]=(byte)(line & 0xFF);
        for(int i=0;i<count;i++){
            if(i<laneHiPassCountBean.getLaneList().size()){
                int needType=laneHiPassCountBean.getLaneList().get(i);
                if(needType==0){
                    bytes[2+i]=0x00;
                }else if(needType==-1){
                    bytes[2+i]=0x2E;
                }else {
                    bytes[2+i]=(byte)( needType& 0xFF);
                }
            }else {
                bytes[2+i]=(byte)0x00;
            }
        }
        return bytes;
    }


    public static byte[] getTrunType(Object... objects) {
        if (null == objects || objects.length < 2) {
            return null;
        }
        if(null==objects||objects.length<2){
            return null;
        }
        HudTurnType type1	= (HudTurnType) objects[0];
        int distance1= (int) objects[1];
        byte[] distance1bytes=BleByteUtil.intToByteArray32only3(distance1);
        byte[] bytes;
        if(objects.length>=4){
            bytes=new byte[8];
            HudTurnType type2	= (HudTurnType) objects[2];
            int distance2= (int) objects[3];
            byte[] distance2bytes=BleByteUtil.intToByteArray32only3(distance2);
            bytes[0]=type1.getType();
            bytes[1]=distance1bytes[0];
            bytes[2]=distance1bytes[1];
            bytes[3]=distance1bytes[2];
            bytes[4]=type2.getType();
            bytes[5]=distance2bytes[0];
            bytes[6]=distance2bytes[1];
            bytes[7]=distance2bytes[2];
        }else {
            bytes=new byte[4];
            bytes[0]=type1.getType();
            bytes[1]=distance1bytes[0];
            bytes[2]=distance1bytes[1];
            bytes[3]=distance1bytes[2];
        }
        return bytes;
    }



    public static byte[] getNowLaneStr(Object... objects) {
        if (null == objects || objects.length < 2) {
            return null;
        }
        HudNowLaneStrType nowLaneStrType= (HudNowLaneStrType) objects[0];
        String name= (String) objects[1];
        byte[] nameBytes=name.getBytes(StandardCharsets.UTF_8);
        byte[] bytes=new byte[nameBytes.length+2];
        bytes[0]=nowLaneStrType.getType();
        bytes[1]=(byte)(nameBytes.length & 0xFF);
        for(int i=0;i<nameBytes.length;i++){
            bytes[i+2]=nameBytes[i];
        }
        return bytes;
    }


    public static byte[] getGPS(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        HudGpsStatuType gpsStatuType= (HudGpsStatuType) objects[0];

        return new byte[]{
                gpsStatuType.getType()
        };
    }

    public static byte[] getSetGpsSpeed(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        int v= (int) objects[0];
//        LG.i("设置----GPS值为："+v+"--"+(byte)( v& 0xFF));
        return new byte[]{
                (byte)( v& 0xFF)
        };
    }


    public static byte[] getSetBrightness(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        HudBrightnessMoldType brightnessMoldType= (HudBrightnessMoldType) objects[0];

        if(brightnessMoldType==HudBrightnessMoldType.AUTO){
            return new byte[]{
                    brightnessMoldType.getType()
            };
        }else if(brightnessMoldType==HudBrightnessMoldType.HAND){
            int v= (int) objects[1];

            return new byte[]{
                    brightnessMoldType.getType(),
                    (byte)( v& 0xFF),
                    (byte)( v& 0xFF)
            };
        }
        return null;
    }

    public static byte[] getSetSound(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        int v= (int) objects[0];

        return new byte[]{
                (byte)( v& 0xFF)
        };
    }

    public static byte[] getRewriteSN(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        String sn= (String) objects[0];
        byte[] snBytes=sn.getBytes(StandardCharsets.UTF_8);
        return snBytes;
    }

    public static byte[] getReadySendImage(Object... objects) {
        if (null == objects || objects.length < 5) {
            return null;
        }
        int w= (int) objects[0];
        byte[] wBs=BleByteUtil.intToByteArray32only2(w);
        int h= (int) objects[1];
        byte[] hBs=BleByteUtil.intToByteArray32only2(h);
        int size= (int) objects[2];
        byte[] sizeBs= BleByteUtil.intToByteArray32(size);
        HudSendImageType sendImageType= (HudSendImageType) objects[3];
        int imageShowType= (int) objects[4];
        byte[]bytes=new byte[10];
        bytes[0]=wBs[0];
        bytes[1]=wBs[1];
        bytes[2]=hBs[0];
        bytes[3]=hBs[1];
        bytes[4]=sizeBs[0];
        bytes[5]=sizeBs[1];
        bytes[6]=sizeBs[2];
        bytes[7]=sizeBs[3];
        bytes[8]=sendImageType.getType();
        bytes[9]=(byte)(imageShowType & 0xFF);
        return bytes;
    }

    public static byte[] getShowImage(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        HudImageShowType imageShowType = (HudImageShowType) objects[0];
        return new byte[]{
               imageShowType.getType()
        };
    }

    public static byte[] getYellowStatu(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        HudYellowStatuBjType statuType = (HudYellowStatuBjType) objects[0];
        return new byte[]{
                statuType.getType()
        };
    }





    public static byte[] getFactroySet(){
        return new byte[]{
               (byte) 0XFF
        };
    }

    public static byte[] getStr(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        String str= (String) objects[0];
        byte[] strBytes=str.getBytes(StandardCharsets.UTF_8);
        byte[] bytes=new byte[strBytes.length+1];
        bytes[0]=(byte)(strBytes.length & 0xFF);
        for(int i=0;i<strBytes.length;i++){
            bytes[i+1]=strBytes[i];
        }
        return bytes;
    }

    public static byte[] getStatu(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        HudStatuType statuType = (HudStatuType) objects[0];
        return new byte[]{
                statuType.getType()
        };
    }


    public static byte[] getUI(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        HudUiType uiType = (HudUiType) objects[0];
        return new byte[]{
                uiType.getType()
        };
    }

    public static byte[] getTurnBjStyle(Object... objects) {
        if (null == objects || objects.length < 1) {
            return null;
        }
        HudTurnBjType styleType = (HudTurnBjType) objects[0];
        return new byte[]{
                styleType.getType()
        };
    }


}
