package me.kimxu.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import me.kimxu.service.PlayService;
import me.kimxu.utils.Actions;


/**
 * 耳机拔出/蓝牙断开接收器
 * Created by kimxu on 2016/1/23.
 */
public class NoisyAudioStreamReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, PlayService.class);
        serviceIntent.setAction(Actions.ACTION_MEDIA_PLAY_PAUSE);
        context.startService(serviceIntent);
    }
}
