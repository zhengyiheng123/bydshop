package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
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
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/11.
 * D: 意见反馈
 * C:
 */

public class ActivityYijian extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.yijian_edt)
    EditText yijianEdt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yijianfankui;
    }

    @Override
    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvRight.setVisibility(View.VISIBLE);
        titleTvRight.setText("保存");
        titleTvBack.setText("意见反馈");
    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.yijian_tv_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.yijian_tv_tijiao:
                //提交
                if (TextUtils.isEmpty(yijianEdt.getText().toString()))
                    showToast("请填写意见反馈");
                else
                    commit(yijianEdt.getText().toString());
                break;
        }
    }

    private void commit(String s) {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .suggestion(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), s)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        showToast(msg);
                    }
                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

}
