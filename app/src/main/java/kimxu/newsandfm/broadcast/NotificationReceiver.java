package kimxu.newsandfm.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import kimxu.newsandfm.KBaseApplication;
import kimxu.newsandfm.NfContant;
import kimxu.newsandfm.R;
import kimxu.newsandfm.utils.GlobalUtils;

/**
 * 通知栏控制
 */
public class NotificationReceiver extends BroadcastReceiver {
    private KBaseApplication application;

    @Override
    public void onReceive(Context context, Intent intent) {
        application = (KBaseApplication) context.getApplicationContext();
        String ctrl_code = intent.getAction();//获取action标记，用户区分点击事件  
        if (application.audios != null) {
            if ("play".equals(ctrl_code)) {
                if (application.mPlayMusicService.getPlayer() == null)
                    playStart();
                else {
                    if (application.mPlayMusicService.getPlayer().isPlaying())
                        playPaused();
                    else
                        playStart();
                }
                System.out.println("play");
            } else if ("pause".equals(ctrl_code)) {
                application.mPlayMusicService.paused();
                System.out.println("pause");
            } else if ("next".equals(ctrl_code)) {
                application.mPlayMusicService.start(application.playNext());
                System.out.println("next");
            }
        }
    }

    private void playStart() {
        //application.mNotification.contentView.setImageViewBitmap(R.id.ib_notificationControl_playStart, GlobalUtils.drawable2Bitmap(application, R.drawable.nf_player_btn_play_normal));
        application.mNotification.contentView.setBitmap(R.id.ib_notificationControl_playStart,"setImageBitmap", GlobalUtils.drawable2Bitmap(application, R.drawable.nf_player_btn_play_normal));
        application.mNotificationManager.notify(NfContant.MUSIC_NOTIFICATION, application.mNotification);
        application.mPlayMusicService.start(application.play());

    }

    private void playPaused() {
        //application.mNotification.contentView.setImageViewBitmap(R.id.ib_notificationControl_playStart, GlobalUtils.drawable2Bitmap(application, R.drawable.nf_player_btn_pause_normal));
        application.mNotification.contentView.setBitmap(R.id.ib_notificationControl_playStart,"setImageBitmap", GlobalUtils.drawable2Bitmap(application, R.drawable.nf_player_btn_pause_normal));
        application.mNotificationManager.notify(NfContant.MUSIC_NOTIFICATION, application.mNotification);
        application.mPlayMusicService.paused();
    }


}  