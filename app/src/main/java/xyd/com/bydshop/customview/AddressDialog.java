package xyd.com.bydshop.customview;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xyd.com.bydshop.R;
import xyd.com.bydshop.entity.AddressModel;
import xyd.com.bydshop.utils.ToastUtils;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/28
 * @time: 11:52
 * @description:
 */

public class AddressDialog extends CustomDialog {


    private TextView no;
    private TextView yes;
    private TextView title;
    private WheelView wv1;
    private WheelView wv2;
    private WheelView wv3;
    private OnAddressSelectedListener onAddressSelectedListener;
    private AddressModel model;
    private int region_id;


    public AddressDialog(Context context, AddressModel addressModel, final OnAddressSelectedListener onAddressSelectedListener) {
        super(context, R.layout.dialog_address_choose, true);
        this.onAddressSelectedListener = onAddressSelectedListener;
        this.model = addressModel;
        no = (TextView) findViewById(R.id.address_tv_close);
        yes = (TextView) findViewById(R.id.address_tv_ok);
        title = (TextView) findViewById(R.id.address_tv_title);
        wv1 = (WheelView) findViewById(R.id.address_wv_1);
        wv2 = (WheelView) findViewById(R.id.address_wv_2);
        wv3 = (WheelView) findViewById(R.id.address_wv_3);
        final List<String> list1 = new ArrayList<>();
        final List<String> list2 = new ArrayList<>();
        final List<String> list3 = new ArrayList<>();
        list1.clear();
        for (AddressModel.RegionTreeBean bean : model.getRegion_tree()) {
            list1.add(bean.getRegion_name());
        }
        list2.add("");
        list2.add("");
        list2.add("");
        list3.add("");
        list3.add("");
        list3.add("");
        wv1.setItems(list1);
        wv1.setOffset(1);
        wv1.setSeletion(0);
        wv2.setItems(list2);
        wv2.setOffset(1);
        wv2.setSeletion(0);
        wv3.setItems(list3);
        wv3.setOffset(1);
        wv3.setSeletion(0);
        list2.clear();
        for (AddressModel.RegionTreeBean.NodesBean bean : model.getRegion_tree().get(0).getNodes()) {
            list2.add(bean.getRegion_name());
        }
        wv2.setItems(list2);
        list3.clear();
        for (AddressModel.RegionTreeBean.NodesBean.NodesBean1 bean : model.getRegion_tree().get(0).getNodes().get(0).getNodes()) {
            list3.add(bean.getRegion_name());
        }
        wv3.setItems(list3);
        region_id = getRegionId();
        wv1.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                list2.clear();
                for (AddressModel.RegionTreeBean.NodesBean bean : model.getRegion_tree().get(selectedIndex - 1).getNodes()) {
                    list2.add(bean.getRegion_name());
                }
                wv2.setItems(list2);

                list3.clear();
                for (AddressModel.RegionTreeBean.NodesBean.NodesBean1 bean : model.getRegion_tree().get(selectedIndex - 1).getNodes().get(0).getNodes()) {
                    list3.add(bean.getRegion_name());
                }
                wv3.setItems(list3);
                region_id = getRegionId();
            }
        });
        wv2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                list3.clear();
                for (AddressModel.RegionTreeBean.NodesBean.NodesBean1 bean : model.getRegion_tree().get(wv1.getSeletedIndex()).getNodes().get(selectedIndex - 1).getNodes()) {
                    list3.add(bean.getRegion_name());
                }
                wv3.setItems(list3);
                region_id = getRegionId();
            }
        });
        wv3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);

                region_id = getRegionId();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressSelectedListener.onSelected(region_id, title.getText().toString());
                dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface OnAddressSelectedListener {
        void onSelected(int id, String address);
    }

    private int getRegionId() {
        int wv1Id;
        int wv2Id;
        int wv3Id;
        if (!TextUtils.isEmpty(wv3.getSeletedItem())) {
            wv1Id = wv1.getSeletedIndex();
            wv2Id = wv2.getSeletedIndex();
            wv3Id = wv3.getSeletedIndex();
            title.setText(wv1.getSeletedItem() + "-" + wv2.getSeletedItem() + "-" + wv3.getSeletedItem());
            return model.getRegion_tree().get(wv1Id).getNodes().get(wv2Id).getNodes().get(wv3Id).getRegion_id();

        } else if (!TextUtils.isEmpty(wv2.getSeletedItem())) {
            wv1Id = wv1.getSeletedIndex();
            wv2Id = wv2.getSeletedIndex();
            wv3Id = -1;
            title.setText(wv1.getSeletedItem() + "-" + wv2.getSeletedItem());
            return model.getRegion_tree().get(wv1Id).getNodes().get(wv2Id).getRegion_id();
        } else {
            wv1Id = wv1.getSeletedIndex();
            wv2Id = -1;
            wv3Id = -1;
            title.setText(wv1.getSeletedItem());
            return model.getRegion_tree().get(wv1Id).getRegion_id();
        }

    }
}
