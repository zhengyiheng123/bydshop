package xyd.com.bydshop.customview;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import xyd.com.bydshop.R;


/**
 * Created by ${zxl} on 2017/4/6.
 * Describe:   订单下拉选择pop
 * CHange:
 */

public abstract class DingdanPop extends PopupWindow {

    private Context context;
    private String title;

    public DingdanPop(Context context, String title) {
        super(context);
        this.context = context;
        this.title=title;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_dingdan, null);
        ListView listView = (ListView) view.findViewById(R.id.pop_dingdan_lv);
        TextView textView= (TextView) view.findViewById(R.id.pop_dingdan_title);
        View v=view.findViewById(R.id.pop_dingdan_view);
        if (title.equals("全部"))
            v.setBackgroundResource(R.mipmap.ic_quanbu);
        else if (title.equals("按导游"))
            v.setBackgroundResource(R.mipmap.ic_zhineng);
        else v.setBackgroundResource(R.mipmap.ic_shaixuan);
        textView.setText(title);
        setData(listView);
        setContentView(view);
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        } else {
            this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        }
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.dialogWindowAnim);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(55000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    protected abstract void setData(ListView listView);

}
