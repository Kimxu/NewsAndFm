package me.kimxu.frag;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import java.io.File;

import kimxu.adapter.AssemblyAdapter;
import kimxu.utils.Ts;
import me.kimxu.KBaseApplication;
import me.kimxu.KBaseFragment;
import me.kimxu.R;
import me.kimxu.adapter.factory.LocalMusicFactory;
import me.kimxu.adapter.factory.OnMoreClickListener;
import me.kimxu.delegate.LocalMusicDelegate;
import me.kimxu.enums.MusicTypeEnum;
import me.kimxu.model.Music;
import me.kimxu.service.PlayService;

/**
 * Fm电台
 */
public class LocalMusicFragment extends KBaseFragment<LocalMusicDelegate> implements OnMoreClickListener, AdapterView.OnItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private AssemblyAdapter mAdapter;
    private LocalMusicFactory localMusicFactory;

    public static LocalMusicFragment newInstance(String param1, String param2) {
        LocalMusicFragment fragment = new LocalMusicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindEvenListener() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mAdapter = new AssemblyAdapter(PlayService.getMusicList());
        localMusicFactory = new LocalMusicFactory(this);
        mAdapter.addItemFactory(localMusicFactory);

        if (getPlayService().getPlayingMusic() != null && getPlayService().getPlayingMusic().getType() == MusicTypeEnum.LOCAL) {
            viewDelegate.lvLocalMusic.setSelection(getPlayService().getPlayingPosition());
        }
        viewDelegate.lvLocalMusic.setOnItemClickListener(this);
        updateView();
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        getActivity().registerReceiver(mDownloadReceiver, filter);

    }
    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mDownloadReceiver);
        super.onDestroy();
    }

    private void updateView() {
        if (PlayService.getMusicList().isEmpty()) {
            viewDelegate.tvEmpty.setVisibility(View.VISIBLE);
        } else {
            viewDelegate.tvEmpty.setVisibility(View.GONE);
        }
        //localMusicFactory.updatePlayingPosition();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected Class<LocalMusicDelegate> getDelegateClass() {
        return LocalMusicDelegate.class;
    }


    @Override
    public void onMoreClick(int position) {
        final Music music = PlayService.getMusicList().get(position);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(music.getTitle());
        int itemsId = position == getPlayService().getPlayingPosition() ? R.array.local_music_dialog_no_delete : R.array.local_music_dialog;
        dialog.setItems(itemsId, (dialog1, which) -> {
            switch (which) {
                case 0:// 分享
                    shareMusic(music);
                    break;
                case 1:// 设为铃声
                    setRingtone(music);
                    break;
                case 2:// 删除
                    deleteMusic(music);
                    break;
            }
        });
        dialog.show();
    }

    /**
     * 分享音乐
     */
    private void shareMusic(Music music) {
        File file = new File(music.getUri());
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("audio/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

    /**
     * 设置铃声
     */
    private void setRingtone(Music music) {
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(music.getUri());
        // 查询音乐文件在媒体库是否存在
        Cursor cursor = getActivity().getContentResolver().query(uri, null,
                MediaStore.MediaColumns.DATA + "=?", new String[]{music.getUri()}, null);
        if (cursor == null) {
            return;
        }
        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            String _id = cursor.getString(0);
            ContentValues values = new ContentValues();
            values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
            values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
            values.put(MediaStore.Audio.Media.IS_ALARM, false);
            values.put(MediaStore.Audio.Media.IS_MUSIC, false);

            getActivity().getContentResolver().update(uri, values, MediaStore.MediaColumns.DATA + "=?", new String[]{music.getUri()});
            Uri newUri = ContentUris.withAppendedId(uri, Long.valueOf(_id));
            RingtoneManager.setActualDefaultRingtoneUri(getActivity(), RingtoneManager.TYPE_RINGTONE, newUri);
            Ts.showToast(R.string.setting_ringtone_success);
        }
        cursor.close();
    }

    /**
     * 删除音乐
     */
    private void deleteMusic(final Music music) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        String title = music.getTitle();
        String msg = getString(R.string.delete_music, title);
        dialog.setMessage(msg);
        dialog.setPositiveButton(R.string.delete, (dialog1, which) -> {
            PlayService.getMusicList().remove(music);
            File file = new File(music.getUri());
            if (file.delete()) {
                getPlayService().updatePlayingPosition();
                updateView();
                // 刷新媒体库
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + music.getUri()));
                getActivity().sendBroadcast(intent);
            }
        });
        dialog.setNegativeButton(R.string.cancel, null);
        dialog.show();
    }

    private BroadcastReceiver mDownloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            String title = KBaseApplication.getInstance().getDownloadList().get(id);
            if (TextUtils.isEmpty(title)) {
                return;
            }
            // 由于系统扫描音乐是异步执行，因此延迟刷新音乐列表
            mHandler.postDelayed(() -> {
                if (!isAdded()) {
                    return;
                }
                getPlayService().updateMusicList();
                updateView();
            }, 500);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getPlayService().play(position);
    }

    public void onItemPlay() {
        updateView();
        if (getPlayService().getPlayingMusic().getType() == MusicTypeEnum.LOCAL) {
            viewDelegate.lvLocalMusic.smoothScrollToPosition(getPlayService().getPlayingPosition());
        }
    }

}
