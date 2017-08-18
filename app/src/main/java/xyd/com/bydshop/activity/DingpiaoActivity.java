package xyd.com.bydshop.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.R;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/15
 * @time: 10:17
 * @description:   订票说明
 */

public class DingpiaoActivity extends BaseActivity {
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
        titleTvBack.setText("订票说明");
        titleTvRight.setVisibility(View.VISIBLE);
        titleTvRight.setText("保存");
        liuyanEdt.setHint("请填写订票说明~");
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
                break;
        }
    }
}