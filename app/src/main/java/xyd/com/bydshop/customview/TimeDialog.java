package xyd.com.bydshop.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Arrays;
import java.util.List;

import xyd.com.bydshop.R;
import xyd.com.bydshop.flowlayout.TagFlowLayout;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/28
 * @time: 11:52
 * @description:
 */

public class TimeDialog extends CustomDialog {


    private TextView no;
    private TextView yes;
    private TextView title;
    private WheelView hour;
    private WheelView minute;
    private OnTimeSelectedListener onTimeSelectedListener;

    public TimeDialog(Context context, final OnTimeSelectedListener onTimeSelectedListener) {
        super(context, R.layout.dialog_time_choose, true);
        this.onTimeSelectedListener = onTimeSelectedListener;
        no = (TextView) findViewById(R.id.time_tv_close);
        yes = (TextView) findViewById(R.id.time_tv_ok);
        title = (TextView) findViewById(R.id.time_tv_title);
        hour = (WheelView) findViewById(R.id.time_wv_hour);
        minute = (WheelView) findViewById(R.id.time_wv_minute);
        String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutes = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

        hour.setItems(Arrays.asList(hours));
        hour.setOffset(1);
        hour.setSeletion(0);
        minute.setItems(Arrays.asList(minutes));
        minute.setOffset(1);
        minute.setSeletion(0);


        hour.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                title.setText(hour.getSeletedItem() + "点" + minute.getSeletedItem() + "分");
            }
        });
        minute.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                title.setText(hour.getSeletedItem() + "点" + minute.getSeletedItem() + "分");

            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimeSelectedListener.onSelected(hour.getSeletedItem(), minute.getSeletedItem());
                dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface OnTimeSelectedListener {
        void onSelected(String hour, String minute);
    }
}
