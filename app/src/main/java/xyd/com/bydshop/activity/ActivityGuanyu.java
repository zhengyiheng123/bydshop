package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.R;

/**
 * Created by ${zxl} on 2017/4/10.
 * D:  关于帮预定
 * C:
 */

public class ActivityGuanyu extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_guanyu;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("关于帮预定");
    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.guanyu_tv_jieshao, R.id.guanyu_tv_tiaowen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.guanyu_tv_jieshao:
                //帮预定介绍
                break;
            case R.id.guanyu_tv_tiaowen:
                //免责声明
                break;
        }
    }
}
