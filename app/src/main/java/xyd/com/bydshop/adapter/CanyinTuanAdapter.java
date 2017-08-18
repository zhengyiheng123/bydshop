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

public class CanyinTuanAdapter extends RecyclerView.Adapter<CanyinTuanAdapter.ViewHolder> {
    private Context context;
    private List<CanyinMealModel.GroupMealBean> list;

    public CanyinTuanAdapter(List<CanyinMealModel.GroupMealBean> list, Context mContext) {
        this.context = mContext;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_cy_meal_tuan, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CanyinMealModel.GroupMealBean bean = list.get(position);
        holder.title.setText(bean.getMeal_name());
        GlideUtil.getInstance().loadImage(context, holder.imageView, PublicStaticData.URL + bean.getImg_path(), true);
        for (int i = 0; i < bean.getMeal_price().size(); i++) {
            holder.price[i].setText(bean.getMeal_price().get(i) + "元/人");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView title;
        private final ImageView imageView;

        private final TextView[] price = new TextView[6];

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            title = (TextView) itemView.findViewById(R.id.item_cy_tuan_title);
            imageView = (ImageView) itemView.findViewById(R.id.item_cy_tuan_iv);
            price[0] = (TextView) itemView.findViewById(R.id.item_cy_tuan_price1);
            price[1] = (TextView) itemView.findViewById(R.id.item_cy_tuan_price2);
            price[2] = (TextView) itemView.findViewById(R.id.item_cy_tuan_price3);
            price[3] = (TextView) itemView.findViewById(R.id.item_cy_tuan_price4);
            price[4] = (TextView) itemView.findViewById(R.id.item_cy_tuan_price5);
            price[5] = (TextView) itemView.findViewById(R.id.item_cy_tuan_price6);


        }
    }
}
