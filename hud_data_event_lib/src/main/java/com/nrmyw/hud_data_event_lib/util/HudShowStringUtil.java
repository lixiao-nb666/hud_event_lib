package com.nrmyw.hud_data_event_lib.util;

import android.text.TextUtils;

import com.nrmyw.hud_data_event_lib.config.HudSetConfig;

public class HudShowStringUtil {

    public static String getNeedLString(String checkStr,int minL,int maxL){
        if(TextUtils.isEmpty(checkStr)){
            return "";
        }
        if(checkStr.length()>minL&&checkStr.length()<maxL){

//            int  needAddNumb=HudSetConfig.getInstance().getHudSetBean().getShowMaxNumb()-checkStr.length();
//            return addEmp(checkStr,needAddNumb);
            return checkStr+"         ";
        }
        return checkStr;
    }

    private static String addEmp(String ss,int needNumb){
        if(TextUtils.isEmpty(ss)){
            return "";
        }
        try {
            if(needNumb<3){
                return ss+"   ";
            }
            StringBuilder sb=new StringBuilder();
            sb.append(ss);
            for(int i=0;i<needNumb;i++){
                sb.append(" ");
            }
            return sb.toString();
        }catch (Exception e){
            return ss+"   ";
        }

    }

}
