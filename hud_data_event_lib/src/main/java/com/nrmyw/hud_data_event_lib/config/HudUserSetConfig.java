package com.nrmyw.hud_data_event_lib.config;

public class HudUserSetConfig {
    private static HudUserSetConfig hudUserSetConfig;
    private HudUserSetConfig(){

    }

    public static HudUserSetConfig getInstance(){
        if(null==hudUserSetConfig){
            synchronized (HudUserSetConfig.class){
                if(null==hudUserSetConfig){
                    hudUserSetConfig=new HudUserSetConfig();
                }
            }
        }
        return hudUserSetConfig;
    }



}
