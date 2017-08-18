package xyd.com.bydshop.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import xyd.com.bydshop.R;
import xyd.com.bydshop.entity.PushMessageModel;
import xyd.com.bydshop.utils.TimeUtils;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/15
 * @time: 10:59
 * @description:
 */

public class PushMessageAdapter extends BaseAdapter {
    private Context context;
    private  List<PushMessageModel.NoticesBean> noticesBeanList;

    public PushMessageAdapter(Context context, List<PushMessageModel.NoticesBean> noticesBeanList){
        this.context=context;
        this.noticesBeanList=noticesBeanList;
    }
    @Override
    public int getCount() {
        return noticesBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticesBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_push_message,null,false);
            holder=new ViewHolder();
            holder.msg= (TextView) convertView.findViewById(R.id.item_push_content);
            holder.time= (TextView) convertView.findViewById(R.id.item_push_time);
            holder.title= (TextView) convertView.findViewById(R.id.item_push_title);
            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title.setText(noticesBeanList.get(position).getTitle());
        holder.msg.setText(noticesBeanList.get(position).getMsg());
        long time=noticesBeanList.get(position).getAdd_time()*1000;
        holder.time.setText(TimeUtils.millis2String(time,"yyyy-MM-dd HH:mm"));


        return convertView;
    }

    class ViewHolder{
        private TextView time;
        private TextView title;
        private TextView msg;

    }
}
