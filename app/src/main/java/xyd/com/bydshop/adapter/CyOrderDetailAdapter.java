package xyd.com.bydshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import xyd.com.bydshop.R;
import xyd.com.bydshop.entity.OrderDetailModel;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/3
 * @time: 10:02
 * @description:  餐饮详情订单，订单信息详情
 */

public class CyOrderDetailAdapter extends BaseAdapter {

    private Context context;
    private OrderDetailModel model;

    public CyOrderDetailAdapter (Context context, OrderDetailModel model){
        this.context=context;
        this.model=model;
    }

    @Override
    public int getCount() {
        return model.getSingle_meal().size()==0? 1:model.getSingle_meal().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_cy_order_detail,null,false);
            holder.imageView= (ImageView) convertView.findViewById(R.id.item_cy_order_iv);
            holder.title= (TextView) convertView.findViewById(R.id.item_cy_order_tv_title);
            holder.nums= (TextView) convertView.findViewById(R.id.item_cy_order_tv_num);
            holder.weight= (TextView) convertView.findViewById(R.id.item_cy_order_tv_ke);
            holder.price= (TextView) convertView.findViewById(R.id.item_cy_order_tv_price);
            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if (model.getSingle_meal().size()>0){
            GlideUtil.getInstance().loadImage(context,holder.imageView, PublicStaticData.URL+model.getSingle_meal().get(position).getImg_path(),false);
            holder.weight.setText(model.getSingle_meal().get(position).getMeal_weight()+"克");
            holder.title.setText(model.getSingle_meal().get(position).getMeal_name());
            holder.price.setText("单价："+model.getSingle_meal().get(position).getMeal_price()+"元");
            holder.nums.setText("数量："+model.getSingle_meal().get(position).getNums());

        }else {
            GlideUtil.getInstance().loadImage(context,holder.imageView, PublicStaticData.URL+model.getGroup_meal().getImg_path(),false);
            holder.weight.setText("");
            holder.title.setText(model.getGroup_meal().getMeal_name());
            holder.price.setText("单价："+model.getGroup_meal().getMeal_price()+"元");
            holder.nums.setText("数量："+model.getGroup_meal().getNums());

        }

        return convertView;
    }
    private static class ViewHolder{
        public ImageView  imageView;
        public TextView title;
        public TextView weight;
        public TextView price;
        public TextView nums;
    }
}
