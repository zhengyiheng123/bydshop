package xyd.com.bydshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import xyd.com.bydshop.R;
import xyd.com.bydshop.entity.CanyinMealModel;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/10.
 * D:  餐饮——商品团餐
 * C:
 */

public class CanyinDanAdapter extends RecyclerView.Adapter<CanyinDanAdapter.ViewHolder> {
    private Context context;
    private List<CanyinMealModel.SingleMealBean> list;

    public CanyinDanAdapter(List<CanyinMealModel.SingleMealBean> list, Context mContext) {
        this.context = mContext;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_cy_single, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CanyinMealModel.SingleMealBean bean = list.get(position);
        holder.title.setText(bean.getMeal_name());
        GlideUtil.getInstance().loadImage(context, holder.imageView, PublicStaticData.URL + bean.getImg_path(), true);
        holder.kezhong.setText(bean.getMeal_weight()+"克");
        holder.nowPrice.setText(bean.getMeal_price()+"元/人");
        holder.oldPrice.setText(bean.getMeal_price()+"元/人");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView title;
        private final TextView kezhong;
        private final TextView nowPrice;
        private final TextView oldPrice;
        private final ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            title = (TextView) itemView.findViewById(R.id.item_cy_single_tv_title);
            kezhong = (TextView) itemView.findViewById(R.id.item_cy_single_tv_ke);
            nowPrice = (TextView) itemView.findViewById(R.id.item_cy_single_tv_now);
            oldPrice = (TextView) itemView.findViewById(R.id.item_cy_single_tv_old);
            imageView = (ImageView) itemView.findViewById(R.id.item_cy_single_iv);



        }
    }
}
