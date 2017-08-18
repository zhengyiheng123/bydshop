package xyd.com.bydshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.adapter.CanyinTuanAdapter;
import xyd.com.bydshop.adapter.JingdianAdapter;
import xyd.com.bydshop.adapter.MyViewpagerAdapter;
import xyd.com.bydshop.entity.CanyinMealModel;
import xyd.com.bydshop.entity.NormalRouteModel;
import xyd.com.bydshop.entity.RoutesModel;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.recyclerview.ZXLRecyclerView;
import xyd.com.bydshop.serviceapi.JingdianOrderApi;
import xyd.com.bydshop.serviceapi.MealApi;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.ToastUtils;


/**
 * Created by ${zxl} on 2017/4/5.
 * Describe:  订单
 * CHange:
 */

public class ShangpinFragment extends Fragment {

    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.shangpin_tab)
    TabLayout shangpinTab;
    @Bind(R.id.shangpin_vp)
    ViewPager shangpinVp;
    @Bind(R.id.shangpin_listview)
    ZXLRecyclerView recyclerView;
    @Bind(R.id.shangpin_image)
    ImageView shangpinImage;
    @Bind(R.id.shangpin_scrollView)
    ScrollView shangpinScrollView;
    private ShangpinChildTuanFragemnt tuangouFragment;
    private ShangpinChildDanFragemnt danndianFragment;
    private String sub_cate_id;
    private List<RoutesModel.RoutesBean> jdRoutes;
    private JingdianAdapter jdAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shangpin, container, false);
        ButterKnife.bind(this, view);
        initView();
        sub_cate_id = PublicStaticData.sharedPreferences.getString(PublicStaticData.SUB_CATE_ID, "");
        if (PublicStaticData.isCanyin) {
            recyclerView.setVisibility(View.GONE);
            getCanyinData();
        } else {
            initJingdian();
        }

        return view;

    }

    private void getCanyinData() {
        BaseApi.getRetrofit()
                .create(MealApi.class)
                .meal(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<CanyinMealModel>>compose())
                .subscribe(new BaseObserver<CanyinMealModel>() {
                    @Override
                    protected void onHandleSuccess(CanyinMealModel canyinMealModel, int code, String msg) {
                        ToastUtils.show(msg);
                        if (TextUtils.isEmpty(canyinMealModel.getPic_meal())){
                            initCanyin(canyinMealModel);
                        }else{
                            initImage(canyinMealModel);
                        }

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                    }
                });
    }

    private void initJingdian() {
        recyclerView.setVisibility(View.VISIBLE);
        shangpinTab.setVisibility(View.GONE);
        shangpinVp.setVisibility(View.GONE);
        jdRoutes = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        jdAdapter = new JingdianAdapter(jdRoutes, getActivity());
        recyclerView.setAdapter(jdAdapter);
        if (sub_cate_id.equals("5"))
            getNormalData();
        else if (sub_cate_id.equals("6"))
            getShowData();
        else if (sub_cate_id.equals("7"))
            getTrailData();

    }
    private void getTrailData() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .trainlRoute(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN,""))
                .compose(RxSchedulers.<BaseModel<RoutesModel>>compose())
                .subscribe(new BaseObserver<RoutesModel>() {
                    @Override
                    protected void onHandleSuccess(RoutesModel normalRouteModel, int code, String msg) {
                        ToastUtils.showDebug(msg);
                        jdAdapter.setImageUrl(normalRouteModel.getLogo_path());
                        jdRoutes.clear();
                        jdRoutes.addAll(normalRouteModel.getRoutes());
                        jdAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.showDebug(msg);
                    }
                });


    }

    private void getShowData() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .showlRoute(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN,""))
                .compose(RxSchedulers.<BaseModel<RoutesModel>>compose())
                .subscribe(new BaseObserver<RoutesModel>() {
                    @Override
                    protected void onHandleSuccess(RoutesModel normalRouteModel, int code, String msg) {
                        ToastUtils.showDebug(msg);
                        jdAdapter.setImageUrl(normalRouteModel.getLogo_path());
                        jdRoutes.clear();
                        jdRoutes.addAll(normalRouteModel.getRoutes());
                        jdAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                       ToastUtils.showDebug(msg);
                    }
                });


    }

    private void getNormalData() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .normalRoute(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN,""))
                .compose(RxSchedulers.<BaseModel<RoutesModel>>compose())
                .subscribe(new BaseObserver<RoutesModel>() {
                    @Override
                    protected void onHandleSuccess(RoutesModel normalRouteModel, int code, String msg) {
                        ToastUtils.showDebug(msg);
                        jdAdapter.setImageUrl(normalRouteModel.getLogo_path());
                        jdRoutes.clear();
                        jdRoutes.addAll(normalRouteModel.getRoutes());
                        jdAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.showDebug(msg);
                    }
                });


    }

    private void initCanyin(CanyinMealModel canyinMealModel) {

        shangpinTab.setVisibility(View.VISIBLE);
        shangpinVp.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        String[] strings = new String[]{"团餐", "单点"};
        tuangouFragment = new ShangpinChildTuanFragemnt();
        danndianFragment = new ShangpinChildDanFragemnt();
        Bundle b = new Bundle();
        b.putSerializable("meal", canyinMealModel);
        tuangouFragment.setArguments(b);
        danndianFragment.setArguments(b);

        Fragment[] fragments = new Fragment[]{tuangouFragment, danndianFragment};
        shangpinTab.addTab(shangpinTab.newTab().setText(strings[0]));
        shangpinTab.addTab(shangpinTab.newTab().setText(strings[1]));
        // shangpinTab.addTab(shangpinTab.newTab().setText(strings[2]));
        MyViewpagerAdapter adapter = new MyViewpagerAdapter(getChildFragmentManager(), fragments, strings);
        shangpinVp.setAdapter(adapter);
        shangpinVp.setOffscreenPageLimit(1);
        shangpinTab.setupWithViewPager(shangpinVp);
        shangpinTab.setTabsFromPagerAdapter(adapter);
    }

    private void initImage(CanyinMealModel model) {
        shangpinTab.setVisibility(View.GONE);
        shangpinVp.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        shangpinScrollView.setVisibility(View.VISIBLE);
        GlideUtil.getInstance().loadImage(getActivity(), shangpinImage, PublicStaticData.URL + model.getPic_meal(), true);


    }

    private void initView() {
        titleTvTitle.setVisibility(View.VISIBLE);
        titleTvTitle.setText("商品");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
