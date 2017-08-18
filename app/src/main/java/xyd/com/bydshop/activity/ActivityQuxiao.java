package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.entity.CancelConditionModel;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.entity.IntroductionModel;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/10.
 * D:  取消条件
 * C:
 */

public class ActivityQuxiao extends BaseActivity {
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.liuyan_edt)
    EditText liuyanEdt;
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.liuyan_cb)
    CheckBox cb;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_liuyan;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setTextSize(18);
        if (PublicStaticData.isCanyin) {
            titleTvBack.setText("取消条件");
            liuyanEdt.setHint("请填写取消条件~");
            cb.setVisibility(View.VISIBLE);
            getData();
        } else {
            titleTvBack.setText("订票说明");
            liuyanEdt.setHint("请填写订票说明~");
            cb.setVisibility(View.GONE);
            getJingdian();
        }
        titleTvRight.setVisibility(View.VISIBLE);
        titleTvRight.setText("保存");


    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .cancel_condition(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<CancelConditionModel>>compose())
                .subscribe(new BaseObserver<CancelConditionModel>() {
                    @Override
                    protected void onHandleSuccess(CancelConditionModel s, int code, String msg) {
                        if (s.getCancel_condition().equals("-1")) {
                            cb.setChecked(false);
                        } else if (s.getCancel_condition().equals("0")) {
                            liuyanEdt.setText("订单不可取消");
                            cb.setChecked(true);
                        } else {
                            cb.setChecked(false);
                            liuyanEdt.setText(s.getCancel_condition());
                        }
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    private void getJingdian() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .introduction(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<IntroductionModel>>compose())
                .subscribe(new BaseObserver<IntroductionModel>() {
                    @Override
                    protected void onHandleSuccess(IntroductionModel introductionModel, int code, String msg) {
                        liuyanEdt.setText(introductionModel.getIntroduction());
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.title_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.title_tv_right:
                if (PublicStaticData.isCanyin)
                    editCanyin();
                else
                    editJingdian();

                break;
        }
    }

    private void editCanyin() {
        String s = "";
        if (cb.isChecked()) {
            s = "0";
        } else {
            if (TextUtils.isEmpty(liuyanEdt.getText().toString()))
                s = "-1";
            else
                s = liuyanEdt.getText().toString();
        }
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .cancel_condition(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), s)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel model, int code, String msg) {
                        showToast(msg);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });

    }

    private void editJingdian() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .introduction(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),liuyanEdt.getText().toString())
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel model, int code, String msg) {
                        showToast(msg);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }
}
