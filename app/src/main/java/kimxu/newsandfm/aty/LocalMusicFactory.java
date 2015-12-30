package kimxu.newsandfm.aty;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.newsandfm.R;
import kimxu.newsandfm.model.Audio;

/**
 * 本地音乐
 * Created by kimxu on 15/12/30.
 */

public class LocalMusicFactory extends AssemblyItemFactory<LocalMusicFactory.LocalMusicItem>{
    private OnClickListener mOnClickLisener;
    public LocalMusicFactory( OnClickListener onClickLisener) {
        this.mOnClickLisener=onClickLisener;
    }

    @Override
    public Class<Audio> getBeanClass() {
        return Audio.class;
    }

    @Override
    public LocalMusicItem createAssemblyItem(ViewGroup parent) {
        return new LocalMusicItem(parent,this);
    }
   public class LocalMusicItem extends AssemblyItem<Audio,LocalMusicFactory>{
       private TextView name;
       private TextView path;
       private View convertView;

       protected LocalMusicItem(ViewGroup parent, LocalMusicFactory itemFactory) {
           super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_local_music,parent,false), itemFactory);
       }

       @Override
       protected void onFindViews(View convertView) {
           this.convertView=convertView;
           name = (TextView) convertView.findViewById(R.id.tv_itemLocal_name);
           path = (TextView) convertView.findViewById(R.id.tv_itemLocal_path);
       }
       @Override
       protected void onConfigViews(Context context) {
           convertView.setOnClickListener(v -> mOnClickLisener.onClickConvertView(getPosition()));
       }

       @Override
       protected void onSetData(int position, Audio audio) {
           name.setText(TextUtils.isEmpty(audio.getAlbum())?"":audio.getTitle());
           path.setText(TextUtils.isEmpty(audio.getPath())?"":audio.getPath());
       }
   }

    public interface OnClickListener {
        void onClickConvertView(int position);
    }
}
