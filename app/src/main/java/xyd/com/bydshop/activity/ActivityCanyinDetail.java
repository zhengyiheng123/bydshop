package xyd.com.bydshop.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.adapter.CyOrderDetailAdapter;
import xyd.com.bydshop.customview.CircleImageView;
import xyd.com.bydshop.customview.DingdanDialog;
import xyd.com.bydshop.customview.MaxListView;
import xyd.com.bydshop.entity.CancelConditionModel;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.entity.OrderDetailModel;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.serviceapi.OrderApi;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.LogUtil;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.TimeUtils;
import xyd.com.bydshop.utils.ToastUtils;

/**
 * Created by ${zxl} on 2017/4/7.
 * Describe:  餐饮订单详情
 * CHange:
 */

public class ActivityCanyinDetail extends BaseActivity {


    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.jingdian_tv_lianxi)
    TextView jingdianTvLianxi;
    @Bind(R.id.jingdian_tv_jiedan_no)
    TextView jingdianTvJiedanNo;
    @Bind(R.id.jingdian_tv_jiedan)
    TextView jingdianTvJiedan;
    @Bind(R.id.item_canyin_head)
    ImageView itemCanyinHead;
    @Bind(R.id.cy_tv_name)
    TextView cyTvName;
    @Bind(R.id.cy_tv_renzheng)
    TextView cyTvRenzheng;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.cy_tv_phone)
    TextView cyTvPhone;
    @Bind(R.id.cy_tv_detail)
    TextView cyTvDetail;
    @Bind(R.id.cy_tv_tuanhao)
    TextView cyTvTuanhao;
    @Bind(R.id.cy_tv_renshu)
    TextView cyTvRenshu;
    @Bind(R.id.cy_edt_liuyan)
    EditText cyEdtLiuyan;
    @Bind(R.id.cy_tv_dingdanhao)
    TextView cyTvDingdanhao;
    @Bind(R.id.cy_tv_xd_time)
    TextView cyTvXdTime;
    @Bind(R.id.cy_tv_dao_time)
    TextView cyTvDaoTime;
    @Bind(R.id.cy_tv_state)
    TextView cyTvState;
    @Bind(R.id.cy_tv_pay_way)
    TextView cyTvPayWay;
    @Bind(R.id.cy_tv_price)
    TextView cyTvPrice;
    @Bind(R.id.cy_tv_pay_qx)
    TextView cyTvPayQx;
    @Bind(R.id.cy_lv_detail)
    MaxListView cyLvDetail;
    @Bind(R.id.rl)
    LinearLayout bouttomLl;
    private int order_state;
    private String order_id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_canyin_detail;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("详情");
        order_id = getIntent().getStringExtra("order_id");
        LogUtil.e("order_id:"+order_id);
        getOrderDetail();
        getData();

    }

    /**
     * 获取取消条件
     */
    private void getData() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .cancel_condition(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<CancelConditionModel>>compose())
                .subscribe(new BaseObserver<CancelConditionModel>() {
                    @Override
                    protected void onHandleSuccess(CancelConditionModel s, int code, String msg) {
                        if (s.getCancel_condition().equals("-1")) {
                            cyTvPayQx.setText("暂未设置取消条件");
                        } else if (s.getCancel_condition().equals("0")) {
                            cyTvPayQx.setText("订单不可取消");

                        } else {
                            cyTvPayQx.setText(s.getCancel_condition());
                        }
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }
    /**
     * 获取订单详情
     */
    private void getOrderDetail() {
        BaseApi.getRetrofit()
                .create(OrderApi.class)
                .orderDetail(order_id, PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<OrderDetailModel>>compose())
                .subscribe(new BaseObserver<OrderDetailModel>() {
                    @Override
                    protected void onHandleSuccess(OrderDetailModel orderDetailModel, int code, String msg) {
                        CyOrderDetailAdapter adapter=new CyOrderDetailAdapter(ActivityCanyinDetail.this,orderDetailModel);
                        cyLvDetail.setAdapter(adapter);
                        GlideUtil.getInstance().loadCircleImage(ActivityCanyinDetail.this, itemCanyinHead, PublicStaticData.URL + orderDetailModel.getAvatar());
                        cyTvName.setText(orderDetailModel.getUser_name());
                        cyTvPhone.setText(orderDetailModel.getMobile());
                        cyTvTuanhao.setText(orderDetailModel.getGroup_num());
                        cyTvDingdanhao.setText(orderDetailModel.getOrd_num());
                        cyTvRenshu.setText(orderDetailModel.getSeat_cost() + "人");
                        cyTvXdTime.setText(TimeUtils.millis2String((long) orderDetailModel.getAdd_time() * 1000));
                        cyTvDaoTime.setText(TimeUtils.millis2String((long) orderDetailModel.getBook_time() * 1000));
                        cyTvPrice.setText("总计" + orderDetailModel.getPrice() + "元");
                        cyEdtLiuyan.setText(orderDetailModel.getMessage());
                        order_state = orderDetailModel.getOrd_status();

                        //  订单状态:-2|用户取消 -1|商家取消 0|待接单 1|待付款 2|已付款 3|已消费 4|已评价 -4已退款 -3确认退款
                        switch (orderDetailModel.getOrd_status()) {
                            case -4:
                                cyTvState.setText("已退款");
                                bouttomLl.setVisibility(View.GONE);
                                jingdianTvJiedan.setVisibility(View.GONE);
                                jingdianTvJiedanNo.setVisibility(View.GONE);
                                jingdianTvLianxi.setVisibility(View.GONE);
                                break;
                            case -3:
                                cyTvState.setText("待退款");
                                bouttomLl.setVisibility(View.VISIBLE);
                                jingdianTvJiedan.setVisibility(View.VISIBLE);
                                jingdianTvJiedanNo.setVisibility(View.GONE);
                                jingdianTvLianxi.setVisibility(View.VISIBLE);
                                jingdianTvJiedan.setText("确认退款");
                                break;
                            case -2:
                                cyTvState.setText("用户取消");
                                bouttomLl.setVisibility(View.GONE);
                                jingdianTvJiedan.setVisibility(View.GONE);
                                jingdianTvJiedanNo.setVisibility(View.GONE);
                                jingdianTvLianxi.setVisibility(View.GONE);
                                break;
                            case -1:
                                cyTvState.setText("商家取消");
                                bouttomLl.setVisibility(View.GONE);
                                jingdianTvJiedan.setVisibility(View.GONE);
                                jingdianTvJiedanNo.setVisibility(View.GONE);
                                jingdianTvLianxi.setVisibility(View.GONE);
                                break;
                            case 0:
                                cyTvState.setText("待接单");
                                bouttomLl.setVisibility(View.VISIBLE);
                                jingdianTvJiedan.setVisibility(View.VISIBLE);
                                jingdianTvJiedanNo.setVisibility(View.VISIBLE);
                                jingdianTvLianxi.setVisibility(View.VISIBLE);
                                jingdianTvJiedanNo.setText("不接单");
                                jingdianTvJiedan.setText("接单");
                                break;
                            case 1:
                                cyTvState.setText("待付款");
                                bouttomLl.setVisibility(View.VISIBLE);
                                jingdianTvJiedan.setVisibility(View.GONE);
                                jingdianTvJiedanNo.setVisibility(View.GONE);
                                jingdianTvLianxi.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                cyTvState.setText("已付款");
                                bouttomLl.setVisibility(View.VISIBLE);
                                jingdianTvJiedan.setVisibility(View.VISIBLE);
                                jingdianTvJiedanNo.setVisibility(View.GONE);
                                jingdianTvLianxi.setVisibility(View.VISIBLE);
                                jingdianTvJiedan.setText("确认收款");

                                break;
                            case 3:
                                cyTvState.setText("已消费");
                                bouttomLl.setVisibility(View.VISIBLE);
                                jingdianTvJiedan.setVisibility(View.VISIBLE);
                                jingdianTvJiedanNo.setVisibility(View.GONE);
                                jingdianTvLianxi.setVisibility(View.VISIBLE);
                                jingdianTvJiedan.setText("评价");

                                break;
                            case 4:
                                cyTvState.setText("已评价");
                                bouttomLl.setVisibility(View.VISIBLE);
                                jingdianTvJiedan.setVisibility(View.GONE);
                                jingdianTvJiedanNo.setVisibility(View.GONE);
                                jingdianTvLianxi.setVisibility(View.VISIBLE);
                                break;
                        }
                        //支付方式: 1|到店支付 2|支付宝 3|微信 4|银联
                        switch (orderDetailModel.getPay_type()) {
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
                        switch (orderDetailModel.getRank()) {
                            case -1:
                                cyTvRenzheng.setText("拉黑");
                                break;
                            case 0:
                                cyTvRenzheng.setText("未评价");
                                break;
                            case 1:
                                cyTvRenzheng.setText("一般");
                                break;
                            case 2:
                                cyTvRenzheng.setText("信任");
                                break;
                        }

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }


    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.jingdian_tv_jiedan, R.id.jingdian_tv_jiedan_no, R.id.jingdian_tv_lianxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.jingdian_tv_jiedan:
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
                                confirmOrder();
                            }

                        }
                    };
                    dialog.show();
                }

                break;
            case R.id.jingdian_tv_jiedan_no:
                Bundle bundle = new Bundle();
                bundle.putString("order_id", order_id);
                ActivityFactory.goQxYuanyin(this, bundle);

                break;
            case R.id.jingdian_tv_lianxi:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + cyTvPhone.getText().toString());
                intent.setData(data);
                startActivity(intent);
                break;
        }
    }
    /**
     * 确定退款
     */
    private void refund() {
        BaseApi.getRetrofit()
                .create(OrderApi.class)
                .refund(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        order_id)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        order_state = -4;
                        cyTvState.setText("已退款");
                        bouttomLl.setVisibility(View.GONE);
                        jingdianTvJiedan.setVisibility(View.GONE);
                        jingdianTvJiedanNo.setVisibility(View.GONE);
                        jingdianTvLianxi.setVisibility(View.GONE);
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
                .create(OrderApi.class)
                .confirmOrder(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        order_id)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        order_state = 3;
                        cyTvState.setText("已消费");
                        jingdianTvJiedan.setVisibility(View.VISIBLE);
                        jingdianTvJiedanNo.setVisibility(View.GONE);
                        jingdianTvLianxi.setVisibility(View.VISIBLE);
                        jingdianTvJiedan.setText("评价");
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
                .create(OrderApi.class)
                .acceptOrder(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        order_id)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        order_state = 1;
                        cyTvState.setText("待付款");
                        jingdianTvJiedan.setVisibility(View.GONE);
                        jingdianTvJiedanNo.setVisibility(View.GONE);
                        jingdianTvLianxi.setVisibility(View.VISIBLE);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.showDebug(msg);
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getOrderDetail();
    }
}
