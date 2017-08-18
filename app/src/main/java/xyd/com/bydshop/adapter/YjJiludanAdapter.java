package xyd.com.bydshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.Bind;
import xyd.com.bydshop.R;
import xyd.com.bydshop.customview.CircleImageView;
import xyd.com.bydshop.customview.MaxGridView;
import xyd.com.bydshop.customview.MaxListView;
import xyd.com.bydshop.entity.JiludanModel;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.TimeUtils;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/24
 * @time: 18:21
 * @description: 意见记录单
 */

public class YjJiludanAdapter extends RecyclerView.Adapter<YjJiludanAdapter.ViewHolder> {
    private Context context;
    private List<JiludanModel.CommentsBean> list;

    public YjJiludanAdapter(Context context, List<JiludanModel.CommentsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_yj_jiludan, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.content.setText(list.get(position).getComment());
        holder.name.setText(list.get(position).getUser_name());
        GlideUtil.getInstance().loadCircleImage(context,holder.head, PublicStaticData.URL+list.get(position).getAvatar());
        holder.time.setText(TimeUtils.millis2String(list.get(position).getAdd_time()*1000,"yyyy-MM-dd"));

        for (int i =0;i<5;i++){
            if (i<list.get(position).getRank()){
                holder.xins[i].setVisibility(View.VISIBLE);
            }else {
                holder.xins[i].setVisibility(View.GONE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView[] xins = new ImageView[5];
        private TextView content;
        private TextView name;
        private ImageView head;
        private TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            xins[0] = (ImageView) itemView.findViewById(R.id.jiludan_xin1);
            xins[1] = (ImageView) itemView.findViewById(R.id.jiludan_xin2);
            xins[2] = (ImageView) itemView.findViewById(R.id.jiludan_xin3);
            xins[3] = (ImageView) itemView.findViewById(R.id.jiludan_xin4);
            xins[4] = (ImageView) itemView.findViewById(R.id.jiludan_xin5);
            content = (TextView) itemView.findViewById(R.id.yj_jiludan_tv_neirong);
            name = (TextView) itemView.findViewById(R.id.yj_jiludan_tv_name);
            time = (TextView) itemView.findViewById(R.id.yj_jiludan_tv_time);
            head = (ImageView) itemView.findViewById(R.id.yj_jiludan_iv_head);


        }
    }
}
