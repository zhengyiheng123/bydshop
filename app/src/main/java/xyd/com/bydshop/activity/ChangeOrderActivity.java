package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.R;
import xyd.com.bydshop.flowlayout.FlowLayout;
import xyd.com.bydshop.flowlayout.TagAdapter;
import xyd.com.bydshop.flowlayout.TagFlowLayout;
import xyd.com.bydshop.utils.ActivityFactory;

/**
 * Created by ${zxl} on 2017/4/12.
 * D:  注册下一步选择
 * C:
 */

public class ChangeOrderActivity extends BaseActivity {
    public  static  final String SHOP_TYPE ="shop_type";
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.change_tfl)
    TagFlowLayout changeTfl;
    private TagAdapter<String> adapter;
    private String[] string;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_order;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("选择商家性质");
        //1|中餐 2|特色餐 3|景区门票
        string = new String[]{"中餐", "特色餐","景区门票"};
        adapter = new TagAdapter<String>(string) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(ChangeOrderActivity.this).inflate(R.layout.tag_change_order1, parent, false);
                tv.setText(s);
                return tv;
            }
        };
        changeTfl.setAdapter(adapter);
        changeTfl.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                //商家详情
                Bundle bundle=new Bundle();
                if (position == 0) {
                    bundle.putString(SHOP_TYPE,"1");
                    ActivityFactory.goRegisterCanyin(ChangeOrderActivity.this,bundle);

                } else if(position==1){
                    bundle.putString(SHOP_TYPE,"2");
                    ActivityFactory.goRegisterCanyin(ChangeOrderActivity.this,bundle);
                }else{
                    bundle.putString(SHOP_TYPE,"3");
                    ActivityFactory.goRegisterJingdian(ChangeOrderActivity.this,bundle);
                }

                return false;
            }
        });
    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
        }
    }
}
