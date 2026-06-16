package com.nrmyw.hud_data_event_lib.util;

import android.text.TextUtils;
import android.util.Log;

import com.nrmyw.hud_data_event_lib.config.HudSetConfig;

public class HudShowStringUtil {

    public static String getNeedLString(String checkStr,int minL,int maxL){
        if(TextUtils.isEmpty(checkStr)){
            return "";
        }
        Log.i("kankangaibianchangewei","kankangaibianchangewei0");
        if(checkStr.length()>minL&&checkStr.length()<maxL){
            if(checkStr.length()<12){
                Log.i("kankangaibianchangewei","kankangaibianchangewei1");
                return checkStr+"             ";
            }else {
                Log.i("kankangaibianchangewei","kankangaibianchangewei2");
                return checkStr+"         ";
            }
//            int  needAddNumb=HudSetConfig.getInstance().getHudSetBean().getShowMaxNumb()-checkStr.length();
//            return addEmp(checkStr,needAddNumb);

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
