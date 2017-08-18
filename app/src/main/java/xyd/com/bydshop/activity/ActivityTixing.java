package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.R;

/**
 * Created by ${zxl} on 2017/4/11.
 * D:消息提醒设置
 * C:
 */

public class ActivityTixing extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.tixing_tv_kaiguan)
    Switch tixingTvKaiguan;
    @Bind(R.id.tixing_tv_xinxiang)
    Switch tixingTvXinxiang;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_tixing;
    }

    @Override
    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("消息提醒设置");
    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.tixing_tv_kaiguan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.tixing_tv_kaiguan:

                break;
        }
    }

    @OnClick(R.id.tixing_tv_xinxiang)
    public void onViewClicked() {
    }
}
