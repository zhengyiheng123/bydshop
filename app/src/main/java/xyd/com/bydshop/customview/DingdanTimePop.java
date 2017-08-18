package xyd.com.bydshop.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyd.com.bydshop.R;
import xyd.com.bydshop.utils.Utils;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/27
 * @time: 12:03
 * @description:  订单部分选择时间pop
 */

public class DingdanTimePop extends PopupWindow {

    TextView popDingdanTitle;
    TextView popTimeTvStart;
    TextView popTimeTvEnd;
    TextView popTimeTvReset;
    TextView popTimeTvOk;
    private Context context;
    private DatePicker datePicker;
    private int state = 0;
    private OnCheckTimeListener onCheckTimeListener;

    public DingdanTimePop(Context context,OnCheckTimeListener onCheckTimeListener) {
        super(context);
        this.context = context;
        this.onCheckTimeListener=onCheckTimeListener;
        initView();

    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_dingdan_time, null);
        popDingdanTitle = (TextView) view.findViewById(R.id.pop_dingdan_title);
        popTimeTvStart = (TextView) view.findViewById(R.id.pop_time_tv_start);
        popTimeTvEnd = (TextView) view.findViewById(R.id.pop_time_tv_end);
        popTimeTvReset = (TextView) view.findViewById(R.id.pop_time_tv_reset);
        popTimeTvOk = (TextView) view.findViewById(R.id.pop_time_tv_ok);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        view.findViewById(R.id.pop_time_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        final LinearLayout pop_time_ll = (LinearLayout) view.findViewById(R.id.pop_time_ll);
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
        this.setAnimationStyle(R.style.dialogWindowAnim);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(55000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (!Utils.inRangeOfView(pop_time_ll, event))
//                    dismiss();
//                return false;
//            }
//        });
        popTimeTvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTimeTvStart.setText("开始时间");
                popTimeTvEnd.setText("结束时间");
            }
        });
        popTimeTvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                state = 1;
            }
        });
        popTimeTvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                state = 2;
            }
        });
        popTimeTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popTimeTvStart.getText().toString().equals("开始时间") || popTimeTvEnd.getText().toString().equals("结束时间")) {
                    datePicker.setVisibility(View.GONE);
                    if (state == 1)
                        popTimeTvStart.setText(datePicker.getYear() + "年" + (datePicker.getMonth() + 1) + "月" + datePicker.getDayOfMonth()+"日");
                    else if (state==2)
                        popTimeTvEnd.setText(datePicker.getYear() + "年" + (datePicker.getMonth() + 1) + "月" + datePicker.getDayOfMonth()+"日");
                } else {
                     dismiss();
                    onCheckTimeListener.checkTime(popTimeTvStart.getText().toString(),popTimeTvEnd.getText().toString());
                }
            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
    public interface OnCheckTimeListener{
        void checkTime(String start,String end);
    }
}
