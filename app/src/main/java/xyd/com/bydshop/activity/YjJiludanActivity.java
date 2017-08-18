package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
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
import xyd.com.bydshop.adapter.DingdanAdapter;
import xyd.com.bydshop.adapter.YjJiludanAdapter;
import xyd.com.bydshop.entity.JiludanModel;
import xyd.com.bydshop.recyclerview.ProgressStyle;
import xyd.com.bydshop.recyclerview.ZXLRecyclerView;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/14.
 * D: 意见记录单
 * C:
 */

public class YjJiludanActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.yj_jiludan_rv)
    ZXLRecyclerView yjJiludanRv;
    int page = 0;
    private List<JiludanModel.CommentsBean> comments;
    private YjJiludanAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yj_jiludan;
    }

    @Override
    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("意见记录单");
        comments = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yjJiludanRv.setLayoutManager(layoutManager);
        yjJiludanRv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        yjJiludanRv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        yjJiludanRv.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        yjJiludanRv.setPullRefreshEnabled(true);
        adapter = new YjJiludanAdapter(this, comments);
        yjJiludanRv.setAdapter(adapter);
        if (PublicStaticData.isCanyin)
            getData();
        else
            getJingdian();
        yjJiludanRv.setLoadingListener(new ZXLRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                if (PublicStaticData.isCanyin)
                    getData();
                else
                    getJingdian();
            }

            @Override
            public void onLoadMore() {
                page++;
                if (PublicStaticData.isCanyin)
                    getData();
                else
                    getJingdian();

            }
        });

    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .res_comment(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), page)
                .compose(RxSchedulers.<BaseModel<JiludanModel>>compose())
                .subscribe(new BaseObserver<JiludanModel>() {
                    @Override
                    protected void onHandleSuccess(JiludanModel jiludanModel, int code, String msg) {
                        if (page == 0)
                            comments.clear();
                        comments.addAll(jiludanModel.getComments());
                        adapter.notifyDataSetChanged();
                        yjJiludanRv.loadMoreComplete();
                        yjJiludanRv.refreshComplete();


                    }

                    @Override
                    protected void onHandleError(String msg) {
                        yjJiludanRv.loadMoreComplete();
                        yjJiludanRv.refreshComplete();
                        showToast(msg);
                    }
                });
    }

    public void getJingdian() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .scnene_comment(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), page)
                .compose(RxSchedulers.<BaseModel<JiludanModel>>compose())
                .subscribe(new BaseObserver<JiludanModel>() {
                    @Override
                    protected void onHandleSuccess(JiludanModel jiludanModel, int code, String msg) {
                        if (page == 0)
                            comments.clear();
                        comments.addAll(jiludanModel.getComments());
                        adapter.notifyDataSetChanged();
                        yjJiludanRv.loadMoreComplete();
                        yjJiludanRv.refreshComplete();


                    }

                    @Override
                    protected void onHandleError(String msg) {
                        yjJiludanRv.loadMoreComplete();
                        yjJiludanRv.refreshComplete();
                        showToast(msg);
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
