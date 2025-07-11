package com.nrmyw.hud_data_event_lib.service;

import android.content.Context;

import com.nrmyw.hud_data_event_lib.base.BaseServiceDao;


public class HudEventServiceDao extends BaseServiceDao {
    public HudEventServiceDao(Context context) {
        super(context);
    }

    @Override
    protected Class getSsCls() {
        return HudEventService.class;
    }
}
