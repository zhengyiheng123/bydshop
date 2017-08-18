package xyd.com.bydshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import xyd.com.bydshop.R;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/24
 * @time: 19:08
 * @description:
 */

public class HuifuAdapter extends BaseAdapter {
    Context context;
    public HuifuAdapter (Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 2;
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
        return LayoutInflater.from(context).inflate(R.layout.item_jiludan_huifu,parent,false);
    }
}
