package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.calendar.CalendarView;
import xyd.com.bydshop.entity.CalendarModel;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.LogUtil;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.TimeUtils;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/24
 * @time: 19:30
 * @description:
 */

public class RiliActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;

    @Bind(R.id.txt_select_month)
    TextView txtSelectMonth;
    @Bind(R.id.rili_calendarView)
    CalendarView riliCalendarView;
    private List<String> kexuan;
    private List<String> yixuan;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rili;
    }

    @Override
    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("行程日历");
        getData();
        kexuan = new ArrayList<>();

        yixuan = new ArrayList<>();


        txtSelectMonth.setText(riliCalendarView.getDate());
        riliCalendarView.setOnClickDate(new CalendarView.OnClickListener() {
            @Override
            public void onClickDateListener(int year, int month, int day) {
                String s;
                if (month < 10) {
                    s = year + "0" + month + "" + day + "";
                } else {
                    s = year + "" + month + "" + day + "";
                }

                if (yixuan.contains(s)) {
                   // showToast(s);
                }
            }
        });

    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .calendar(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<CalendarModel>>compose())
                .subscribe(new BaseObserver<CalendarModel>() {
                    @Override
                    protected void onHandleSuccess(CalendarModel calendarModel, int code, String msg) {
                        for (Long aa : calendarModel.getCalendar()) {
                            yixuan.add(TimeUtils.millis2String(aa * 1000, "yyyyMMdd"));
                          //  LogUtil.e(TimeUtils.millis2String(aa * 1000, "yyyyMMdd"));

                        }
                       // LogUtil.e(yixuan.size()+"");
                        riliCalendarView.setSelectedDates(yixuan);
                        //riliCalendarView.setOptionalDate(yixuan);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    @OnClick({R.id.title_iv_back, R.id.img_select_last_month, R.id.img_select_next_month})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.img_select_last_month:
                riliCalendarView.setLastMonth();
                txtSelectMonth.setText(riliCalendarView.getDate());
                break;
            case R.id.img_select_next_month:
                riliCalendarView.setNextMonth();
                txtSelectMonth.setText(riliCalendarView.getDate());
                break;
        }
    }
}
