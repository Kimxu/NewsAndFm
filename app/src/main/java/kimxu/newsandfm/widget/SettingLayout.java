package kimxu.newsandfm.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kimxu.newsandfm.R;

/**
 * 自定义使用组件
 * Created by xuzhiguo on 15/12/21.
 */
public class SettingLayout extends RelativeLayout {
    private TextView tvName;
    private TextView tvSubName;

    public SettingLayout(Context context) {
        super(context);
        initData(context, null);
    }

    public SettingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public SettingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.widget_setting_install, this, true);
        tvName= (TextView) layout.findViewById(R.id.setting_install_name);
        tvSubName= (TextView) layout.findViewById(R.id.setting_install_sub_name);
        setViewDatas(context, attrs);
    }
    private void setViewDatas(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingLayout);
        String name = typedArray.getString(R.styleable.SettingLayout_setting_title);
        tvName.setText(name);
        String subName = typedArray.getString(R.styleable.SettingLayout_setting_sub_title);
        if (TextUtils.isEmpty(subName)){
            tvSubName.setVisibility(GONE);
        }
        tvSubName.setText(subName);

        typedArray.recycle();
    }
}
