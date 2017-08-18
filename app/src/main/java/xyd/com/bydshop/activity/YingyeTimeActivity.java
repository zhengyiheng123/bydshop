package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.R;
import xyd.com.bydshop.customview.TimeDialog;

/**
 * Created by ${zxl} on 2017/4/13.
 * D: 营业时间
 * C:
 */

public class YingyeTimeActivity extends BaseActivity {


    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.yingye_tv_normal_morning_start)
    TextView yingyeTvNormalMorningStart;
    @Bind(R.id.yingye_tv_normal_morning_end)
    TextView yingyeTvNormalMorningEnd;
    @Bind(R.id.yingye_tv_normal_afternoon_start)
    TextView yingyeTvNormalAfternoonStart;
    @Bind(R.id.yingye_tv_normal_afternoon_end)
    TextView yingyeTvNormalAfternoonEnd;
    @Bind(R.id.yingye_tv_weekend_morning_start)
    TextView yingyeTvWeekendMorningStart;
    @Bind(R.id.yingye_tv_weekend_morning_end)
    TextView yingyeTvWeekendMorningEnd;
    @Bind(R.id.yingye_tv_weekend_afternoon_start)
    TextView yingyeTvWeekendAfternoonStart;
    @Bind(R.id.yingye_tv_weekend_afternoon_end)
    TextView yingyeTvWeekendAfternoonEnd;
    @Bind(R.id.yingye_tv_add)
    TextView yingyeTvAdd;
    @Bind(R.id.yingye_ll)
    LinearLayout yingyeLl;
    // private View [] views=new View[10];
    private ArrayList<View> views;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yingye_time;
    }

    @Override
    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvTitle.setVisibility(View.VISIBLE);
        titleTvTitle.setText("营业时间");
        views=new ArrayList<>();

    }


    @OnClick({R.id.title_iv_back, R.id.yingye_tv_normal_morning_start, R.id.yingye_tv_normal_morning_end, R.id.yingye_tv_normal_afternoon_start, R.id.yingye_tv_normal_afternoon_end, R.id.yingye_tv_weekend_morning_start, R.id.yingye_tv_weekend_morning_end, R.id.yingye_tv_weekend_afternoon_start, R.id.yingye_tv_weekend_afternoon_end, R.id.yingye_tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.yingye_tv_normal_morning_start:
                chooseTime(yingyeTvNormalMorningStart);
                break;
            case R.id.yingye_tv_normal_morning_end:
                chooseTime(yingyeTvNormalMorningEnd);
                break;
            case R.id.yingye_tv_normal_afternoon_start:
                chooseTime(yingyeTvNormalAfternoonStart);
                break;
            case R.id.yingye_tv_normal_afternoon_end:
                chooseTime(yingyeTvNormalAfternoonEnd);
                break;
            case R.id.yingye_tv_weekend_morning_start:
                chooseTime(yingyeTvWeekendMorningStart);
                break;
            case R.id.yingye_tv_weekend_morning_end:
                chooseTime(yingyeTvWeekendMorningEnd);
                break;
            case R.id.yingye_tv_weekend_afternoon_start:
                chooseTime(yingyeTvWeekendAfternoonStart);
                break;
            case R.id.yingye_tv_weekend_afternoon_end:
                chooseTime(yingyeTvWeekendAfternoonEnd);
                break;
            case R.id.yingye_tv_add:
                if (views.size()>9){
                    showToast("最多添加十条特殊情况");
                }else {
                    addView();
                }

                break;
        }
    }

    private void addView() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_special_date, null);
        views.add(view);
        yingyeLl.addView(view);
        setViewListener(view,views.size()-1);
    }



    private void chooseTime( final TextView view) {
        TimeDialog dialog = new TimeDialog(this, new TimeDialog.OnTimeSelectedListener() {
            @Override
            public void onSelected(String hour, String minute) {
                view.setText(hour + ":" + minute);
            }
        });
        dialog.show();
    }
    private void setViewListener(final View view, final int index) {
        final TextView morningStart= (TextView) view.findViewById(R.id.special_tv_morning_start);
        final TextView morningEnd= (TextView) view.findViewById(R.id.special_tv_morning_end);
        final TextView afternoonStart= (TextView) view.findViewById(R.id.special_tv_afternoon_start);
        final TextView afternoonEnd= (TextView) view.findViewById(R.id.special_tv_afternoon_end);
        final TextView start= (TextView) view.findViewById(R.id.special_tv_start);
        final TextView end= (TextView) view.findViewById(R.id.special_tv_end);
        TextView delete= (TextView) view.findViewById(R.id.special_tv_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                views.remove(view);
                yingyeLl.removeView(view);
            }
        });
        morningStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTime(morningStart);
            }
        });
        morningEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTime(morningEnd);
            }
        });
        afternoonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTime(afternoonStart);
            }
        });
        afternoonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTime(afternoonEnd);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTime(start);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTime(end);
            }
        });

    }
}
