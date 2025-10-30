package com.nrmyw.hud_data_event.activity;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.newbee.ble_lib.NewBeeBleManager;
import com.nrmyw.ble_event_lib.type.BleSendBitmapQualityType;
import com.nrmyw.hud_data_event.R;
import com.nrmyw.hud_data_event_lib.HudEventManager;


public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button sendTimeBT,sendSpeedBT;
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(null==v){
                return;
            }
            switch (v.getId()){
                case R.id.bt_send_time:
//                    HudEvent.getInstance().sendTime();
                    break;
                case R.id.bt_send_speed:
//                    HudEvent.getInstance().sendNowSpeed(165);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        sendTimeBT=findViewById(R.id.bt_send_time);
        sendSpeedBT=findViewById(R.id.bt_send_speed);
        sendTimeBT.setOnClickListener(onClickListener);
        sendSpeedBT.setOnClickListener(onClickListener);
        Log.i("kankanshibushikong","kankanshibushikong"+(null==HudEventManager.getInstance().getHudEvent()));
        NewBeeBleManager.getInstance().nowGetAllPermissions();
    }


}
