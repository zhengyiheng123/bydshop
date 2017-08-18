package xyd.com.bydshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.customview.DingdanDialog;
import xyd.com.bydshop.customview.XianlianxiDialog;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.entity.NormalSceneModel;
import xyd.com.bydshop.entity.OrderModel;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.runtimepermissions.PermissionUtils;
import xyd.com.bydshop.serviceapi.JingdianOrderApi;
import xyd.com.bydshop.serviceapi.OrderApi;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.TimeUtils;
import xyd.com.bydshop.utils.ToastUtils;

/**
 * Created by ${zxl} on 2017/4/14.
 * D: 订单
 * C:
 */

public class JindianDingdanAdapter extends RecyclerView.Adapter<JindianDingdanAdapter.ViewHolder> {

    private List<NormalSceneModel.OrdersBean> list;
    private Context context;
    //子级分类id 5|普通景区 6|演出类 7|火车游船
    private String sub_cate_id;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public JindianDingdanAdapter(Context context, List<NormalSceneModel.OrdersBean> list, String sub_cate_id) {
        this.context = context;
        this.list = list;
        this.sub_cate_id = sub_cate_id;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dingdan, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GlideUtil.getInstance().loadCircleImage(context, holder.itemDingdanIvTouxiang, PublicStaticData.URL + list.get(position).getAvatar());
        holder.itemDingdanTvName.setText(list.get(position).getUser_name());
        holder.itemDingdanTvNeirong.setText("订单号：" + list.get(position).getOrd_num());
        holder.itemDingdanTvRenshu.setText("线路名称：" + list.get(position).getRoute_name());
        holder.itemDingdanTvZongji.setText("总计：" + list.get(position).getPrice() + "元");
        if (sub_cate_id.equals("5")) {
            holder.time.setText("下单时间：");
            holder.itemDingdanTvTime.setText(TimeUtils.millis2String(list.get(position).getAdd_time() * 1000));
        } else if (sub_cate_id.equals("6")) {
            holder.time.setText("预约时间：");
            holder.itemDingdanTvTime.setText(TimeUtils.millis2String(list.get(position).getBook_time() * 1000));
        }else if (sub_cate_id.equals("7")) {
            holder.time.setText("预约时间：");
            holder.itemDingdanTvTime.setText(TimeUtils.millis2String(list.get(position).getBook_time() * 1000));
        }


        //支付方式: 1|到店支付 2|支付宝 3|微信 4|银联
        switch (list.get(position).getPay_type()) {
            case 1:
                holder.itemDingdanTvZhifu.setText("到店支付");
                break;
            case 2:
                holder.itemDingdanTvZhifu.setText("支付宝");
                break;
            case 3:
                holder.itemDingdanTvZhifu.setText("微信");
                break;
            case 4:
                holder.itemDingdanTvZhifu.setText("银联");
                break;
        }
        //用户信用评价: -1|拉黑 0|未设置 1|一般 2|信任
        switch (list.get(position).getRank()) {
            case -1:
                holder.itemDingdanTvXinren.setText("拉黑");
                break;
            case 0:
                holder.itemDingdanTvXinren.setText("未评价");
                break;
            case 1:
                holder.itemDingdanTvXinren.setText("一般");
                break;
            case 2:
                holder.itemDingdanTvXinren.setText("信任");
                break;
        }

        switch (list.get(position).getOrd_status()) {
            case -4:
                holder.itemDingdanIv3.setText("已退款");
                holder.itemDingdanIv1.setVisibility(View.GONE);
                holder.itemDingdanIv2.setVisibility(View.GONE);
                break;
            case -3:
                holder.itemDingdanIv3.setText("确认退款");
                holder.itemDingdanIv1.setText("先联系");
                holder.itemDingdanIv1.setVisibility(View.VISIBLE);
                holder.itemDingdanIv2.setVisibility(View.GONE);
                break;
            case -2:
                holder.itemDingdanIv3.setText("已取消");
                holder.itemDingdanIv1.setVisibility(View.GONE);
                holder.itemDingdanIv2.setVisibility(View.GONE);

                break;
            case -1:
                holder.itemDingdanIv3.setText("已取消");
                holder.itemDingdanIv1.setVisibility(View.GONE);
                holder.itemDingdanIv2.setVisibility(View.GONE);


                break;
            case 0:
                holder.itemDingdanIv1.setText("先联系");
                holder.itemDingdanIv2.setText("拒绝");
                holder.itemDingdanIv3.setText("接单");
                holder.itemDingdanIv1.setVisibility(View.VISIBLE);
                holder.itemDingdanIv2.setVisibility(View.VISIBLE);
                break;
            case 1:

                holder.itemDingdanIv3.setText("待付款");
                holder.itemDingdanIv1.setVisibility(View.GONE);
                holder.itemDingdanIv2.setVisibility(View.GONE);
                break;
            case 2:
                if (list.get(position).getPay_type() == 1) {
                    holder.itemDingdanIv3.setText("待评价");
                    holder.itemDingdanIv1.setVisibility(View.GONE);
                    holder.itemDingdanIv2.setVisibility(View.GONE);
                } else {
                    holder.itemDingdanIv1.setText("先联系");
                    holder.itemDingdanIv3.setText("确认收费");
                    holder.itemDingdanIv1.setVisibility(View.VISIBLE);
                    holder.itemDingdanIv2.setVisibility(View.GONE);
                }

                break;
            case 3:
                holder.itemDingdanIv3.setText("待评价");
                holder.itemDingdanIv1.setVisibility(View.GONE);
                holder.itemDingdanIv2.setVisibility(View.GONE);
                break;
            case 4:
                holder.itemDingdanIv3.setText("已完成");
                holder.itemDingdanIv1.setVisibility(View.GONE);
                holder.itemDingdanIv2.setVisibility(View.GONE);
                break;
        }
        holder.dingdanLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  onItemClickListener.onItemClick(position);
                Bundle b = new Bundle();
                b.putString("order_id", list.get(position).getOrd_id() + "");
                ActivityFactory.goJingdianDetail(context, b);
            }
        });
        holder.itemDingdanIvTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("order_id", list.get(position).getOrd_id() + "");
                ActivityFactory.goPingjia(context, b);
            }
        });

        holder.itemDingdanIv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = list.get(position).getOrd_status();
                if (type == -2 || type == -1 || type == 4 || type == 1 || type == 3||type ==-4) {
                    Bundle b = new Bundle();
                    b.putString("order_id", list.get(position).getOrd_id() + "");
                    ActivityFactory.goJingdianDetail(context, b);
                } else if (type == 2) {
                    if (list.get(position).getPay_type() == 1) {
                    } else {
                        DingdanDialog dialog = new DingdanDialog(context, "您确定已收款么？") {
                            @Override
                            public void call(boolean b) {
                                if (b) {
                                    confirmOrder(position);
                                }

                            }
                        };
                        dialog.show();
                    }

                } else if (type == 0) {
                    DingdanDialog dialog = new DingdanDialog(context, "您确定要接单么？") {
                        @Override
                        public void call(boolean b) {
                            if (b) {
                                acceptOrder(position);
                            }

                        }
                    };
                    dialog.show();
                }
                else if (type==-3){
                    DingdanDialog dialog = new DingdanDialog(context, "您确定已退款么？") {
                        @Override
                        public void call(boolean b) {
                            if (b) {
                                refund(position);
                            }

                        }
                    };
                    dialog.show();
                }
            }
        });

        holder.itemDingdanIv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DingdanDialog dialog = new DingdanDialog(context, "您确定不接该订单么？") {
                    @Override
                    public void call(boolean b) {
                        if (b) {
                            Bundle bundle = new Bundle();
                            bundle.putString("order_id", list.get(position).getOrd_id() + "");
                            ActivityFactory.goQxYuanyin(context, bundle);
                        }
                    }
                };
                dialog.show();
            }
        });
        holder.itemDingdanIv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XianlianxiDialog dialog = new XianlianxiDialog(context) {
                    @Override
                    public void callBack(boolean phone) {
                        if (phone) {
                            PermissionUtils.phone((Activity) context, new PermissionUtils.OnPermissionResult() {
                                @Override
                                public void onGranted() {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    Uri data = Uri.parse("tel:" + list.get(position).getMobile());
                                    intent.setData(data);
                                    context.startActivity(intent);
                                    dismiss();
                                }
                            });
                        } else {
                            Bundle bundle=new Bundle();
                            bundle.putInt("id",list.get(position).getOrd_id());
                            ActivityFactory.goLiuyan(context, bundle);
                        }
                    }
                };
                dialog.show();
                dialog.setNumber(list.get(position).getMobile());
            }
        });
    }
    /**
     * 确定退款
     *
     * @param position
     */
    private void refund(final int position) {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .refund(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        list.get(position).getOrd_id() + "")
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        list.get(position).setOrd_status(-4);
                        notifyItemChanged(position + 1);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                    }
                });
    }
    /**
     * 确定收款
     *
     * @param position
     */
    private void confirmOrder(final int position) {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .confirmOrder(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        list.get(position).getOrd_id() + "")
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        list.get(position).setOrd_status(3);
                        notifyItemChanged(position + 1);
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
    private void acceptOrder(final int position) {
        BaseApi.getRetrofit()
                .create(JingdianOrderApi.class)
                .accept(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        list.get(position).getOrd_id() + "")
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, int code, String msg) {
                        list.get(position).setOrd_status(1);
                        notifyItemChanged(position + 1);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.showDebug(msg);
                    }
                });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView itemDingdanTvTime;
        private final TextView itemDingdanTvZhifu;
        private final TextView itemDingdanTvName;
        private final TextView itemDingdanTvNeirong;
        private final TextView itemDingdanTvRenshu;
        private final TextView itemDingdanTvXinren;
        private final TextView itemDingdanTvZongji;
        private final ImageView itemDingdanIvTouxiang;
        private final TextView itemDingdanIv1;
        private final TextView itemDingdanIv2;
        private final TextView itemDingdanIv3;
        private final LinearLayout dingdanLl;
        private final TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            itemDingdanTvTime = (TextView) itemView.findViewById(R.id.item_dingdan_tv_time);
            dingdanLl = (LinearLayout) itemView.findViewById(R.id.dingdan_ll);
            itemDingdanTvZhifu = (TextView) itemView.findViewById(R.id.item_dingdan_tv_zhifu);
            itemDingdanTvName = (TextView) itemView.findViewById(R.id.item_dingdan_tv_name);
            itemDingdanTvNeirong = (TextView) itemView.findViewById(R.id.item_dingdan_tv_neirong);
            itemDingdanTvRenshu = (TextView) itemView.findViewById(R.id.item_dingdan_tv_renshu);
            itemDingdanTvXinren = (TextView) itemView.findViewById(R.id.item_dingdan_tv_xinren);
            itemDingdanTvZongji = (TextView) itemView.findViewById(R.id.item_dingdan_tv_zongji);
            itemDingdanIvTouxiang = (ImageView) itemView.findViewById(R.id.item_dingdan_iv_touxiang);
            itemDingdanIv1 = (TextView) itemView.findViewById(R.id.item_dingdan_iv_1);
            itemDingdanIv2 = (TextView) itemView.findViewById(R.id.item_dingdan_iv_2);
            itemDingdanIv3 = (TextView) itemView.findViewById(R.id.item_dingdan_iv_3);
            time = (TextView) itemView.findViewById(R.id.item_jingdian_tv_time);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
