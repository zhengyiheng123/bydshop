package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.customview.CircleImageView;
import xyd.com.bydshop.entity.CriticizeModel;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.flowlayout.FlowLayout;
import xyd.com.bydshop.flowlayout.TagAdapter;
import xyd.com.bydshop.flowlayout.TagFlowLayout;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.serviceapi.JingdianOrderApi;
import xyd.com.bydshop.serviceapi.OrderApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/12.
 * D: 评价
 * C:
 */

public class PingjiaActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.pingjia_head)
    ImageView pingjiaHead;
    @Bind(R.id.pingjia_tv_name)
    TextView pingjiaTvName;
    @Bind(R.id.pingia_tv_renzheng)
    TextView pingiaTvRenzheng;
    @Bind(R.id.pingjia_tfl)
    TagFlowLayout pingjiaTfl;
    private int rank = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pingjia;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvRight.setVisibility(View.VISIBLE);
        titleTvBack.setText("评价");
        titleTvRight.setText("确定");
        if (PublicStaticData.isCanyin)
            getData();
        else
            getJingdianData();
        // -1|拉黑 0|未设置 1|一般 2|可信任
        String[] s = new String[]{"拉黑", "一般", "可信任"};
        TagAdapter<String> adapter = new TagAdapter<String>(s) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(PingjiaActivity.this).inflate(R.layout.tag_change_order, parent, false);
                tv.setText(s);
                return tv;
            }
        };
        pingjiaTfl.setMaxSelectCount(1);
        pingjiaTfl.setAdapter(adapter);
        pingjiaTfl.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                switch (position) {
                    case 0:
                        rank = -1;
                        break;
                    case 1:
                        rank = 1;
                        break;
                    case 2:
                        rank = 2;
                        break;
                }
                return false;
            }
        });
    }

    private void getJingdianData() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .criticize(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        getIntent().getStringExtra("order_id"))
                .compose(RxSchedulers.<BaseModel<CriticizeModel>>compose())
                .subscribe(new BaseObserver<CriticizeModel>() {
                    @Override
                    protected void onHandleSuccess(CriticizeModel criticizeModel, int code, String msg) {
                        GlideUtil.getInstance().loadCircleImage(PingjiaActivity.this, pingjiaHead, PublicStaticData.URL + criticizeModel.getAvatar());
                        pingjiaTvName.setText(criticizeModel.getUser_name());
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(OrderApi.class)
                .criticize(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        getIntent().getStringExtra("order_id"))
                .compose(RxSchedulers.<BaseModel<CriticizeModel>>compose())
                .subscribe(new BaseObserver<CriticizeModel>() {
                    @Override
                    protected void onHandleSuccess(CriticizeModel criticizeModel, int code, String msg) {
                        GlideUtil.getInstance().loadCircleImage(PingjiaActivity.this, pingjiaHead, PublicStaticData.URL + criticizeModel.getAvatar());
                        pingjiaTvName.setText(criticizeModel.getUser_name());
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.title_tv_right, R.id.pingjia_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.title_tv_right:
                //确认
                if (rank == 0) {
                    showToast("请选择评价标签！");
                } else {
                    if (PublicStaticData.isCanyin)
                        commit();
                    else
                        commitJingdian();
                }

                break;
            case R.id.pingjia_head:

                break;
        }
    }

    private void commitJingdian() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .saveCriticize(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        getIntent().getStringExtra("order_id"), rank + "")
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

    /**
     * 提交评论
     */
    private void commit() {
        BaseApi.getRetrofit()
                .create(OrderApi.class)
                .saveCriticize(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        getIntent().getStringExtra("order_id"), rank + "")
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
