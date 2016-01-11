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

public class ItemShow extends RelativeLayout {
    public ImageView ivAlbum;
    public TextView tvTitle;
    public TextView tvDesc;

    public ItemShow(Context context) {
        super(context);
        initData(context, null);
    }


    public ItemShow(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public ItemShow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.widget_item_show, this, true);
        ivAlbum = (ImageView) layout.findViewById(R.id.iv_item_album);
        tvTitle = (TextView) layout.findViewById(R.id.tv_item_title);
        tvDesc = (TextView) layout.findViewById(R.id.tv_item_desc);
    }

}
