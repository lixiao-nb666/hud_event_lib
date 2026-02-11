package com.nrmyw.hud_data_event_lib.manager;

import android.media.Image;
import android.util.Log;

import com.nrmyw.ble_event_lib.bean.BleSendImageInfoBean;
import com.nrmyw.ble_event_lib.send.BleEventSubscriptionSubject;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.image.HudImageShowType;
import com.nrmyw.hud_data_lib.type.image.HudImageType;

public class HudSendImageManager {
    private static HudSendImageManager sendImageManager;
    private HudImageType hudImageType;
    private HudSendImageManager(){}

    public static HudSendImageManager getInstance(){
        if(null==sendImageManager){
            synchronized (HudSendImageManager.class){
                if(null==sendImageManager){
                    sendImageManager=new HudSendImageManager();
                }
            }
        }
        return sendImageManager;
    }

    private BleSendImageInfoBean reSendImageInfoBean;
    public void setReSendImage(BleSendImageInfoBean bleSendImageInfoBean){
        this.reSendImageInfoBean=bleSendImageInfoBean;
        Log.i("chongxinfasongle","chongxinfasong:1");
    }


    public void nowReSend(){
        Log.i("chongxinfasongle","chongxinfasong:2");
        if(null==reSendImageInfoBean){
            return;
        }
        Log.i("chongxinfasongle","chongxinfasong:3");
        if(null==reSendImageInfoBean.getBitmap()||reSendImageInfoBean.getBitmap().isRecycled()){
            return;
        }
        Log.i("chongxinfasongle","chongxinfasong:4");
        if(null==hudImageType){
            reSendImageInfoBean=null;
            return;
        }
        Log.i("chongxinfasongle","chongxinfasong:5");
        if(hudImageType.ordinal()==reSendImageInfoBean.getType()){
            reSendImageInfoBean=null;
            return;
        }
        Log.i("chongxinfasongle","chongxinfasong:6");
        BleEventSubscriptionSubject.getInstance().sendImage(reSendImageInfoBean);
    }


    public void setImageType(int imageType){
        try {
            this.hudImageType=HudImageType.values()[imageType];
        }catch (Exception e){
            this.hudImageType=null;
        }
    }


    public void hideImage(){
       checkImageTypeIsSameAndDoClear(HudImageType.IMAGE);
        HudImageManeger.getInstance().setImageCanShow(false);
        HudSendManager.getInstance().sendCmd(HudCmdType. SHOW_IMAGE, HudImageShowType.HIDE);
    }


    public void hideProgressBar(){
        checkImageTypeIsSameAndDoClear(HudImageType.PROGRESS_BAR);
        HudSendManager.getInstance().sendCmd(HudCmdType.CLEAR_PROGRESS_BAR );
    }

    private void  checkImageTypeIsSameAndDoClear(HudImageType nowImageType){

        if(null!=hudImageType&&hudImageType==nowImageType){
            BleEventSubscriptionSubject.getInstance().clearIndexMsg();
        }
    }


}
