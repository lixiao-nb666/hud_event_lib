package com.nrmyw.hud_data_event_lib.manager.turn;

import android.util.Log;

import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.manager.image.HudSendImageManager;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.image.HudImageType;
import com.nrmyw.hud_data_lib.type.turn.HudTurnType;

public class HudSendTurnTypeManager {

    private static HudSendTurnTypeManager hudSendTurnTypeManager;

    private HudSendTurnTypeManager(){

    }


    public static HudSendTurnTypeManager getInstance(){
        if(null==hudSendTurnTypeManager){
            synchronized (HudSendTurnTypeManager.class){
                if(null==hudSendTurnTypeManager){
                    hudSendTurnTypeManager=new HudSendTurnTypeManager();
                }
            }
        }
        return hudSendTurnTypeManager;
    }



    boolean imageIsshow;
    public void setImageIsShow(){
        HudImageType hudImageType= HudSendImageManager.getInstance().getHudImageType();
        Log.i("shifouzoulexinde","shifouzoulexinde0:"+imageIsshow);
        if(null==hudImageType){
            return;
        }
        if(hudImageType== HudImageType.IMAGE){
            imageIsshow=true;
        }
        Log.i("shifouzoulexinde","shifouzoulexinde1:"+imageIsshow);
    }

    public void setImageIsHide(){
        Log.i("shifouzoulexinde","shifouzoulexinde2:"+imageIsshow);
        imageIsshow=false;
    }

    public void setTureType(HudTurnType type1, int distance1){
        Log.i("shifouzoulexinde","shifouzoulexinde3:"+imageIsshow);
        if(imageIsshow){
            HudSendManager.getInstance().sendCmd(HudCmdType.NEW_TURN_TYPE,type1,distance1);
        }else {
            HudSendManager.getInstance().sendCmd(HudCmdType.TURN_TYPE,type1,distance1);
        }
    }

    public void setTureType(HudTurnType type1, int distance1,HudTurnType type2, int distance2){
        Log.i("shifouzoulexinde","shifouzoulexinde4:"+imageIsshow);
        if(imageIsshow){
            HudSendManager.getInstance().sendCmd(HudCmdType.NEW_TURN_TYPE,type1,distance1,type2,distance2);
        }else {
            HudSendManager.getInstance().sendCmd(HudCmdType.TURN_TYPE,type1,distance1,type2,distance2);
        }
    }
}
