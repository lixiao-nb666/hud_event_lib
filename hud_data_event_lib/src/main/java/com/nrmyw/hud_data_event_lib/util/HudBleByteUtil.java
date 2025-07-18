package com.nrmyw.hud_data_event_lib.util;


import com.nrmyw.hud_data_lib.config.HudConfig;

public class HudBleByteUtil {





    private static int defMixLength=5;
    public static byte[] useTitleAndBodyGetAllCmdBytes(byte title,byte[] body){
        int needLength=defMixLength;
        if(null!=body&&body.length>0){
            needLength+=body.length;
        }
        byte[] result = new byte[needLength];
        result[0]= HudConfig.cmdt1;
        result[1]= HudConfig.cmdt2;
        result[2]= (byte) ((needLength >> 8) & 0xFF);
        result[3]= (byte) (needLength & 0xFF);
        result[4]=title;
        if(needLength>defMixLength){
            for(int i=0;i<body.length;i++){
                result[i+5] = body[i];
            }
        }
        if(defMixLength>=7){
            result[needLength-2]=HudConfig.end1;
            result[needLength-1]=HudConfig.end2;
        }
        return result;
    }








}
