package com.nrmyw.hud_data_event_lib.manager;

import android.graphics.Bitmap;
import android.util.ArrayMap;
import android.util.Log;

import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.image.HudImageShowType;
import com.nrmyw.hud_data_lib.type.image.HudImageType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HudImageManeger {

    private static HudImageManeger hudImageManeger;
    private Map<HudImageType,Bitmap>bitmapMap=new ArrayMap<>();
    private HudImageManeger(){

    }

    public static HudImageManeger getInstance(){
        if(null==hudImageManeger){
            synchronized (HudImageManeger.class){
                if(null==hudImageManeger){
                    hudImageManeger=new HudImageManeger();
                }
            }
        }
        return hudImageManeger;
    }

    private boolean nowSendImageIng;
    private  long lastSendOverTime;
    public void setSendImageIsStart(boolean nowSendImageIng){
        this.nowSendImageIng=nowSendImageIng;
        if(!this.nowSendImageIng){
            lastSendOverTime=System.currentTimeMillis();
        }
    }



    public void send(HudImageType hudImageType,Bitmap bitmap){
        Log.i("kankanshibushijinlaile","kankanshibushijinlaile:11");
        if(null==hudImageType||null==bitmap||bitmap.isRecycled()){
            return;
        }
        Log.i("kankanshibushijinlaile","kankanshibushijinlaile:22");
        long nowTime=System.currentTimeMillis();
        if(nowSendImageIng&&nowTime-lastSendOverTime>3000){
            nowSendImageIng=false;
        }
        if(nowSendImageIng){
            Log.i("kankanshibushijinlaile","kankanshibushijinlaile:33");
            if(hudImageType==HudImageType.IMAGE){
                addToMap(hudImageType,bitmap);
            }
        }else {
            Log.i("kankanshibushijinlaile","kankanshibushijinlaile:33");
            HudSendManager.getInstance().sendBitmap(bitmap,hudImageType.getType());
        }

//        if(!nowSendImageIng&&checkNowCanSend()){
//            HudSendManager.getInstance().sendBitmap(bitmap,hudImageType.getType());
//        }else {
//
//        }


    }

    private void addToMap(HudImageType hudImageType,Bitmap bitmap){
        if(null==bitmapMap){
            bitmapMap=new HashMap<>();
        }
        bitmapMap.put(hudImageType,bitmap);
    }

    public void checkToSend(){
        if(null==bitmapMap||bitmapMap.isEmpty()){
            return;
        }
        for(HudImageType hudImageType:bitmapMap.keySet()){
            if(null==hudImageType){
                bitmapMap.clear();
                return;
            }else {
                Bitmap bitmap=bitmapMap.get(hudImageType);
                if(null!=bitmap&&!bitmap.isRecycled()){
                    HudSendManager.getInstance().sendBitmap(bitmap,hudImageType.getType());
                }
                bitmapMap.clear();
                return;
            }
        }
    }
//    private boolean checkNowCanSend(){
//        long nowTime=System.currentTimeMillis();
//        if(nowTime-lastSendOverTime>500){
//            return true;
//        }else {
//            return false;
//        }
//    }

    public void cancelImage(HudImageType hudImageType){
        if(null==hudImageType){
            hudImageType=HudImageType.IMAGE;
        }
        switch (hudImageType){
            case IMAGE:
                HudSendManager.getInstance().sendCmd(HudCmdType.SHOW_IMAGE, HudImageShowType.HIDE);
                break;
            case PROGRESS_BAR:
                HudSendManager.getInstance().sendCmd(HudCmdType.CLEAR_PROGRESS_BAR );
                break;
        }
        if(null!=bitmapMap&&!bitmapMap.isEmpty()){
            bitmapMap.remove(hudImageType);
        }

    }
}
