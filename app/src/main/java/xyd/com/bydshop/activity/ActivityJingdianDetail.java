package xyd.com.bydshop.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import xyd.com.bydshop.customview.DingdanDialog;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.entity.IntroductionModel;
import xyd.com.bydshop.entity.NormalDetailModel;
import xyd.com.bydshop.entity.ShowDetailModel;
import xyd.com.bydshop.entity.TrainDetailModel;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.serviceapi.JingdianOrderApi;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.TimeUtils;
import xyd.com.bydshop.utils.ToastUtils;

/**
 * Created by ${zxl} on 2017/4/10.
 * D: 待接单景点详情
 * C:
 */

public class ActivityJingdianDetail extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.jd_lianxi)
    TextView jdLianxi;
    @Bind(R.id.jd_jiedan_no)
    TextView jdJiedanNo;
    @Bind(R.id.jd_jiedan)
    TextView jdJiedan;
    @Bind(R.id.jingdian_civ_head)
    ImageView jingdianCivHead;
    @Bind(R.id.jd_tv_name)
    TextView jdTvName;
    @Bind(R.id.jd_tv_renzheng)
    TextView jdTvRenzheng;
    @Bind(R.id.jd_tv_yudingren)
    TextView jdTvYudingren;
    @Bind(R.id.jd_tv_qupiaoren)
    TextView jdTvQupiaoren;
    @Bind(R.id.jd_tv_dianhua)
    TextView jdTvDianhua;
    @Bind(R.id.jd_tv_youxiang)
    TextView jdTvYouxiang;
    @Bind(R.id.jd_tv_type)
    TextView jdTvType;
    @Bind(R.id.jd_ll_detail)
    LinearLayout jdLlDetail;
    @Bind(R.id.jd_edt_liuyan)
    EditText jdEdtLiuyan;
    @Bind(R.id.jd_tv_xd_time)
    TextView jdTvXdTime;
    @Bind(R.id.jd_tv_dao_time)
    TextView jdTvDaoTime;
    @Bind(R.id.jd_tv_state)
    TextView jdTvState;
    @Bind(R.id.cy_tv_pay_way)
    TextView cyTvPayWay;
    @Bind(R.id.jd_ll_dao_time)
    LinearLayout jdLlDaoTime;
    @Bind(R.id.rl)
    LinearLayout rl;
    @Bind(R.id.jd_tv_price)
    TextView jdTvPrice;
    @Bind(R.id.jd_tv_shuoming)
    TextView jdTvShuoming;
    private String order_id;
    private String sub_cate_id;
    private int order_state;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jingdian_detail;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("详情");
        order_id = getIntent().getStringExtra("order_id");
        sub_cate_id = PublicStaticData.sharedPreferences.getString(PublicStaticData.SUB_CATE_ID, "");
        getJingdianShuoming();
        //子级分类id 5|普通景区 6|演出类 7|火车游船
        if (sub_cate_id.equals("5"))
            getJingdian();
        else if (sub_cate_id.equals("6"))
            getShowJingdian();
        else if (sub_cate_id.equals("7"))
            getCarJingdian();

    }

    /**
     * 获取订票说明
     */
    private void getJingdianShuoming() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .introduction(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<IntroductionModel>>compose())
                .subscribe(new BaseObserver<IntroductionModel>() {
                    @Override
                    protected void onHandleSuccess(IntroductionModel introductionModel, int code, String msg) {
                        jdTvShuoming.setText(introductionModel.getIntroduction());
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    private void getCarJingdian() {
        jdLlDaoTime.setVisibility(View.GONE);
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .trainOrderDetail(order_id, PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<TrainDetailModel>>compose())
                .subscribe(new BaseObserver<TrainDetailModel>() {
                    @Override
                    protected void onHandleSuccess(TrainDetailModel trainDetailModel, int code, String msg) {
                        showTestToast(msg);
                        addTrainDetailLayout(trainDetailModel);
                        GlideUtil.getInstance().loadCircleImage(ActivityJingdianDetail.this, jingdianCivHead, PublicStaticData.URL + trainDetailModel.getAvatar());
                        if (trainDetailModel.getTicketBean().getTicket_type()== 1)
                            jdTvType.setText("团票");
                        else
                            jdTvType.setText("普通票");
                        jdTvName.setText(trainDetailModel.getUser_name());
                        jdTvDianhua.setText(trainDetailModel.getMobile());
                        jdTvYudingren.setText(trainDetailModel.getUser_name());
                        jdTvQupiaoren.setText(trainDetailModel.getUser_name());
                        jdTvXdTime.setText(TimeUtils.millis2String((long) trainDetailModel.getAdd_time() * 1000));

                        jdTvPrice.setText("总计" + trainDetailModel.getPrice() + "元");
                        order_state = trainDetailModel.getOrd_status();

                        //  订单状态:-2|用户取消 -1|商家取消 0|待接单 1|待付款 2|已付款 3|已消费 4|已评价
                        switch (trainDetailModel.getOrd_status()) {
                            case -4:
                                jdTvState.setText("已退款");
                                rl.setVisibility(View.GONE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.GONE);
                                break;
                            case -3:
                                jdTvState.setText("待退款");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedan.setText("确认退款");
                                break;
                            case -2:
                                jdTvState.setText("用户取消");
                                rl.setVisibility(View.GONE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.GONE);
                                break;
                            case -1:
                                jdTvState.setText("商家取消");
                                rl.setVisibility(View.GONE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.GONE);
                                break;
                            case 0:
                                jdTvState.setText("待接单");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.VISIBLE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedanNo.setText("不接单");
                                jdJiedan.setText("接单");
                                break;
                            case 1:
                                jdTvState.setText("待付款");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                jdTvState.setText("已付款");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedan.setText("确认收款");

                                break;
                            case 3:
                                jdTvState.setText("已消费");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedan.setText("评价");

                                break;
                            case 4:
                                jdTvState.setText("已评价");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                break;
                        }
                        //支付方式: 1|到店支付 2|支付宝 3|微信 4|银联
                        switch (trainDetailModel.getPay_type()) {
                            case 1:
                                cyTvPayWay.setText("到店支付");
                                break;
                            case 2:
                                cyTvPayWay.setText("支付宝");
                                break;
                            case 3:
                                cyTvPayWay.setText("微信");
                                break;
                            case 4:
                                cyTvPayWay.setText("银联");
                                break;
                        }
                        //用户信用评价: -1|拉黑 0|未设置 1|一般 2|信任
                        switch (trainDetailModel.getRank()) {
                            case -1:
                                jdTvRenzheng.setText("拉黑");
                                break;
                            case 0:
                                jdTvRenzheng.setText("未评价");
                                break;
                            case 1:
                                jdTvRenzheng.setText("一般");
                                break;
                            case 2:
                                jdTvRenzheng.setText("信任");
                                break;
                        }
                    }

                    @Override
                    protected void onHandleError(String msg) {

                    }
                });


    }

    private void addTrainDetailLayout(TrainDetailModel trainDetailModel) {
     TrainDetailModel.TicketBean bean= trainDetailModel.getTicketBean();
        View view=LayoutInflater.from(this).inflate(R.layout.item_train_detail,null);
        ((TextView) view.findViewById(R.id.train_checi)).setText("车次："+bean.getDate());
        ((TextView) view.findViewById(R.id.train_chufa)).setText("出发时间："+bean.getTime_start());
        ((TextView) view.findViewById(R.id.train_daoda)).setText("到达时间："+bean.getTime_end());
        ((TextView) view.findViewById(R.id.train_qidian)).setText("起点："+bean.getStart_station());
        ((TextView) view.findViewById(R.id.train_zhongdian)).setText("终点："+bean.getEnd_station());
        ((TextView) view.findViewById(R.id.train_shichang)).setText("时长："+bean.getTime_spend());
        ((TextView) view.findViewById(R.id.train_shuliang)).setText("数量 ："+bean.getNums()+"张");
        jdLlDetail.addView(view);

    }

    private void getShowJingdian() {
        jdLlDaoTime.setVisibility(View.VISIBLE);
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .showOrderDetail(order_id, PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<ShowDetailModel>>compose())
                .subscribe(new BaseObserver<ShowDetailModel>() {
                    @Override
                    protected void onHandleSuccess(ShowDetailModel showDetailModel, int code, String msg) {
                        showTestToast(msg);
                        addShowDetailLayout(showDetailModel);
                        GlideUtil.getInstance().loadCircleImage(ActivityJingdianDetail.this, jingdianCivHead, PublicStaticData.URL + showDetailModel.getAvatar());
                        if (showDetailModel.getGroup_ticket().size() > 0)
                            jdTvType.setText("团票");
                        else
                            jdTvType.setText("普通票");
                        jdTvName.setText(showDetailModel.getUser_name());
                        jdTvDianhua.setText(showDetailModel.getMobile());
                        jdTvYudingren.setText(showDetailModel.getUser_name());
                        jdTvQupiaoren.setText(showDetailModel.getUser_name());
                        jdTvXdTime.setText(TimeUtils.millis2String((long) showDetailModel.getAdd_time() * 1000));
                        jdTvDaoTime.setText(TimeUtils.millis2String((long) showDetailModel.getBook_time() * 1000));
                        jdTvPrice.setText("总计" + showDetailModel.getPrice() + "元");
                        order_state = showDetailModel.getOrd_status();

                        //  订单状态:-2|用户取消 -1|商家取消 0|待接单 1|待付款 2|已付款 3|已消费 4|已评价
                        switch (showDetailModel.getOrd_status()) {
                            case -4:
                                jdTvState.setText("已退款");
                                rl.setVisibility(View.GONE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.GONE);
                                break;
                            case -3:
                                jdTvState.setText("待退款");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedan.setText("确认退款");
                                break;
                            case -2:
                                jdTvState.setText("用户取消");
                                rl.setVisibility(View.GONE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.GONE);
                                break;
                            case -1:
                                jdTvState.setText("商家取消");
                                rl.setVisibility(View.GONE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.GONE);
                                break;
                            case 0:
                                jdTvState.setText("待接单");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.VISIBLE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedanNo.setText("不接单");
                                jdJiedan.setText("接单");
                                break;
                            case 1:
                                jdTvState.setText("待付款");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                jdTvState.setText("已付款");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedan.setText("确认收款");

                                break;
                            case 3:
                                jdTvState.setText("已消费");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedan.setText("评价");

                                break;
                            case 4:
                                jdTvState.setText("已评价");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                break;
                        }
                        //支付方式: 1|到店支付 2|支付宝 3|微信 4|银联
                        switch (showDetailModel.getPay_type()) {
                            case 1:
                                cyTvPayWay.setText("到店支付");
                                break;
                            case 2:
                                cyTvPayWay.setText("支付宝");
                                break;
                            case 3:
                                cyTvPayWay.setText("微信");
                                break;
                            case 4:
                                cyTvPayWay.setText("银联");
                                break;
                        }
                        //用户信用评价: -1|拉黑 0|未设置 1|一般 2|信任
                        switch (showDetailModel.getRank()) {
                            case -1:
                                jdTvRenzheng.setText("拉黑");
                                break;
                            case 0:
                                jdTvRenzheng.setText("未评价");
                                break;
                            case 1:
                                jdTvRenzheng.setText("一般");
                                break;
                            case 2:
                                jdTvRenzheng.setText("信任");
                                break;
                        }

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showTestToast(msg);
                    }
                });
    }

    private void addShowDetailLayout(ShowDetailModel showDetailModel) {
        for (int i = 0; i < showDetailModel.getGroup_ticket().size(); i++) {
            String left = showDetailModel.getGroup_ticket().get(i).getTicket_name();
            View view = LayoutInflater.from(ActivityJingdianDetail.this).inflate(R.layout.item_jingdian_ticket, null);
            ((TextView) view.findViewById(R.id.ticket_left)).setText(left);
            jdLlDetail.addView(view);
            List<ShowDetailModel.GroupTicketBean.TicketsBean> list = showDetailModel.getGroup_ticket().get(i).getTickets();
            for (int m = 0; m < list.size(); m++) {

                String left1 = showDetailModel.getGroup_ticket().get(i).getTickets().get(m).getTicket_type() + ":" + showDetailModel.getGroup_ticket().get(i).getTickets().get(m).getPrice() + "元/人";
                String right1 = "X" + showDetailModel.getGroup_ticket().get(i).getTickets().get(m).getNums() + "人";
                View view1 = LayoutInflater.from(ActivityJingdianDetail.this).inflate(R.layout.item_jingdian_ticket, null);
                ((TextView) view1.findViewById(R.id.ticket_left)).setText(left1);
                ((TextView) view1.findViewById(R.id.ticket_right)).setText(right1);
                jdLlDetail.addView(view1);
            }


        }
        for (int i = 0; i < showDetailModel.getNormal_ticket().size(); i++) {
            String left = showDetailModel.getNormal_ticket().get(i).getTicket_name();
            View view = LayoutInflater.from(ActivityJingdianDetail.this).inflate(R.layout.item_jingdian_ticket, null);
            ((TextView) view.findViewById(R.id.ticket_left)).setText(left);
            jdLlDetail.addView(view);
            for (int x = 0; x < showDetailModel.getNormal_ticket().get(i).getTickets().size(); x++) {
                String left1 = showDetailModel.getNormal_ticket().get(i).getTickets().get(x).getTicket_type() + ":" + showDetailModel.getNormal_ticket().get(i).getTickets().get(x).getPrice() + "元/人";
                String right1 = "X" + showDetailModel.getNormal_ticket().get(i).getTickets().get(x).getNums() + "人";
                View view1 = LayoutInflater.from(ActivityJingdianDetail.this).inflate(R.layout.item_jingdian_ticket, null);
                ((TextView) view1.findViewById(R.id.ticket_left)).setText(left1);
                ((TextView) view1.findViewById(R.id.ticket_right)).setText(right1);
                jdLlDetail.addView(view1);
            }


        }

    }

    private void getJingdian() {
        jdLlDaoTime.setVisibility(View.GONE);
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .normalOrderDetail(order_id, PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<NormalDetailModel>>compose())
                .subscribe(new BaseObserver<NormalDetailModel>() {
                    @Override
                    protected void onHandleSuccess(NormalDetailModel normalDetailModel, int code, String msg) {
                        showTestToast(msg);
                        addDetailLayout(normalDetailModel);
                        if (normalDetailModel.getGroup_ticket().size() > 0)
                            jdTvType.setText("团票");
                        else
                            jdTvType.setText("普通票");
                        GlideUtil.getInstance().loadCircleImage(ActivityJingdianDetail.this, jingdianCivHead, PublicStaticData.URL + normalDetailModel.getAvatar());
                        jdTvName.setText(normalDetailModel.getUser_name());
                        jdTvDianhua.setText(normalDetailModel.getMobile());
                        jdTvYudingren.setText(normalDetailModel.getUser_name());
                        jdTvQupiaoren.setText(normalDetailModel.getUser_name());
                        jdTvXdTime.setText(TimeUtils.millis2String((long) normalDetailModel.getAdd_time() * 1000));
                        jdTvPrice.setText("总计" + normalDetailModel.getPrice() + "元");
                        order_state = normalDetailModel.getOrd_status();

                        //  订单状态:-2|用户取消 -1|商家取消 0|待接单 1|待付款 2|已付款 3|已消费 4|已评价
                        switch (normalDetailModel.getOrd_status()) {
                            case -4:
                                jdTvState.setText("已退款");
                                rl.setVisibility(View.GONE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.GONE);
                                break;
                            case -3:
                                jdTvState.setText("待退款");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedan.setText("确认退款");
                                break;
                            case -2:
                                jdTvState.setText("用户取消");
                                rl.setVisibility(View.GONE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.GONE);
                                break;
                            case -1:
                                jdTvState.setText("商家取消");
                                rl.setVisibility(View.GONE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.GONE);
                                break;
                            case 0:
                                jdTvState.setText("待接单");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.VISIBLE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedanNo.setText("不接单");
                                jdJiedan.setText("接单");
                                break;
                            case 1:
                                jdTvState.setText("待付款");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                jdTvState.setText("已付款");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedan.setText("确认收款");

                                break;
                            case 3:
                                jdTvState.setText("已消费");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.VISIBLE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                jdJiedan.setText("评价");

                                break;
                            case 4:
                                jdTvState.setText("已评价");
                                rl.setVisibility(View.VISIBLE);
                                jdJiedan.setVisibility(View.GONE);
                                jdJiedanNo.setVisibility(View.GONE);
                                jdLianxi.setVisibility(View.VISIBLE);
                                break;
                        }
                        //支付方式: 1|到店支付 2|支付宝 3|微信 4|银联
                        switch (normalDetailModel.getPay_type()) {
                            case 1:
                                cyTvPayWay.setText("到店支付");
                                break;
                            case 2:
                                cyTvPayWay.setText("支付宝");
                                break;
                            case 3:
                                cyTvPayWay.setText("微信");
                                break;
                            case 4:
                                cyTvPayWay.setText("银联");
                                break;
                        }
                        //用户信用评价: -1|拉黑 0|未设置 1|一般 2|信任
                        switch (normalDetailModel.getRank()) {
                            case -1:
                                jdTvRenzheng.setText("拉黑");
                                break;
                            case 0:
                                jdTvRenzheng.setText("未评价");
                                break;
                            case 1:
                                jdTvRenzheng.setText("一般");
                                break;
                            case 2:
                                jdTvRenzheng.setText("信任");
                                break;
                        }
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showTestToast(msg);
                    }
                });
    }

    /**
     * 普通景点详情
     *
     * @param normalDetailModel
     */
    private void addDetailLayout(NormalDetailModel normalDetailModel) {
        for (int i = 0; i < normalDetailModel.getGroup_ticket().size(); i++) {
            String left = normalDetailModel.getGroup_ticket().get(i).getTicket_type() + ":" + normalDetailModel.getGroup_ticket().get(i).getPrice() + "元/人";
            String right = "X" + normalDetailModel.getGroup_ticket().get(i).getNums() + "人";
            View view = LayoutInflater.from(ActivityJingdianDetail.this).inflate(R.layout.item_jingdian_ticket, null);
            ((TextView) view.findViewById(R.id.ticket_left)).setText(left);
            ((TextView) view.findViewById(R.id.ticket_right)).setText(right);
            jdLlDetail.addView(view);
        }
        for (int i = 0; i < normalDetailModel.getNormal_ticket().size(); i++) {
            String left = normalDetailModel.getNormal_ticket().get(i).getTicket_type() + ":" + normalDetailModel.getNormal_ticket().get(i).getPrice() + "元                      /人";
            String right = "X" + normalDetailModel.getNormal_ticket().get(i).getNums() + "人";
            View view = LayoutInflater.from(ActivityJingdianDetail.this).inflate(R.layout.item_jingdian_ticket, null);
            ((TextView) view.findViewById(R.id.ticket_left)).setText(left);
            ((TextView) view.findViewById(R.id.ticket_right)).setText(right);
            jdLlDetail.addView(view);
        }
    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.jd_lianxi, R.id.jd_jiedan_no, R.id.jd_jiedan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.jd_lianxi:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + jdTvDianhua.getText().toString());
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.jd_jiedan_no:
                Bundle bundle = new Bundle();
                bundle.putString("order_id", order_id);
                ActivityFactory.goQxYuanyin(this, bundle);
                break;
            case R.id.jd_jiedan:
                if (order_state == 0) {
                    DingdanDialog dialog = new DingdanDialog(this, "您确定要接单么？") {
                        @Override
                        public void call(boolean b) {
                            if (b) {
                                acceptOrder();
                            }

                        }
                    };
                    dialog.show();
                } else if (order_state == 2) {
                    DingdanDialog dialog = new DingdanDialog(this, "您确定已收款么？") {
                        @Override
                        public void call(boolean b) {
                            if (b) {
                                confirmOrder();
                            }

                        }
                    };
                    dialog.show();
                } else if (order_state == 3) {
                    Bundle b = new Bundle();
                    b.putString("order_id", order_id);
                    ActivityFactory.goPingjia(this, b);
                }else if (order_state==-3){
                    DingdanDialog dialog = new DingdanDialog(this, "您确定已退款么？") {
                        @Override
                        public void call(boolean b) {
                            if (b) {
                                refund();
                            }

                        }
                    };
                    dialog.show();
                }
                break;
        }
    }

    /**
     * 确定tui款
     */
    private void refund() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .refund(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        order_id + "")
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        jdTvState.setText("已退款");
                        rl.setVisibility(View.GONE);
                        jdJiedan.setVisibility(View.GONE);
                        jdJiedanNo.setVisibility(View.GONE);
                        jdLianxi.setVisibility(View.GONE);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                    }
                });
    }

    /**
     * 确定收款
     */
    private void confirmOrder() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .confirmOrder(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        order_id + "")
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        jdTvState.setText("已消费");
                        rl.setVisibility(View.VISIBLE);
                        jdJiedan.setVisibility(View.VISIBLE);
                        jdJiedanNo.setVisibility(View.GONE);
                        jdLianxi.setVisibility(View.VISIBLE);
                        jdJiedan.setText("评价");
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                    }
                });
    }

    /**
     * 接受订单
     */
    private void acceptOrder() {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .accept(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        order_id + "")
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        jdTvState.setText("待付款");
                        rl.setVisibility(View.VISIBLE);
                        jdJiedan.setVisibility(View.GONE);
                        jdJiedanNo.setVisibility(View.GONE);
                        jdLianxi.setVisibility(View.VISIBLE);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.showDebug(msg);
                    }
                });
    }
}
