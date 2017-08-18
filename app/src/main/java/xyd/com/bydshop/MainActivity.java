package xyd.com.bydshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.fragment.DingdanFragment;
import xyd.com.bydshop.fragment.KefuFragment;
import xyd.com.bydshop.fragment.ShangpinFragment;
import xyd.com.bydshop.fragment.WodeFragment;
import xyd.com.bydshop.runtimepermissions.PermissionsManager;
import xyd.com.bydshop.utils.StatusBarUtil;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    @Bind(R.id.main_iv_dingdan)
    ImageView mainIvDingdan;
    @Bind(R.id.main_iv_kefu)
    ImageView mainIvKefu;
    @Bind(R.id.main_iv_shangpin)
    ImageView mainIvShangpin;
    @Bind(R.id.main_iv_wode)
    ImageView mainIvWode;
    @Bind(R.id.main_ll_dingdan)
    LinearLayout mainLlDingdan;
    @Bind(R.id.main_ll_kefu)
    LinearLayout mainLlKefu;
    @Bind(R.id.main_ll_shangpin)
    LinearLayout mainLlShangpin;
    @Bind(R.id.main_ll_wode)
    LinearLayout mainLlWode;
    @Bind(R.id.main_tv_dingdan)
    TextView mainTvDingdan;
    @Bind(R.id.main_tv_kefu)
    TextView mainTvKefu;
    @Bind(R.id.main_tv_shangpin)
    TextView mainTvShangpin;
    @Bind(R.id.main_tv_wode)
    TextView mainTvWode;

    private DingdanFragment dingdanFragment;
    private KefuFragment kefuFragment;
    private ShangpinFragment shangpinFragment;
    private WodeFragment wodeFragment;
    private Fragment[] fragments;
    private int index = 0, currentTabIndex = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        dingdanFragment = new DingdanFragment();
        kefuFragment = new KefuFragment();
        shangpinFragment = new ShangpinFragment();
        wodeFragment = new WodeFragment();
        fragments = new Fragment[]{dingdanFragment, kefuFragment, shangpinFragment, wodeFragment};
        //默认 选中订单
        onTextClicked();
    }


    @OnClick({R.id.main_ll_dingdan, R.id.main_ll_kefu, R.id.main_ll_shangpin, R.id.main_ll_wode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_ll_dingdan:
                index = 0;
                mainTvDingdan.setTextColor(getResources().getColor(R.color.text_blue));
                mainIvDingdan.setImageResource(R.mipmap.tabicon32);
                if (currentTabIndex != index)
                    changeColor();
                break;
            case R.id.main_ll_kefu:
                index = 1;
                mainTvKefu.setTextColor(getResources().getColor(R.color.text_blue));
                mainIvKefu.setImageResource(R.mipmap.tabicon42);
                if (currentTabIndex != index)
                changeColor();
                break;
            case R.id.main_ll_shangpin:
                index = 2;
                mainTvShangpin.setTextColor(getResources().getColor(R.color.text_blue));
                mainIvShangpin.setImageResource(R.mipmap.shangpin);
                if (currentTabIndex != index)
                changeColor();

                break;
            case R.id.main_ll_wode:
                index = 3;
                mainTvWode.setTextColor(getResources().getColor(R.color.text_blue));
                mainIvWode.setImageResource(R.mipmap.a35);
                if (currentTabIndex != index)
                changeColor();
                break;
        }
        onTextClicked();

    }

    private void onTextClicked() {
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        if (currentTabIndex != index) {
            if (currentTabIndex != -1) {
                trx.hide(fragments[currentTabIndex]);
            }

            if (!fragments[index].isAdded()) {
                trx.add(R.id.main_fl, fragments[index]);
            }
            trx.show(fragments[index]).commit();
            currentTabIndex = index;
        }

    }

    private void changeColor() {
        //setStatusBar();
        switch (currentTabIndex) {
            case 0:
                mainIvDingdan.setImageResource(R.mipmap.tabicon3);
                mainTvDingdan.setTextColor(getResources().getColor(R.color.c_666666));
                break;
            case 1:
                mainIvKefu.setImageResource(R.mipmap.tabicon4);
                mainTvKefu.setTextColor(getResources().getColor(R.color.c_666666));
                break;
            case 2:
                mainIvShangpin.setImageResource(R.mipmap.gongnengshiyong);
                mainTvShangpin.setTextColor(getResources().getColor(R.color.c_666666));
                break;
            case 3:
                mainIvWode.setImageResource(R.mipmap.tabicon5);
                mainTvWode.setTextColor(getResources().getColor(R.color.c_666666));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
}
