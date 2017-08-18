package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.serviceapi.JingdianOrderApi;
import xyd.com.bydshop.serviceapi.OrderApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/14.
 * D: 取消原因  （去掉）
 * C:
 */

public class QxYuanyinActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_tv_right)
    TextView titleRight;
    @Bind(R.id.qx_yy_edt)
    EditText qxYyEdt;
    @Bind(R.id.qx_yy_rg_rbt1)
    RadioButton qxYyRgRbt1;
    @Bind(R.id.qx_yy_rg_rbt2)
    RadioButton qxYyRgRbt2;
    @Bind(R.id.qx_yy_rg_rbt3)
    RadioButton qxYyRgRbt3;
    @Bind(R.id.qx_yy_rg_rbt4)
    RadioButton qxYyRgRbt4;
    @Bind(R.id.qx_yy_rg)
    RadioGroup qxYyRg;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_quxiao_yuanxin;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("确定");
        titleTvBack.setText("取消原因");
        if (PublicStaticData.isCanyin) {
            qxYyRgRbt1.setText("1.座位已满");
            qxYyRgRbt2.setText("2.客户想要取消");
            qxYyRgRbt3.setText("3.本店厨师临时有事");
            qxYyRgRbt4.setText("4.其他");
        } else {
            qxYyRgRbt1.setText("1.临时有事");
            qxYyRgRbt2.setText("2.天气原因");
            qxYyRgRbt3.setText("3.景区原因");
            qxYyRgRbt4.setText("4.其他");
        }
        qxYyRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {
                    case R.id.qx_yy_rg_rbt1:

                        qxYyEdt.setText(getString(qxYyRgRbt1.getText().toString()));
                        break;
                    case R.id.qx_yy_rg_rbt2:
                        qxYyEdt.setText(getString(qxYyRgRbt2.getText().toString()));
                        break;
                    case R.id.qx_yy_rg_rbt3:
                        qxYyEdt.setText(getString(qxYyRgRbt3.getText().toString()));
                        break;
                    case R.id.qx_yy_rg_rbt4:
                        //  qxYyEdt.setText(getString(qxYyRgRbt4.getText().toString()));
                        qxYyEdt.getText().clear();
                        break;
                }
            }
        });
    }

    private String getString(String s) {
        if (TextUtils.isEmpty(s))
            return "";
        else
            return s.substring(2, s.length());
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
                if (TextUtils.isEmpty(qxYyEdt.getText())) {
                    showToast("请填写取消原因");
                } else {
                    if (PublicStaticData.isCanyin)
                        qxOrder();
                    else
                        qxJingdianOrder();

                }
                break;
        }
    }

    private void qxJingdianOrder() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .refuse(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        getIntent().getStringExtra("order_id"), qxYyEdt.getText().toString())
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        finish();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    private void qxOrder() {
        BaseApi.getRetrofit()
                .create(OrderApi.class)
                .qxOrder(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        getIntent().getStringExtra("order_id"), qxYyEdt.getText().toString())
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        finish();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }
}
