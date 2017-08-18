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
import xyd.com.bydshop.serviceapi.JingdianOrderApi;
import xyd.com.bydshop.serviceapi.OrderApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/10.
 * D:  发表留言
 * C:
 */

public class ActivityLiuyan extends BaseActivity {
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.liuyan_edt)
    EditText liuyanEdt;
    private int o_id;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_liuyan;
    }

    protected void initView() {
        o_id = getIntent().getIntExtra("id", 0);
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("取消");
        titleTvTitle.setVisibility(View.VISIBLE);
        titleTvTitle.setText("发表留言");
        titleTvRight.setVisibility(View.VISIBLE);
        titleTvRight.setText("发表");
    }


    @OnClick({R.id.title_tv_back, R.id.title_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.title_tv_right:
                if (TextUtils.isEmpty(liuyanEdt.getText().toString())) {
                    showToast("请填写留言信息~");
                } else {
                    if (PublicStaticData.isCanyin)
                        canyin();
                    else
                        jingdian();
                }
                break;
        }
    }

    private void jingdian() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .msg(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), liuyanEdt.getText().toString(), o_id + "")
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

    private void canyin() {
        BaseApi.getRetrofit()
                .create(OrderApi.class)
                .msg(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), liuyanEdt.getText().toString(), o_id + "")
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
