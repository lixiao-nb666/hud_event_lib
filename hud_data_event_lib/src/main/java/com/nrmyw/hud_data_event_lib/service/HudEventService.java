package com.nrmyw.hud_data_event_lib.service;


import android.content.Intent;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.nrmyw.ble_event_lib.bean.BleSendImageEndInfoBean;
import com.nrmyw.ble_event_lib.bean.BleSendImageInfoBean;
import com.nrmyw.ble_event_lib.bean.BleSendImageStartInfoBean;
import com.nrmyw.ble_event_lib.send.BleEventSubscriptionSubject;
import com.nrmyw.ble_event_lib.statu.BleStatu;
import com.nrmyw.ble_event_lib.statu.BleStatuEventObserver;
import com.nrmyw.ble_event_lib.statu.BleStatuEventSubscriptionSubject;

import com.nrmyw.hud_data_event_lib.base.BaseService;

import com.nrmyw.hud_data_event_lib.manager.HudImageManeger;
import com.nrmyw.hud_data_event_lib.manager.HudSendImageManager;
import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.manager.HudTimeManager;
import com.nrmyw.hud_data_event_lib.util.HudCmdRetrunDataUtil;
import com.nrmyw.hud_data_event_lib.HudEvent;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.image.HudImageShowType;
import com.nrmyw.hud_data_lib.type.image.HudSendImageType;
import java.util.Date;

public class HudEventService extends BaseService {

    private HudImageManeger.Listen imageListen=new HudImageManeger.Listen() {
        @Override
        public void nowCanShowImage() {
            handler.removeMessages(HudEventServiceMsgType.HIDE_IMAGE.ordinal());
        }

        @Override
        public void nowMustSetImageHide() {
            handler.sendEmptyMessageDelayed(HudEventServiceMsgType.HIDE_IMAGE.ordinal(),1000);
        }
    };

    private HudTimeManager.Listen timeListen=new HudTimeManager.Listen() {
        @Override
        public void initTime() {
            handler.removeMessages(HudEventServiceMsgType.SEND_TIME.ordinal());
            handler.sendEmptyMessageDelayed(HudEventServiceMsgType.SEND_TIME.ordinal(),1000);
        }

        @Override
        public void stopTime() {
            handler.removeMessages(HudEventServiceMsgType.SEND_TIME.ordinal());
        }
    };



    private BleStatuEventObserver bleStatuEventObserver=new BleStatuEventObserver() {
        @Override
        public void sendBleStatu(BleStatu bleStatu, Object... objects) {
            try {
                switch (bleStatu){
                    case CONNECTING:

                        break;
                    case CONNECTED:
                        HudTimeManager.getInstance().setTimeStart(true);
                        break;
                    case DISCONNECTED:
                        HudTimeManager.getInstance().setTimeStart(false);
                        break;
                    case SEND_IMAGE_START:
//                    HudImageManeger.getInstance().setSendImageIsStart(true);
//                    doSendImageStartThing((BleSendImageStartInfoBean) objects[0]);
                        break;
                    case SEND_IMAGE_END:
                        doSendImageEndThing((BleSendImageEndInfoBean) objects[0]);
                        break;
                    case SEND_IMAGE_DATA_END:
                        if(!HudImageManeger.getInstance().isImageCanShow()){
                            HudSendManager.getInstance().sendCmd(HudCmdType. SHOW_IMAGE, HudImageShowType.HIDE);
                        }
                        HudSendImageManager.getInstance().nowReSend();
                        break;
                    case RUN_ERR:
                        break;
                    case SENDING_DATA:

                        break;
                    case RETRUN_BYTES:
                        byte[] retrunBytes= (byte[]) objects[0];
                        HudCmdRetrunDataUtil.doVue(retrunBytes);
                        break;
                    case SEND_IMAGE_ERR_ISRUN:
                        HudSendImageManager.getInstance().setReSendImage((BleSendImageInfoBean)objects[0]);
                        break;
                }
            }catch (Exception e){}

        }

        private void doSendImageStartThing(BleSendImageStartInfoBean startInfoBean){
            byte[] startBytes= HudSendManager.getInstance().getAllByte(HudCmdType.READY_SEND_IMAGE,startInfoBean.getW(),startInfoBean.getH(),startInfoBean.getSize(), HudSendImageType.START,startInfoBean.getType());
            BleEventSubscriptionSubject.getInstance().sendBytesIndexCmd(0,startBytes);
        }

        private void doSendImageEndThing(BleSendImageEndInfoBean endInfoBean){
//            byte[] endBytes=HudSendManager.getInstance().getAllByte(HudCmdType.READY_SEND_IMAGE,endInfoBean.getW(),endInfoBean.getH(),endInfoBean.getSize(), HudSendImageType.END,endInfoBean.getType());
//            BleEventSubscriptionSubject.getInstance().sendBytesIndexCmd(endInfoBean.getIndex(),endBytes);
            HudSendImageManager.getInstance().setImageType(endInfoBean.getType());
            if(endInfoBean.getType()==0&&endInfoBean.getIndex()>0) {
                if(HudImageManeger.getInstance().isImageCanShow()){
                    byte[] showBytes = HudSendManager.getInstance().getAllByte(HudCmdType.SHOW_IMAGE, HudImageShowType.SHOW);
                    BleEventSubscriptionSubject.getInstance().sendBytesIndexCmd(endInfoBean.getIndex(), showBytes);
                }
            }
        }


    };




    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                HudEventServiceMsgType msgType=HudEventServiceMsgType.values()[msg.what];
                switch (msgType){
                    case SEND_TIME:

                        handler.removeMessages(HudEventServiceMsgType.SEND_TIME.ordinal());
                        if(!HudTimeManager.getInstance().getTimeIsStart()){
                            return;
                        }
                        getHudEvent().sendTime();
                        long nowTime=System.currentTimeMillis();
                        Date date = new Date(nowTime);
                        int needS=60-date.getSeconds();
                        handler.sendEmptyMessageDelayed(HudEventServiceMsgType.SEND_TIME.ordinal(),needS*1000);
                        break;
                    case HIDE_IMAGE:
                        if(HudImageManeger.getInstance().isImageCanShow()){
                            return;
                        }
                        Log.i("kaishiyincangtupian","kaishiyincangtupian");
                        HudSendManager.getInstance().sendCmd(HudCmdType. SHOW_IMAGE, HudImageShowType.HIDE);
                        if(HudImageManeger.getInstance().getHideNumb()>0){
                            handler.sendEmptyMessageDelayed(HudEventServiceMsgType.HIDE_IMAGE.ordinal(),1000);
                        }
                        break;
                }
            }catch (Exception e){}
        }
    };


    private HudEvent getHudEvent(){
        return HudEvent.getInstance();
    }




    @Override
    public void init() {
        BleStatuEventSubscriptionSubject.getInstance().attach(bleStatuEventObserver);
        HudTimeManager.getInstance().setListen(timeListen);
        HudImageManeger.getInstance().setListen(imageListen);
    }

    @Override
    public void start(Intent intent, int flags, int startId) {

    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            if(HudEventManager.getInstance().getNotificationId()==0||null==HudEventManager.getInstance().getNotification()){
//                return super.onStartCommand(intent, flags, startId);
//
//            }else {
//                startForeground(HudEventManager.getInstance().getNotificationId(), HudEventManager.getInstance().getNotification());//服务前台化只能使用startForeground()方法,不能使用 notificationManager.notify(1,notification); 这个只是启动通知使用的,使用这个方法你只需要等待几秒就会发现报错了
//                return START_STICKY;
//            }
//
//
//        }else {
//            return super.onStartCommand(intent, flags, startId);
//        }
////
//    }




    @Override
    public void close() {
        handler.removeCallbacksAndMessages(null);
        BleStatuEventSubscriptionSubject.getInstance().detach(bleStatuEventObserver);
    }


}
