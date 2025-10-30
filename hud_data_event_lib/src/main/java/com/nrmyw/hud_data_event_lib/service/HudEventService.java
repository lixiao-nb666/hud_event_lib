package com.nrmyw.hud_data_event_lib.service;


import android.content.Intent;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.nrmyw.ble_event_lib.bean.BleSendImageEndInfoBean;
import com.nrmyw.ble_event_lib.bean.BleSendImageStartInfoBean;
import com.nrmyw.ble_event_lib.send.BleEventSubscriptionSubject;
import com.nrmyw.ble_event_lib.statu.BleStatu;
import com.nrmyw.ble_event_lib.statu.BleStatuEventObserver;
import com.nrmyw.ble_event_lib.statu.BleStatuEventSubscriptionSubject;

import com.nrmyw.hud_data_event_lib.base.BaseService;
import com.nrmyw.hud_data_event_lib.manager.HudImageManeger;
import com.nrmyw.hud_data_event_lib.manager.HudSendManager;
import com.nrmyw.hud_data_event_lib.util.HudCmdRetrunDataUtil;
import com.nrmyw.hud_data_event_lib.HudEvent;
import com.nrmyw.hud_data_lib.type.HudCmdType;
import com.nrmyw.hud_data_lib.type.image.HudSendImageType;
import java.util.Date;

public class HudEventService extends BaseService {
//    private BluetoothGattServiceDao bluetoothGattServiceDao;
    private boolean canSendTime;
    private BleStatuEventObserver bleStatuEventObserver=new BleStatuEventObserver() {
        @Override
        public void sendBleStatu(BleStatu bleStatu, Object... objects) {
            Log.i("lookStatu","kankanshibushikong:"+bleStatu);
            switch (bleStatu){
                case CONNECTING:

                    break;
                case CONNECTED:
                    canSendTime=true;
                    initTime();
                    break;
                case DISCONNECTED:
                    canSendTime=false;
                    break;
                case SEND_IMAGE_START:
                    HudImageManeger.getInstance().setSendImageIsStart(true);
                    doSendImageStartThing((BleSendImageStartInfoBean) objects[0]);
                    break;
                case SEND_IMAGE_END:
                    doSendImageEndThing((BleSendImageEndInfoBean) objects[0]);
                    HudImageManeger.getInstance().setSendImageIsStart(false);
                    handler.removeMessages(HudEventServiceMsgType.CHECK_IMAGE_SEND.ordinal());
                    handler.sendEmptyMessageDelayed(HudEventServiceMsgType.CHECK_IMAGE_SEND.ordinal(),168);
                    break;
                case RUN_ERR:
                    break;
                case SENDING_DATA:

                    break;
                case RETRUN_BYTES:
                    byte[] retrunBytes= (byte[]) objects[0];
                    HudCmdRetrunDataUtil.doVue(retrunBytes);
                    break;
            }
        }

        private void doSendImageStartThing(BleSendImageStartInfoBean startInfoBean){
            byte[] startBytes= HudSendManager.getInstance().getAllByte(HudCmdType.READY_SEND_IMAGE,startInfoBean.getW(),startInfoBean.getH(),startInfoBean.getSize(), HudSendImageType.START,startInfoBean.getType());
            BleEventSubscriptionSubject.getInstance().sendImageIndexCmd(0,startBytes);
        }

        private void doSendImageEndThing(BleSendImageEndInfoBean endInfoBean){
            byte[] endBytes=HudSendManager.getInstance().getAllByte(HudCmdType.READY_SEND_IMAGE,endInfoBean.getW(),endInfoBean.getH(),endInfoBean.getSize(), HudSendImageType.END,endInfoBean.getType());
            BleEventSubscriptionSubject.getInstance().sendImageIndexCmd(endInfoBean.getIndex(),endBytes);
            if(endInfoBean.getType()==0&&HudImageManeger.getInstance().checkImageCanShow()){
                getHudEvent().showImage();
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
                        if(!canSendTime){
                            return;
                        }
                        getHudEvent().sendTime();
                        long nowTime=System.currentTimeMillis();
                        Date date = new Date(nowTime);
                        int needS=60-date.getSeconds();
                        handler.sendEmptyMessageDelayed(HudEventServiceMsgType.SEND_TIME.ordinal(),needS*1000);
                        break;
                    case CHECK_IMAGE_SEND:
                        HudImageManeger.getInstance().checkToSend();
                        break;
                }
            }catch (Exception e){}
        }
    };


    private HudEvent getHudEvent(){
        return HudEvent.getInstance();
    }

    private void initTime(){
        handler.removeMessages(HudEventServiceMsgType.SEND_TIME.ordinal());
        handler.sendEmptyMessageDelayed(HudEventServiceMsgType.SEND_TIME.ordinal(),1000);
    }



    @Override
    public void init() {
        BleStatuEventSubscriptionSubject.getInstance().attach(bleStatuEventObserver);
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
