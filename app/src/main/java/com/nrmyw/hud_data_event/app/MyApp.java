package com.nrmyw.hud_data_event.app;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import com.newbee.ble_lib.NewBeeBleManager;
import com.nrmyw.ble_event_lib.config.NewBeeBleConfig;
import com.nrmyw.hud_data_event.R;
import com.nrmyw.hud_data_event.bean.HudDevice;
import com.nrmyw.hud_data_event_lib.HudEventManager;
import com.nrmyw.hud_data_lib.config.HudConfig;


public class MyApp extends Application {
//    private HudCmdListen hudCmdListen=new HudCmdListen() {
//        @Override
//        public void getCmd(byte[] bytes) {
//            Log.i("kankanzhiling","kankanzhiling:"+ BleByteUtil.parseByte2HexStr(bytes));
//        }
//    };



    @Override
    public void onCreate() {
        super.onCreate();
        NewBeeBleConfig.getInstance().init(true, HudConfig.mtu,HudConfig.serviceID,HudConfig.writeID,HudConfig.noticeID, HudDevice.getBleDeviceTypeList());
        NewBeeBleManager.getInstance().init(this);
        HudEventManager.getInstance().init(this);
        startTo();
    }

    private void startTo() {
        String CHANNEL_ID="99";
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel Channel = null;
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Channel = new NotificationChannel(CHANNEL_ID, "Main Service", NotificationManager.IMPORTANCE_LOW);
            Channel.enableLights(true);
            Channel.setLightColor(Color.RED);
            Channel.setShowBadge(true);
            Channel.setDescription("PowerStateService");
            Channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC); //设置锁屏可见 VISIBILITY_PUBLIC=可见
            manager.createNotificationChannel(Channel);
            notification = new Notification.Builder(this)
                    .setChannelId(CHANNEL_ID)
                    .setContentTitle("Main Service")//标题
                    .setContentText("Running...")//内容
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)//小图标一定需要设置,否则会报错(如果不设置它启动服务前台化不会报错,但是你会发现这个通知不会启动),如果是普通通知,不设置必然报错
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .build();
//            HudEventManager.getInstance().setNotification(notification);
//            HudEventManager.getInstance().setNotificationId(11);
        }


    }
}
