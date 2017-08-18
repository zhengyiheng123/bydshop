package xyd.com.bydshop.customview;

import android.app.Activity;
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
import xyd.com.bydshop.runtimepermissions.PermissionUtils;
import xyd.com.bydshop.utils.ActivityFactory;


/**
 * Created by ${zxl} on 2017/4/6.
 * Describe:   订单下拉选择pop
 * CHange:
 */

public  class LianxiPop extends PopupWindow {

    private Context context;
    private String title;
    private String phone1;
    private Activity activity;

    public LianxiPop(Activity activity,Context context, String title,String phone) {
        super(context);
        this.context = context;
        this.title=title;
        this.phone1=phone;
        this.activity=activity;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_xianlianli, null);
        TextView textView= (TextView) view.findViewById(R.id.tv_liuyan);
        final TextView phone= (TextView) view.findViewById(R.id.tv_phone);
        textView.setText(title);

        setContentView(view);
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//            this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
//        } else {
//            this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
//        }
        setClippingEnabled(false);
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
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityFactory.goLiuyan(context,null);
                dismiss();
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.phone(activity, new PermissionUtils.OnPermissionResult() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + phone1);
                        intent.setData(data);
                        context.startActivity(intent);
                        dismiss();
                    }
                });

            }
        });

    }



}
