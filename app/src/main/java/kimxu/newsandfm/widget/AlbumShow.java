package kimxu.newsandfm.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kimxu.newsandfm.R;

/**
 *
 * Created by kimxu on 16/1/11.
 */

public class AlbumShow extends RelativeLayout {
    public ImageView ivAlbum;
    public TextView tvTitle;
    public TextView tvSum;

    public AlbumShow(Context context) {
        super(context);
        initData(context, null);
    }


    public AlbumShow(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public AlbumShow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.glist_item_hot_playlist, this, true);
        ivAlbum = (ImageView) layout.findViewById(R.id.iv_album);
        tvTitle = (TextView) layout.findViewById(R.id.tv_album_title);
        tvSum = (TextView) layout.findViewById(R.id.tv_album_sum);
    }

}
