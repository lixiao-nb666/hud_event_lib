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
            if(null==wpBjType1){
                wpBjType1=HudYellowStatuBjType.HIDE;
            }
            if(null==wpBjType2){
                wpBjType2=HudYellowStatuBjType.HIDE;
            }

            if(wpBjType1==HudYellowStatuBjType.HIDE&&wpBjType2==HudYellowStatuBjType.HIDE){
                return;
            }
            if(onlyShowOne){
                if(wpBjType1!=HudYellowStatuBjType.HIDE){
                    HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU,  wpBjType1, wpBjType1);
                }else if(wpBjType2!=HudYellowStatuBjType.HIDE){
                    HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, wpBjType2, wpBjType2);
                }
            }else {
                HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, wpBjType1, wpBjType2);
            }

    }

    private HudYellowStatuBjType wpBjType1,wpBjType2;

    public void showTitleYellowStatu(HudYellowStatuBjType hudYellowStatuBjType) {
        if(null==hudYellowStatuBjType){
            hudYellowStatuBjType=HudYellowStatuBjType.HIDE;
        }
        HudSendManager.getInstance().sendCmd(HudCmdType.TITLE_YELLOW_STATU, hudYellowStatuBjType);

    }


    public void showWarningPointYellowStatu(HudYellowStatuBjType hudYellowStatuBjType1, HudYellowStatuBjType hudYellowStatuBjType2) {
        if(null==hudYellowStatuBjType1){
            hudYellowStatuBjType1=HudYellowStatuBjType.HIDE;
        }
        if(null==hudYellowStatuBjType2){
            hudYellowStatuBjType2=HudYellowStatuBjType.HIDE;
        }
        wpBjType1=hudYellowStatuBjType1;
        wpBjType2=hudYellowStatuBjType2;
        if(onlyShowOne){
            if(wpBjType1!=HudYellowStatuBjType.HIDE){
                HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU,  wpBjType1, wpBjType1);
            }else if(wpBjType2!=HudYellowStatuBjType.HIDE){
                HudSendManager.getInstance().sendCmd(HudCmdType.YELLOW_STATU, wpBjType2, wpBjType2);
            }

        }else {
            HudSendManager.getInstance().sendCmd(HudCmdType.WARNING_POINT_YELLOW_STATU, hudYellowStatuBjType1, hudYellowStatuBjType2);

        }

    }
}
