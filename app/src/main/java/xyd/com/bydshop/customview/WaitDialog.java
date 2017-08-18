package xyd.com.bydshop.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import xyd.com.bydshop.R;


/**
 * Created by ${zxl} on 2017/4/6.
 * Describe: 通用加载dialog
 * CHange:
 */

public class WaitDialog extends Dialog {

    public WaitDialog(Context context) {
        this(context, R.layout.dialog_loading, R.style.DialogTheme1);
    }
    public WaitDialog(Context context, int layout, int style) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        //setCanceledOnTouchOutside(true);
        //window.setWindowAnimations(R.style.dialogWindowAnim);

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //params.gravity = Gravity.BOTTOM;
        //params.alpha = 0.6f; //背景透明
        window.setAttributes(params);
    }





}
