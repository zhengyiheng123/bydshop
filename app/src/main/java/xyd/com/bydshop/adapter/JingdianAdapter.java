package xyd.com.bydshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import xyd.com.bydshop.R;
import xyd.com.bydshop.entity.CanyinMealModel;
import xyd.com.bydshop.entity.RoutesModel;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.TimeUtils;

/**
 * Created by ${zxl} on 2017/4/10.
 * D:  景点商品列表
 * C:
 */

public class JingdianAdapter extends RecyclerView.Adapter<JingdianAdapter.ViewHolder> {
    private Context context;
    private List<RoutesModel.RoutesBean> list;
    private String sub_cate_id;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl="";

    public JingdianAdapter(List<RoutesModel.RoutesBean> list, Context mContext) {
        this.context = mContext;
        this.list = list;
        sub_cate_id=PublicStaticData.sharedPreferences.getString(PublicStaticData.SUB_CATE_ID, "");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_jd_meal, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RoutesModel.RoutesBean bean = list.get(position);
        //子级分类id 5|普通景区 6|演出类 7|火车游船
        GlideUtil.getInstance().loadImage(context,holder.imageView,PublicStaticData.URL+imageUrl,true);
        holder.title.setText(bean.getRoute_name() + "(团票" + bean.getGroup_start() + "元起)");

        if (bean.getStatus() == 0) {
            holder.status.setText("已下架");
        } else {
            holder.status.setText("已上架");
        }

        if (bean.getDriver_free() == 0) {
            holder.driver.setText("司机免费：否");
        } else {
            holder.driver.setText("司机免费：是");
        }
        if (bean.getGuide_free() == 0) {
            holder.guide.setText("导游免费：否");
        } else {
            holder.guide.setText("导游免费：是");
        }
        if (sub_cate_id.equals("5")) {
            holder.timeLl.setVisibility(View.VISIBLE);

        } else if (sub_cate_id.equals("6")) {
            holder.timeLl.setVisibility(View.VISIBLE);
            holder.start.setText("开始："+TimeUtils.millis2String(bean.getStart_time()," yyyy-MM-dd HH:mm"));
            holder.end.setText("结束："+ TimeUtils.millis2String(bean.getEnd_time()," yyyy-MM-dd HH:mm"));

        } else if (sub_cate_id.equals("7")) {

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView title;
        private final TextView status;
        private final TextView start;
        private final TextView end;
        private final TextView driver;
        private final TextView guide;
        private final LinearLayout timeLl;
        private final ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            title = (TextView) itemView.findViewById(R.id.item_jd_title);
            imageView = (ImageView) itemView.findViewById(R.id.item_jd_iv);
            status = (TextView) itemView.findViewById(R.id.item_jd_status);
            start = (TextView) itemView.findViewById(R.id.item_jd_start_time);
            end = (TextView) itemView.findViewById(R.id.item_jd_end_time);
            driver = (TextView) itemView.findViewById(R.id.item_jd_driver);
            guide = (TextView) itemView.findViewById(R.id.item_jd_guide);
            timeLl = (LinearLayout) itemView.findViewById(R.id.item_jd_time);

        }
    }
}
