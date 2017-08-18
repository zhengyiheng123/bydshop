package xyd.com.bydshop.customview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import xyd.com.bydshop.R;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/18
 * @time: 16:04
 * @description:
 */

public abstract class XianlianxiDialog extends Dialog {

    private final TextView phone;

    public XianlianxiDialog(Context context) {
        super(context, R.style.DialogTheme1);
        setContentView(R.layout.pop_xianlianli);
        Window window = getWindow();
        //setCanceledOnTouchOutside(true);
        window.setWindowAnimations(R.style.dialogWindowAnim);

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM;
        //params.alpha = 0.6f; //背景透明
        window.setAttributes(params);
        RelativeLayout rl= (RelativeLayout) findViewById(R.id.xianlianxi_rl);
        phone = (TextView) findViewById(R.id.tv_phone);
      TextView liuyan= (TextView) findViewById(R.id.tv_liuyan);

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack(true);
                dismiss();
            }
        });
        liuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack(false);
                dismiss();
            }
        });
    }

    /**
     *
     * @param phone  true 拨打电话
     *               false 留言
     */
    public abstract void callBack(boolean phone);
    public void setNumber(String s){
        phone.setText(s);
    }
}
