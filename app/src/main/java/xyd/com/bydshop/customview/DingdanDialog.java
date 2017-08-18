package xyd.com.bydshop.customview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import xyd.com.bydshop.R;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/18
 * @time: 15:29
 * @description:
 */

public abstract class DingdanDialog extends CustomDialog {
    private TextView title;
    private ImageView ok;
    private ImageView cancle;

    public DingdanDialog(Context context,String s) {
        super(context, R.layout.item_customdialog);
        title= (TextView) findViewById(R.id.custom_dialog_title);
        title.setText(s);
        ok= (ImageView) findViewById(R.id.custom_dialog_ok);
        cancle= (ImageView) findViewById(R.id.custom_dialog_cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                call(true);
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                call(false);
            }
        });
    }



    public abstract void call(boolean b);
}
