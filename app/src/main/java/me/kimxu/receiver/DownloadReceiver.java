package me.kimxu.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

import kimxu.utils.Ts;
import me.kimxu.KBaseApplication;
import me.kimxu.R;


/**
 * 下载完成广播接收器
 * Created by hzwangchenyan on 2015/12/30.
 */
public class DownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        //TODO 下载这里没做
        //if (id == UpdateUtils.sDownloadId) {
        if (id == -1) {
            DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = dManager.getUriForDownloadedFile(id);
            File file = new File(uri.getPath());
            if (file.exists()) {
                Intent install = new Intent(Intent.ACTION_VIEW);
                install.setDataAndType(uri, "application/vnd.android.package-archive");
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(install);
            }
        } else {
            String title = KBaseApplication.getInstance().getDownloadList().get(id);
            if (!TextUtils.isEmpty(title)) {
                Ts.showToast(context.getString(R.string.download_success, title));
            }
        }
    }
}
