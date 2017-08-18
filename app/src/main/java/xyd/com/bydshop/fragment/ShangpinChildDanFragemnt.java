package xyd.com.bydshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyd.com.bydshop.R;
import xyd.com.bydshop.adapter.CanyinDanAdapter;
import xyd.com.bydshop.adapter.CanyinTuanAdapter;
import xyd.com.bydshop.entity.CanyinMealModel;
import xyd.com.bydshop.recyclerview.ProgressStyle;
import xyd.com.bydshop.recyclerview.ZXLRecyclerView;

/**
 * Created by ${zxl} on 2017/4/10.
 * D:  餐饮类——商品——单点、团餐、优惠
 * C:
 */

public class ShangpinChildDanFragemnt extends Fragment {
    @Bind(R.id.base_listview)
    ZXLRecyclerView baseListview;
    private int state=1;
    private CanyinDanAdapter adapter;
    private List<CanyinMealModel.SingleMealBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_listview, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;


    }

    private void initView() {
        CanyinMealModel model= (CanyinMealModel) getArguments().getSerializable("meal");
        list=new ArrayList<>();
        list.addAll(model.getSingle_meal());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        baseListview.setLayoutManager(layoutManager);
        baseListview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        baseListview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        baseListview.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        baseListview.setLoadingMoreEnabled(false);
        baseListview.setPullRefreshEnabled(false);
        adapter =new CanyinDanAdapter(list,getActivity());
        baseListview.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
