package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.R;
import xyd.com.bydshop.adapter.MyViewpagerAdapter;
import xyd.com.bydshop.fragment.ForgetEmailFragment;
import xyd.com.bydshop.fragment.ForgetPhoneFragment;
import xyd.com.bydshop.fragment.RegisterEmailFragment;
import xyd.com.bydshop.fragment.RegisterPhoneFragment;

/**
 * Created by ${zxl} on 2017/4/12.
 * D:  注册
 * C:
 */

public class RegisterActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.forget_tab)
    TabLayout forgetTab;
    @Bind(R.id.forget_vp)
    ViewPager forgetVp;
    private RegisterPhoneFragment phoneFragment;
    private RegisterEmailFragment emailFragment;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvTitle.setVisibility(View.VISIBLE);
        titleTvTitle.setText("忘记密码");
        String [] s=new String[]{"手机注册","邮箱注册"};
        forgetTab.addTab(forgetTab.newTab().setText(s[0]));
        forgetTab.addTab(forgetTab.newTab().setText(s[1]));
        phoneFragment = new RegisterPhoneFragment();
        emailFragment = new RegisterEmailFragment();
        Fragment [] fragments=new Fragment[]{phoneFragment,emailFragment};
        MyViewpagerAdapter adapter=new MyViewpagerAdapter(getSupportFragmentManager(),fragments,s);
        forgetTab.setupWithViewPager(forgetVp);
        forgetTab.setTabsFromPagerAdapter(adapter);
        forgetVp.setAdapter(adapter);

    }

    @OnClick(R.id.title_iv_back)
    public void onViewClicked() {
        finish();
    }
}
