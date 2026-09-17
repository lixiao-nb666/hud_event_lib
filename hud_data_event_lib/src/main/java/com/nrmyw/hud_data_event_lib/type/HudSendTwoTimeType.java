package com.nrmyw.hud_data_event_lib.type;

public enum HudSendTwoTimeType {
    ICON_FLICKER,
    HIDE_WP,

    FRIST_SHOW_EXIT_MSG,
    HIDE_EXIT_MSG,
    IntervalSpeed,
    ;

    public int getCmdIndex(){
        return ordinal()+100;
    }

}
