package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

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
import xyd.com.bydshop.customview.TimeDialog;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.entity.TimeModel;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/13.
 * D: 营业时间
 * C:
 */

public class YingyeTime1Activity extends BaseActivity {


    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.yingye_tv_1_morning_start)
    TextView yingyeTv1MorningStart;
    @Bind(R.id.yingye_tv_1_morning_end)
    TextView yingyeTv1MorningEnd;
    @Bind(R.id.yingye_tv_1_afternoon_start)
    TextView yingyeTv1AfternoonStart;
    @Bind(R.id.yingye_tv_1_afternoon_end)
    TextView yingyeTv1AfternoonEnd;
    @Bind(R.id.yingye_tv_2_morning_start)
    TextView yingyeTv2MorningStart;
    @Bind(R.id.yingye_tv_2_morning_end)
    TextView yingyeTv2MorningEnd;
    @Bind(R.id.yingye_tv_2_afternoon_start)
    TextView yingyeTv2AfternoonStart;
    @Bind(R.id.yingye_tv_2_afternoon_end)
    TextView yingyeTv2AfternoonEnd;
    @Bind(R.id.yingye_tv_3_morning_start)
    TextView yingyeTv3MorningStart;
    @Bind(R.id.yingye_tv_3_morning_end)
    TextView yingyeTv3MorningEnd;
    @Bind(R.id.yingye_tv_3_afternoon_start)
    TextView yingyeTv3AfternoonStart;
    @Bind(R.id.yingye_tv_3_afternoon_end)
    TextView yingyeTv3AfternoonEnd;
    @Bind(R.id.yingye_tv_4_morning_start)
    TextView yingyeTv4MorningStart;
    @Bind(R.id.yingye_tv_4_morning_end)
    TextView yingyeTv4MorningEnd;
    @Bind(R.id.yingye_tv_4_afternoon_start)
    TextView yingyeTv4AfternoonStart;
    @Bind(R.id.yingye_tv_4_afternoon_end)
    TextView yingyeTv4AfternoonEnd;
    @Bind(R.id.yingye_tv_5_morning_start)
    TextView yingyeTv5MorningStart;
    @Bind(R.id.yingye_tv_5_morning_end)
    TextView yingyeTv5MorningEnd;
    @Bind(R.id.yingye_tv_5_afternoon_start)
    TextView yingyeTv5AfternoonStart;
    @Bind(R.id.yingye_tv_5_afternoon_end)
    TextView yingyeTv5AfternoonEnd;
    @Bind(R.id.yingye_tv_6_morning_start)
    TextView yingyeTv6MorningStart;
    @Bind(R.id.yingye_tv_6_morning_end)
    TextView yingyeTv6MorningEnd;
    @Bind(R.id.yingye_tv_6_afternoon_start)
    TextView yingyeTv6AfternoonStart;
    @Bind(R.id.yingye_tv_6_afternoon_end)
    TextView yingyeTv6AfternoonEnd;
    @Bind(R.id.yingye_tv_7_morning_start)
    TextView yingyeTv7MorningStart;
    @Bind(R.id.yingye_tv_7_morning_end)
    TextView yingyeTv7MorningEnd;
    @Bind(R.id.yingye_tv_7_afternoon_start)
    TextView yingyeTv7AfternoonStart;
    @Bind(R.id.yingye_tv_7_afternoon_end)
    TextView yingyeTv7AfternoonEnd;
    @Bind(R.id.yingye_tv_edt)
    EditText yingyeTvEdt;
    @Bind(R.id.yingye_ll)
    LinearLayout yingyeLl;

    private List<TimeModel.TimeListBean> timeLists;
    private List<TimeModel.TimeListBean> upTimeLists;
    private TextView[] morningStart;
    private TextView[] morningEnd;
    private TextView[] afternoonStart;
    private TextView[] afternoonEnd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yingye_time1;
    }

    @Override
    protected void initView() {
        morningStart = new TextView[]{yingyeTv1MorningStart, yingyeTv2MorningStart, yingyeTv3MorningStart, yingyeTv4MorningStart,
                yingyeTv5MorningStart, yingyeTv6MorningStart, yingyeTv7MorningStart};
        morningEnd = new TextView[]{yingyeTv1MorningEnd, yingyeTv2MorningEnd, yingyeTv3MorningEnd, yingyeTv4MorningEnd,
                yingyeTv5MorningEnd, yingyeTv6MorningEnd, yingyeTv7MorningEnd};
        afternoonStart = new TextView[]{yingyeTv1AfternoonStart, yingyeTv2AfternoonStart, yingyeTv3AfternoonStart, yingyeTv4AfternoonStart,
                yingyeTv5AfternoonStart, yingyeTv6AfternoonStart, yingyeTv7AfternoonStart};
        afternoonEnd = new TextView[]{yingyeTv1AfternoonEnd, yingyeTv2AfternoonEnd, yingyeTv3AfternoonEnd, yingyeTv4AfternoonEnd,
                yingyeTv5AfternoonEnd, yingyeTv6AfternoonEnd, yingyeTv7AfternoonEnd};
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvTitle.setVisibility(View.VISIBLE);
        titleTvTitle.setText("营业时间");
        titleTvRight.setVisibility(View.VISIBLE);
        titleTvRight.setText("保存");
        timeLists = new ArrayList<>();
        upTimeLists = new ArrayList<>();
        getTime();


    }

    private void getTime() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .time(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<TimeModel>>compose())
                .subscribe(new BaseObserver<TimeModel>() {
                    @Override
                    protected void onHandleSuccess(TimeModel timeModel, int code, String msg) {
                        yingyeTvEdt.setText(timeModel.getTime_desc());
                        timeLists.clear();
                        timeLists.addAll(timeModel.getTime_list());
                        for (TimeModel.TimeListBean bean : timeLists) {
                            setTimeView(bean);
                        }
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });


    }


    private void setTimeView(TimeModel.TimeListBean bean) {
        switch (bean.getDay_type()) {
            case 1:
                yingyeTv1AfternoonEnd.setText(bean.getEnd_pm());
                yingyeTv1AfternoonStart.setText(bean.getStart_pm());
                yingyeTv1MorningEnd.setText(bean.getEnd_am());
                yingyeTv1MorningStart.setText(bean.getStart_am());
                break;
            case 2:
                yingyeTv2AfternoonEnd.setText(bean.getEnd_pm());
                yingyeTv2AfternoonStart.setText(bean.getStart_pm());
                yingyeTv2MorningEnd.setText(bean.getEnd_am());
                yingyeTv2MorningStart.setText(bean.getStart_am());
                break;
            case 3:
                yingyeTv3AfternoonEnd.setText(bean.getEnd_pm());
                yingyeTv3AfternoonStart.setText(bean.getStart_pm());
                yingyeTv3MorningEnd.setText(bean.getEnd_am());
                yingyeTv3MorningStart.setText(bean.getStart_am());
                break;
            case 4:
                yingyeTv4AfternoonEnd.setText(bean.getEnd_pm());
                yingyeTv4AfternoonStart.setText(bean.getStart_pm());
                yingyeTv4MorningEnd.setText(bean.getEnd_am());
                yingyeTv4MorningStart.setText(bean.getStart_am());
                break;
            case 5:
                yingyeTv5AfternoonEnd.setText(bean.getEnd_pm());
                yingyeTv5AfternoonStart.setText(bean.getStart_pm());
                yingyeTv5MorningEnd.setText(bean.getEnd_am());
                yingyeTv5MorningStart.setText(bean.getStart_am());
                break;
            case 6:
                yingyeTv6AfternoonEnd.setText(bean.getEnd_pm());
                yingyeTv6AfternoonStart.setText(bean.getStart_pm());
                yingyeTv6MorningEnd.setText(bean.getEnd_am());
                yingyeTv6MorningStart.setText(bean.getStart_am());
                break;
            case 7:
                yingyeTv7AfternoonEnd.setText(bean.getEnd_pm());
                yingyeTv7AfternoonStart.setText(bean.getStart_pm());
                yingyeTv7MorningEnd.setText(bean.getEnd_am());
                yingyeTv7MorningStart.setText(bean.getStart_am());
                break;


        }

    }


    private void chooseTime(final TextView view) {
        TimeDialog dialog = new TimeDialog(this, new TimeDialog.OnTimeSelectedListener() {
            @Override
            public void onSelected(String hour, String minute) {
                view.setText(hour + ":" + minute);
            }
        });
        dialog.show();
    }


    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.title_tv_right, R.id.yingye_tv_1_morning_start, R.id.yingye_tv_1_morning_end, R.id.yingye_tv_1_afternoon_start, R.id.yingye_tv_1_afternoon_end, R.id.yingye_tv_2_morning_start, R.id.yingye_tv_2_morning_end, R.id.yingye_tv_2_afternoon_start, R.id.yingye_tv_2_afternoon_end, R.id.yingye_tv_3_morning_start, R.id.yingye_tv_3_morning_end, R.id.yingye_tv_3_afternoon_start, R.id.yingye_tv_3_afternoon_end, R.id.yingye_tv_4_morning_start, R.id.yingye_tv_4_morning_end, R.id.yingye_tv_4_afternoon_start, R.id.yingye_tv_4_afternoon_end, R.id.yingye_tv_5_morning_start, R.id.yingye_tv_5_morning_end, R.id.yingye_tv_5_afternoon_start, R.id.yingye_tv_5_afternoon_end, R.id.yingye_tv_6_morning_start, R.id.yingye_tv_6_morning_end, R.id.yingye_tv_6_afternoon_start, R.id.yingye_tv_6_afternoon_end, R.id.yingye_tv_7_morning_start, R.id.yingye_tv_7_morning_end, R.id.yingye_tv_7_afternoon_start, R.id.yingye_tv_7_afternoon_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.title_tv_right:
                addTime();
                break;
            case R.id.yingye_tv_1_morning_start:
                chooseTime(yingyeTv1MorningStart);
                break;
            case R.id.yingye_tv_1_morning_end:
                chooseTime(yingyeTv1MorningEnd);
                break;
            case R.id.yingye_tv_1_afternoon_start:
                chooseTime(yingyeTv1AfternoonStart);
                break;
            case R.id.yingye_tv_1_afternoon_end:
                chooseTime(yingyeTv1AfternoonEnd);
                break;
            case R.id.yingye_tv_2_morning_start:
                chooseTime(yingyeTv2MorningStart);
                break;
            case R.id.yingye_tv_2_morning_end:
                chooseTime(yingyeTv2MorningEnd);
                break;
            case R.id.yingye_tv_2_afternoon_start:
                chooseTime(yingyeTv2AfternoonStart);
                break;
            case R.id.yingye_tv_2_afternoon_end:
                chooseTime(yingyeTv2AfternoonEnd);
                break;
            case R.id.yingye_tv_3_morning_start:
                chooseTime(yingyeTv3MorningStart);
                break;
            case R.id.yingye_tv_3_morning_end:
                chooseTime(yingyeTv3MorningEnd);
                break;
            case R.id.yingye_tv_3_afternoon_start:
                chooseTime(yingyeTv3AfternoonStart);
                break;
            case R.id.yingye_tv_3_afternoon_end:
                chooseTime(yingyeTv3AfternoonEnd);
                break;
            case R.id.yingye_tv_4_morning_start:
                chooseTime(yingyeTv4MorningStart);
                break;
            case R.id.yingye_tv_4_morning_end:
                chooseTime(yingyeTv4MorningEnd);
                break;
            case R.id.yingye_tv_4_afternoon_start:
                chooseTime(yingyeTv4AfternoonStart);
                break;
            case R.id.yingye_tv_4_afternoon_end:
                chooseTime(yingyeTv4AfternoonEnd);
                break;
            case R.id.yingye_tv_5_morning_start:
                chooseTime(yingyeTv5MorningStart);
                break;
            case R.id.yingye_tv_5_morning_end:
                chooseTime(yingyeTv5MorningEnd);
                break;
            case R.id.yingye_tv_5_afternoon_start:
                chooseTime(yingyeTv5AfternoonStart);
                break;
            case R.id.yingye_tv_5_afternoon_end:
                chooseTime(yingyeTv5AfternoonEnd);
                break;
            case R.id.yingye_tv_6_morning_start:
                chooseTime(yingyeTv6MorningStart);
                break;
            case R.id.yingye_tv_6_morning_end:
                chooseTime(yingyeTv6MorningEnd);
                break;
            case R.id.yingye_tv_6_afternoon_start:
                chooseTime(yingyeTv6AfternoonStart);
                break;
            case R.id.yingye_tv_6_afternoon_end:
                chooseTime(yingyeTv6AfternoonEnd);
                break;
            case R.id.yingye_tv_7_morning_start:
                chooseTime(yingyeTv7MorningStart);
                break;
            case R.id.yingye_tv_7_morning_end:
                chooseTime(yingyeTv7MorningEnd);
                break;
            case R.id.yingye_tv_7_afternoon_start:
                chooseTime(yingyeTv7AfternoonStart);
                break;
            case R.id.yingye_tv_7_afternoon_end:
                chooseTime(yingyeTv7AfternoonEnd);
                break;
        }
    }

    private void addTime() {
        upTimeLists.clear();
        TimeModel.TimeListBean bean;
        for (int i = 0; i < 7; i++) {
            bean = new TimeModel.TimeListBean();
            bean.setDay_type(i + 1);
            bean.setEnd_am(morningEnd[i].getText().toString());
            bean.setEnd_pm(afternoonEnd[i].getText().toString());
            bean.setMt_id(0);
            bean.setStart_am(morningStart[i].getText().toString());
            bean.setStart_pm(afternoonStart[i].getText().toString());
            upTimeLists.add(bean);
        }
        for (int i = 0; i < timeLists.size(); i++) {
            upTimeLists.get(timeLists.get(i).getDay_type() - 1).setMt_id(timeLists.get(i).getMt_id());
        }
        Gson gson = new Gson();
        gson.toJson(timeLists);
        gson.toJson(upTimeLists);
        Log.e("timeLists", gson.toJson(timeLists));
        Log.e("upTimeLists", gson.toJson(upTimeLists));
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .updateTime(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), gson.toJson(upTimeLists), yingyeTvEdt.getText().toString())
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        showToast(msg);
                        getTime();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }
}
