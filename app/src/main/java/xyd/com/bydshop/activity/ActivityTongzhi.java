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
import xyd.com.bydshop.R;

/**
 * Created by ${zxl} on 2017/4/10.
 * D:  官方通知
 * C:
 */

public class ActivityTongzhi extends BaseActivity {
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



    @Override
    protected int getLayoutId() {
        return R.layout.activity_liuyan;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setTextSize(18);
        titleTvBack.setText("官方通知");
        titleTvRight.setVisibility(View.VISIBLE);
        titleTvRight.setText("提交");
        liuyanEdt.setHint("请填写需要提醒用户的信息~");
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
                if (TextUtils.isEmpty(liuyanEdt.getText().toString()))
                    showToast("请填写信息");
                else
                    showToast("提交成功");
                break;
        }
    }
}
