package com.nrmyw.hud_data_event_lib.manager;

import android.graphics.Bitmap;
import android.util.ArrayMap;

import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.image.HudImageShowType;
import com.nrmyw.hud_data_lib.type.image.HudImageType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HudImageManeger {

    private static HudImageManeger hudImageManeger;
    private Map<HudImageType, Bitmap> bitmapMap;
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
    public void setSendImageStatu(boolean nowSendImageIng){
        this.nowSendImageIng=nowSendImageIng;
        if(!this.nowSendImageIng){
            lastSendOverTime=System.currentTimeMillis();
        }
    }



    public void send(HudImageType hudImageType,Bitmap bitmap){
        if(null==hudImageType||null==bitmap||bitmap.isRecycled()){
            return;
        }
        long nowTime=System.currentTimeMillis();
        if(nowSendImageIng&&nowTime-lastSendOverTime>3000){
            nowSendImageIng=false;
        }
        if(!nowSendImageIng&&checkNowCanSend()){
            HudSendManager.getInstance().sendBitmap(bitmap,hudImageType.getType());
        }else {
            addToMap(hudImageType,bitmap);
        }


    }

    private void addToMap(HudImageType hudImageType,Bitmap bitmap){
        if(null==bitmapMap){
            bitmapMap=new HashMap<>();
        }
        bitmapMap.put(hudImageType,bitmap);
    }

    public void checkToSend(){
        if(null==bitmapMap||bitmapMap.isEmpty()||nowSendImageIng||!checkNowCanSend()){
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
                bitmapMap.remove(hudImageType);
                return;
            }
        }

    }

    private boolean checkNowCanSend(){
        long nowTime=System.currentTimeMillis();
        if(nowTime-lastSendOverTime>500){
            return true;
        }else {
            return false;
        }
    }



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
