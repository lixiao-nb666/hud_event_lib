package com.nrmyw.hud_data_event_lib.manager.yellowstatu;

import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.manager.notifiction.HudNotifictionManager;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.yellow_statu.HudYellowStatuBjType;

public class HudYellowStatuManager {
    private static HudYellowStatuManager hudYellowStatuManager;
    private HudYellowStatuManager(){

    }

    public static HudYellowStatuManager getInstance(){
        if(null==hudYellowStatuManager){
            synchronized (HudNotifictionManager.class){
                if(null==hudYellowStatuManager){
                    hudYellowStatuManager=new HudYellowStatuManager();
                }
            }
        }
        return hudYellowStatuManager;
    }

    private boolean onlyShowOne;
    public void reShow(boolean onlyShowOne){
            this.onlyShowOne=onlyShowOne;
            if(null==bjType1){
                bjType1=HudYellowStatuBjType.HIDE;
            }
            if(null==bjType2){
                bjType2=HudYellowStatuBjType.HIDE;
            }

            if(bjType1==HudYellowStatuBjType.HIDE&&bjType2==HudYellowStatuBjType.HIDE){
                return;
            }
            if(onlyShowOne){
                if(bjType1!=HudYellowStatuBjType.HIDE){
                    HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, HudYellowStatuBjType.HIDE, bjType1);
                }else {
                    HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, bjType1, bjType2);
                }

            }else {
                HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, bjType1, bjType2);
            }

    }

    private HudYellowStatuBjType bjType1,bjType2;

    public void showYellowStatu(HudYellowStatuBjType hudYellowStatuBjType) {
        if(null==hudYellowStatuBjType){
            hudYellowStatuBjType=HudYellowStatuBjType.HIDE;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, hudYellowStatuBjType);
        bjType1=hudYellowStatuBjType;
        bjType2=HudYellowStatuBjType.HIDE;
    }


    public void showYellowStatu(HudYellowStatuBjType hudYellowStatuBjType1, HudYellowStatuBjType hudYellowStatuBjType2) {
        if(null==hudYellowStatuBjType1){
            hudYellowStatuBjType1=HudYellowStatuBjType.HIDE;
        }
        if(null==hudYellowStatuBjType2){
            hudYellowStatuBjType2=HudYellowStatuBjType.HIDE;
        }
        bjType1=hudYellowStatuBjType1;
        bjType2=hudYellowStatuBjType2;
        if(onlyShowOne){
            if(bjType1!=HudYellowStatuBjType.HIDE){
                HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, HudYellowStatuBjType.HIDE, bjType1);
            }else {
                HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, bjType1, bjType2);
            }

        }else {
            HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, hudYellowStatuBjType1, hudYellowStatuBjType2);

        }

    }
}
