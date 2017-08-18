package xyd.com.bydshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import xyd.com.bydshop.R;
import xyd.com.bydshop.entity.PushMessageModel;
import xyd.com.bydshop.utils.TimeUtils;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/15
 * @time: 13:58
 * @description:
 */

public class PushRvAdapter extends RecyclerView.Adapter<PushRvAdapter.PushHolder> {
    private Context context;
    private List<PushMessageModel.NoticesBean> list;

    public PushRvAdapter(Context context, List<PushMessageModel.NoticesBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public PushHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PushHolder(LayoutInflater.from(context).inflate(R.layout.item_push_message,parent,false));
    }

    @Override
    public void onBindViewHolder(PushHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.msg.setText(list.get(position).getMsg());
        long time=list.get(position).getAdd_time()*1000;
        holder.time.setText(TimeUtils.millis2String(time,"yyyy-MM-dd HH:mm"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PushHolder extends RecyclerView.ViewHolder {
        private TextView msg;
        private TextView time;
        private TextView title;

        public PushHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            msg= (TextView) itemView.findViewById(R.id.item_push_content);
            time= (TextView) itemView.findViewById(R.id.item_push_time);
            title= (TextView) itemView.findViewById(R.id.item_push_title);
        }
    }
}
