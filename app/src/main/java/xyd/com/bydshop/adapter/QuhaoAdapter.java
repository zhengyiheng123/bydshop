package xyd.com.bydshop.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyd.com.bydshop.R;
import xyd.com.bydshop.entity.QuhaoEntity;
import xyd.com.bydshop.utils.LogUtil;

/**
 * Created by ${zxl} on 2017/4/12.
 * D: 国际区号适配器
 * C:
 */

public class QuhaoAdapter extends ArrayAdapter<QuhaoEntity> implements SectionIndexer {
    private static final String TAG = "QuhaoAdapter";
    private Context context;
    private List<String> sList;
    private List<QuhaoEntity> qList;
    private List<QuhaoEntity> copyqList;
    private LayoutInflater layoutInflater;
    private SparseIntArray positionOfSection;
    private SparseIntArray sectionOfPosition;
    private MyFilter myFilter;
    private int res;
    private boolean notiyfyByFilter;

    public QuhaoAdapter(Context context, int resource, List<QuhaoEntity> list) {
        super(context, resource, list);
        this.res = resource;
        this.qList = list;
        this.context = context;
        copyqList = new ArrayList<>();
        copyqList.addAll(list);
        layoutInflater = LayoutInflater.from(context);
    }

    private static class ViewHolder {
        TextView headerView;
        TextView nameView;
        TextView numberView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_quhao, parent, false);
            holder.headerView = (TextView) convertView.findViewById(R.id.quhao_header);
            holder.nameView = (TextView) convertView.findViewById(R.id.quhao_name);
            holder.numberView = (TextView) convertView.findViewById(R.id.quhao_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        QuhaoEntity entity = qList.get(position);
        if (entity == null) {
            LogUtil.e(TAG, "entity" + position);
        }
        String name = entity.getArea();
        String header = entity.getInitialLetter();
        if (position == 0 || header != null && !header.equals(getItem(position - 1).getInitialLetter())) {
            if (TextUtils.isEmpty(header)) {
                holder.headerView.setVisibility(View.GONE);
            } else {
                holder.headerView.setText(header);
                holder.headerView.setVisibility(View.VISIBLE);

            }
        } else {
            holder.headerView.setVisibility(View.GONE);
        }
        holder.nameView.setText(name);
        holder.numberView.setText(entity.getArea_code());
        return convertView;
    }

    @Override
    public QuhaoEntity getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionOfSection.get(sectionIndex);
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionOfPosition.get(position);
    }

    @Override
    public Object[] getSections() {
        positionOfSection = new SparseIntArray();
        sectionOfPosition = new SparseIntArray();
        int count = getCount();
        sList = new ArrayList<String>();
        //热门、搜等等
        sList.add("A");
        positionOfSection.put(0, 0);
        sectionOfPosition.put(0, 0);
        for (int i = 1; i < count; i++) {

            String letter = getItem(i).getInitialLetter();
            int section = sList.size() - 1;
            if (sList.get(section) != null && !sList.get(section).equals(letter)) {
                sList.add(letter);
                section++;
                positionOfSection.put(section, i);
            }
            sectionOfPosition.put(i, section);
        }
        return sList.toArray(new String[sList.size()]);
    }


    @Override
    public Filter getFilter() {
        if (myFilter == null) {
            myFilter = new MyFilter(qList);
        }
        return myFilter;
    }

    protected class MyFilter extends Filter {
        List<QuhaoEntity> mOriginalList = null;

        public MyFilter(List<QuhaoEntity> myList) {
            this.mOriginalList = myList;
        }

        @Override
        protected synchronized FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (mOriginalList == null) {
                mOriginalList = new ArrayList<QuhaoEntity>();
            }
            LogUtil.d(TAG, "contacts original size: " + mOriginalList.size());
            LogUtil.d(TAG, "contacts copy size: " + copyqList.size());

            if (prefix == null || prefix.length() == 0) {
                results.values = copyqList;
                results.count = copyqList.size();
            } else {
                String prefixString = prefix.toString();
                final int count = mOriginalList.size();
                final ArrayList<QuhaoEntity> newValues = new ArrayList<QuhaoEntity>();
                for (int i = 0; i < count; i++) {
                    final QuhaoEntity quaho = mOriginalList.get(i);
                    String username = quaho.getArea();

                    if (username.startsWith(prefixString)) {
                        newValues.add(quaho);
                    } else {
                        final String[] words = username.split(" ");
                        final int wordCount = words.length;

                        // Start at index 0, in case valueText starts with space(s)
                        for (String word : words) {
                            if (word.startsWith(prefixString)) {
                                newValues.add(quaho);
                                break;
                            }
                        }
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            LogUtil.d(TAG, "contacts filter results size: " + results.count);
            return results;
        }

        @Override
        protected synchronized void publishResults(CharSequence constraint,
                                                   FilterResults results) {
            qList.clear();
            qList.addAll((List<QuhaoEntity>) results.values);
            LogUtil.d(TAG, "publish contacts filter results size: " + results.count);
            if (results.count > 0) {
                notiyfyByFilter = true;
                notifyDataSetChanged();
                notiyfyByFilter = false;
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!notiyfyByFilter) {
            copyqList.clear();
            copyqList.addAll(qList);
        }
    }
}
