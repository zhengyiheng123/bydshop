package xyd.com.bydshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.MainActivity;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.activity.PushMessageActivity;
import xyd.com.bydshop.adapter.DingdanAdapter;
import xyd.com.bydshop.adapter.JindianDingdanAdapter;
import xyd.com.bydshop.customview.CommentViewHolder;
import xyd.com.bydshop.customview.DingdanPop;
import xyd.com.bydshop.customview.DingdanTimePop;
import xyd.com.bydshop.customview.MyCommonAdapter;
import xyd.com.bydshop.entity.GliderModel;
import xyd.com.bydshop.entity.NormalSceneModel;
import xyd.com.bydshop.entity.OrderModel;
import xyd.com.bydshop.jpush.PushMessage;
import xyd.com.bydshop.recyclerview.ProgressStyle;
import xyd.com.bydshop.recyclerview.RecyclerViewOnitemClickListener;
import xyd.com.bydshop.recyclerview.ZXLRecyclerView;
import xyd.com.bydshop.serviceapi.JingdianOrderApi;
import xyd.com.bydshop.serviceapi.OrderApi;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.LogUtil;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.TimeUtils;
import xyd.com.bydshop.utils.ToastUtils;


/**
 * Created by ${zxl} on 2017/4/5.
 * Describe:  订单
 * CHange:
 */

public class DingdanFragment extends Fragment {

    @Bind(R.id.title_tv_title)
    TextView title;
    @Bind(R.id.title_tv_right)
    TextView right;
    @Bind(R.id.title_tv_red)
    ImageView red;
    @Bind(R.id.dingdan_tv_all)
    TextView all;
    @Bind(R.id.dingdan_tv_daoyou)
    TextView daoyou;
    @Bind(R.id.dingdan_tv_shijian)
    TextView shijian;
    @Bind(R.id.dingdan_zxlRv)
    ZXLRecyclerView dingdanZxlRv;
    private int index = 0, current = 0;
    private List<String> poplist;
    private DingdanAdapter adapter;
    private List<OrderModel.OrdersBean> orderList;
    private int page = 0;
    String ord_status = "";
    String user_id = "";
    String start_time = "";
    String end_time = "";
    private List<GliderModel.GliderBean> gliderList;
    private List<NormalSceneModel.OrdersBean> normalScenes;
    private String sub_cate_id;
    private JindianDingdanAdapter jingdianAdaper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dingdan, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initData();
        sub_cate_id = PublicStaticData.sharedPreferences.getString(PublicStaticData.SUB_CATE_ID, "");
        if (PublicStaticData.isCanyin) {
            initCanyin();
        } else {
            //子级分类id 5|普通景区 6|演出类 7|火车游船
            if (sub_cate_id.equals("5"))
                initJingdian();
            else if (sub_cate_id.equals("6"))
                initShowJingdian();
            else if (sub_cate_id.equals("7"))
                initCarJingdian();

        }

        return view;

    }
    @Subscribe
    public void onEvent(PushMessage  message){
        red.setVisibility(View.VISIBLE);
    }

    private void initShowJingdian() {
        normalScenes=new ArrayList<>();
        jingdianAdaper = new JindianDingdanAdapter(getActivity(),normalScenes,sub_cate_id);
        dingdanZxlRv.setAdapter(jingdianAdaper);
        getShowJingdian();


    }

    private void initJingdian() {
        normalScenes=new ArrayList<>();
        jingdianAdaper = new JindianDingdanAdapter(getActivity(),normalScenes,sub_cate_id);
        dingdanZxlRv.setAdapter(jingdianAdaper);
        getJingdian();

    }

    /**
     * 游船类
     */
    private void initCarJingdian() {
        normalScenes=new ArrayList<>();
        jingdianAdaper = new JindianDingdanAdapter(getActivity(),normalScenes,sub_cate_id);
        dingdanZxlRv.setAdapter(jingdianAdaper);
        getTrain();

    }

    private void getTrain() {
        BaseApi.getRetrofit().create(JingdianOrderApi.class)
                .trainOrder(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        page + "", ord_status + "", user_id + "", start_time, end_time)
                .compose(RxSchedulers.<BaseModel<NormalSceneModel>>compose())
                .subscribe(new BaseObserver<NormalSceneModel>() {
                    @Override
                    protected void onHandleSuccess(NormalSceneModel normalSceneModel, int code, String msg) {
                        if (page == 0) {
                            normalScenes.clear();
                        }
                        normalScenes.addAll(normalSceneModel.getOrders());
                        jingdianAdaper.notifyDataSetChanged();
                        dingdanZxlRv.refreshComplete();
                        dingdanZxlRv.loadMoreComplete();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                        dingdanZxlRv.refreshComplete();
                        dingdanZxlRv.loadMoreComplete();
                    }
                });
    }

    /**
     * 表演景点类
     */
    private void getShowJingdian() {
        BaseApi.getRetrofit().create(JingdianOrderApi.class)
                .showOrder(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        page + "", ord_status + "", user_id + "", start_time, end_time)
                .compose(RxSchedulers.<BaseModel<NormalSceneModel>>compose())
                .subscribe(new BaseObserver<NormalSceneModel>() {
                    @Override
                    protected void onHandleSuccess(NormalSceneModel normalSceneModel, int code, String msg) {
                        if (page == 0) {
                            normalScenes.clear();
                        }
                        normalScenes.addAll(normalSceneModel.getOrders());
                        jingdianAdaper.notifyDataSetChanged();
                        dingdanZxlRv.refreshComplete();
                        dingdanZxlRv.loadMoreComplete();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                        dingdanZxlRv.refreshComplete();
                        dingdanZxlRv.loadMoreComplete();
                    }
                });

    }

    /**
     * 普通景点类
     */
    private void getJingdian() {
        BaseApi.getRetrofit().create(JingdianOrderApi.class)
                .normalOrder(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        page + "", ord_status + "", user_id + "", start_time, end_time)
                .compose(RxSchedulers.<BaseModel<NormalSceneModel>>compose())
                .subscribe(new BaseObserver<NormalSceneModel>() {
                    @Override
                    protected void onHandleSuccess(NormalSceneModel normalSceneModel, int code, String msg) {
                        if (page == 0) {
                            normalScenes.clear();
                        }
                        normalScenes.addAll(normalSceneModel.getOrders());
                        jingdianAdaper.notifyDataSetChanged();
                        dingdanZxlRv.refreshComplete();
                        dingdanZxlRv.loadMoreComplete();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                        dingdanZxlRv.refreshComplete();
                        dingdanZxlRv.loadMoreComplete();
                    }
                });

    }

    private void initCanyin() {
        orderList = new ArrayList<>();
        adapter = new DingdanAdapter(getActivity(), orderList);
        dingdanZxlRv.setAdapter(adapter);
        getOrders();
    }

    private void initData() {
        title.setText("订单");
        title.setVisibility(View.VISIBLE);
        right.setVisibility(View.VISIBLE);
        red.setVisibility(View.GONE);
        right.setText("消息");
        gliderList = new ArrayList<GliderModel.GliderBean>();
        poplist = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dingdanZxlRv.setLayoutManager(layoutManager);
        dingdanZxlRv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        dingdanZxlRv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        dingdanZxlRv.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        dingdanZxlRv.setPullRefreshEnabled(true);
        getGlider();

        dingdanZxlRv.setLoadingListener(new ZXLRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                if (PublicStaticData.isCanyin) {
                    getOrders();
                } else {
                    //子级分类id 5|普通景区 6|演出类 7|火车游船
                    if (sub_cate_id.equals("5"))
                        getJingdian();
                    else if (sub_cate_id.equals("6"))
                        getShowJingdian();
                    else if (sub_cate_id.equals("7"))
                        getTrain();
                }

            }

            @Override
            public void onLoadMore() {
                page++;
                if (PublicStaticData.isCanyin) {
                    getOrders();
                } else {
                    //子级分类id 5|普通景区 6|演出类 7|火车游船
                    if (sub_cate_id.equals("5"))
                        getJingdian();
                    else if (sub_cate_id.equals("6"))
                        getShowJingdian();
                    else if (sub_cate_id.equals("7"))
                        getTrain();
                }
            }
        });

    }

    /**
     * 获取订单列表
     */
    private void getOrders() {

        BaseApi.getRetrofit()
                .create(OrderApi.class)
                .order(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        page + "", ord_status + "", user_id + "", start_time, end_time)
                .compose(RxSchedulers.<BaseModel<OrderModel>>compose())
                .subscribe(new BaseObserver<OrderModel>() {
                    @Override
                    protected void onHandleSuccess(OrderModel orderModel, int code, String msg) {
                        if (page == 0) {
                            orderList.clear();
                        }
                        orderList.addAll(orderModel.getOrders());
                        adapter.notifyDataSetChanged();
                        dingdanZxlRv.refreshComplete();
                        dingdanZxlRv.loadMoreComplete();

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                        dingdanZxlRv.refreshComplete();
                        dingdanZxlRv.loadMoreComplete();
                    }
                });

    }

    private void getGlider() {
        BaseApi.getRetrofit()
                .create(OrderApi.class)
                .set(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<GliderModel>>compose())
                .subscribe(new BaseObserver<GliderModel>() {
                    @Override
                    protected void onHandleSuccess(GliderModel gliderModel, int code, String msg) {

                        gliderList.clear();
                        gliderList.addAll(gliderModel.getUsers());
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                    }
                });
    }


    @OnClick({R.id.dingdan_tv_all, R.id.dingdan_tv_daoyou, R.id.dingdan_tv_shijian,R.id.title_tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dingdan_tv_all:
                index = 0;
                changTextColors();
                break;
            case R.id.dingdan_tv_daoyou:
                index = 1;
                changTextColors();
                break;
            case R.id.dingdan_tv_shijian:
                index = 2;
                changTextColors();
                break;
            case R.id.title_tv_right:
                Intent intent =new Intent(getActivity(), PushMessageActivity.class);
                startActivity(intent);
                red.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 切换字体颜色
     * a:判断是否滑动切换
     */
    private void changTextColors() {
        if (current == index) {
            current = -1;
        }
        switch (index) {
            case 0:
                all.setTextColor(getResources().getColor(R.color.text_blue));
                setAll();
                break;
            case 1:
                daoyou.setTextColor(getResources().getColor(R.color.text_blue));
                setDaoyou();
                break;
            case 2:
                shijian.setTextColor(getResources().getColor(R.color.text_blue));
                setTime();
                break;
        }

        switch (current) {
            case 0:
                all.setTextColor(getResources().getColor(R.color.c_333333));

                break;
            case 1:
                daoyou.setTextColor(getResources().getColor(R.color.c_333333));

                break;
            case 2:
                shijian.setTextColor(getResources().getColor(R.color.c_333333));

                break;
        }
        current = index;


    }

    /**
     * 按全部
     */
    private void setAll() {
        //-1|已取消 0|待接单 1|待付款 2|已付款 3|已完成
        poplist.clear();
        poplist.add("已取消");
        poplist.add("待接单");
        poplist.add("待付款");
        poplist.add("已付款");
        poplist.add("已完成");
        poplist.add("全部");
        DingdanPop pop = new DingdanPop(getActivity(), "全部") {
            @Override
            protected void setData(final ListView listView) {
                listView.setAdapter(new MyCommonAdapter<String>(poplist, getActivity(), R.layout.item_pop_dingdan) {
                    @Override
                    public void setDate(CommentViewHolder commentViewHolder, int position) {

                        ((TextView) commentViewHolder.FindViewById(R.id.item_pop_tv)).setText(poplist.get(position));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                all.setText(poplist.get(position));
                                switch (position) {
                                    case 0:
                                        ord_status = -1 + "";
                                        break;
                                    case 1:
                                        ord_status = 0 + "";
                                        break;
                                    case 2:
                                        ord_status = 1 + "";
                                        break;
                                    case 3:
                                        ord_status = 2 + "";
                                        break;
                                    case 4:
                                        ord_status = 3 + "";
                                        break;
                                    case 5:
                                        ord_status = "";
                                        break;

                                }
                                user_id = "";
                                start_time = "";
                                end_time = "";
                                page = 0;
                                if (PublicStaticData.isCanyin) {
                                    getOrders();
                                } else {
                                    //子级分类id 5|普通景区 6|演出类 7|火车游船
                                    if (sub_cate_id.equals("5"))
                                        getJingdian();
                                    else if (sub_cate_id.equals("6"))
                                        getShowJingdian();
                                    else if (sub_cate_id.equals("7"))
                                        getTrain();
                                }
                                dismiss();
                            }
                        });
                    }
                });

            }
        };
        pop.showAsDropDown(all, 0, -15);
    }

    /**
     * 按时间段搜索
     */
    private void setTime() {
        final DingdanTimePop pop = new DingdanTimePop(getActivity(), new DingdanTimePop.OnCheckTimeListener() {
            @Override
            public void checkTime(String start, String end) {
              //  Toast.makeText(getActivity(), start + "----" + end, Toast.LENGTH_SHORT).show();
                all.setText("全部");
                start_time = TimeUtils.string2Millis(start.replace("年","-").replace("月","-").replace("日",""),"yyyy-MM-dd")/1000+"";
                end_time = TimeUtils.string2Millis(end.replace("年","-").replace("月","-").replace("日",""),"yyyy-MM-dd")/1000+"";
                ord_status = "";
                user_id = "";
                page = 0;
                if (PublicStaticData.isCanyin) {
                    getOrders();
                } else {
                    //子级分类id 5|普通景区 6|演出类 7|火车游船
                    if (sub_cate_id.equals("5"))
                        getJingdian();
                    else if (sub_cate_id.equals("6"))
                        getShowJingdian();
                    else if (sub_cate_id.equals("7"))
                        getTrain();
                }
            }
        });
        pop.showAsDropDown(all, 0, -15);

    }

    /**
     * 按导游搜索
     */
    private void setDaoyou() {
        poplist.clear();
        for (GliderModel.GliderBean bean : gliderList) {
            poplist.add(bean.getUser_name());
        }
        DingdanPop pop = new DingdanPop(getActivity(), "按导游") {
            @Override
            protected void setData(final ListView listView) {
                listView.setAdapter(new MyCommonAdapter<String>(poplist, getActivity(), R.layout.item_pop_dingdan) {
                    @Override
                    public void setDate(CommentViewHolder commentViewHolder, int position) {
                        ((TextView) commentViewHolder.FindViewById(R.id.item_pop_tv)).setText(poplist.get(position));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // daoyou.setText(poplist.get(position));
                                all.setText("全部");
                                user_id = gliderList.get(position).getUser_id() + "";
                                ord_status = "";
                                start_time = "";
                                end_time = "";
                                dismiss();
                                page = 0;
                                if (PublicStaticData.isCanyin) {
                                    getOrders();
                                } else {
                                    //子级分类id 5|普通景区 6|演出类 7|火车游船
                                    if (sub_cate_id.equals("5"))
                                        getJingdian();
                                    else if (sub_cate_id.equals("6"))
                                        getShowJingdian();
                                    else if (sub_cate_id.equals("7"))
                                        getTrain();
                                }
                            }
                        });
                    }
                });

            }
        };
        pop.showAsDropDown(all, 0, -15);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
