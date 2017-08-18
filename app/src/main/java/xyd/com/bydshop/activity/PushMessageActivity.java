package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
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
import xyd.com.bydshop.adapter.PushMessageAdapter;
import xyd.com.bydshop.adapter.PushRvAdapter;
import xyd.com.bydshop.entity.PushMessageModel;
import xyd.com.bydshop.recyclerview.ProgressStyle;
import xyd.com.bydshop.recyclerview.ZXLRecyclerView;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/15
 * @time: 10:48
 * @description:
 */

public class PushMessageActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.push_lv)
    ListView pushLv;
    @Bind(R.id.push_zxlRv)
    ZXLRecyclerView pushZxlRv;
    //  @Bind(R.id.push_srl)
    // SwipeRefreshLayout pushSrl;
    private List<PushMessageModel.NoticesBean> notices;
    private PushRvAdapter rvAdapter;
    // private PushMessageAdapter adapter;
    int page = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_push_message;
    }

    @Override
    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvTitle.setVisibility(View.VISIBLE);
        titleTvTitle.setText("消息");
        //  pushSrl.setOnRefreshListener(this);
        notices = new ArrayList<>();
        rvAdapter=new PushRvAdapter(this,notices);
//        adapter = new PushMessageAdapter(this,notices);
//        pushLv.setAdapter(adapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pushZxlRv.setLayoutManager(layoutManager);
        pushZxlRv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        pushZxlRv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        pushZxlRv.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        pushZxlRv.setPullRefreshEnabled(true);
        pushZxlRv.setAdapter(rvAdapter);

        pushZxlRv.setLoadingListener(new ZXLRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                getData();
            }

            @Override
            public void onLoadMore() {
                page++;
                getData();

            }
        });
        getData();

    }

    private void getData() {

        BaseApi.getRetrofit()
                .create(MineApi.class)
                .notice(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), page)
                .compose(RxSchedulers.<BaseModel<PushMessageModel>>compose())
                .subscribe(new BaseObserver<PushMessageModel>() {
                    @Override
                    protected void onHandleSuccess(PushMessageModel pushMessageModel, int code, String msg) {
                        //  pushSrl.setRefreshing(false);
                        pushZxlRv.loadMoreComplete();
                        pushZxlRv.refreshComplete();
                        if (page == 0)
                            notices.clear();
                        notices.addAll(pushMessageModel.getNotices());
                        rvAdapter.notifyDataSetChanged();
                        //  adapter.notifyDataSetChanged();


                    }

                    @Override
                    protected void onHandleError(String msg) {
                        //pushSrl.setRefreshing(false);
                        pushZxlRv.loadMoreComplete();
                        pushZxlRv.refreshComplete();
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

    @Override
    public void onRefresh() {
        getData();
    }
}
