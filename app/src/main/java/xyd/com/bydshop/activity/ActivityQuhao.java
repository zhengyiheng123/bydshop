package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.R;
import xyd.com.bydshop.adapter.QuhaoAdapter;
import xyd.com.bydshop.customview.ZxlSideBar;
import xyd.com.bydshop.entity.QuhaoEntity;

/**
 * Created by ${zxl} on 2017/4/12.
 * D:国际区号
 * C:
 */

public class ActivityQuhao extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.quhao_lv)
    ListView quhaoLv;
    @Bind(R.id.quhao_sidebar)
    ZxlSideBar quhaoSidebar;
    @Bind(R.id.quhao_floating_header)
    TextView quhaoFloatingHeader;
    private QuhaoAdapter adapter;
    private ArrayList<QuhaoEntity> list;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_quhao;
    }

    protected void initView() {
        String s = getString();
        Log.e("quhao", getString());
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<QuhaoEntity>>() {
        }.getType();
        list = gson.fromJson(s, collectionType);
        EventBus.getDefault().register(this);
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("国际区号");
        adapter = new QuhaoAdapter(this, R.layout.item_quhao, list);
        quhaoLv.setAdapter(adapter);
        quhaoSidebar.setListView(quhaoLv);
        quhaoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(list.get(position));
                finish();
            }
        });

    }

    @Subscribe
    public void onEvent(QuhaoEntity q) {
       // titleTvBack.setText(q.getArea_code());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
        }
    }

    public String getString() {
        InputStream inputStream = getResources().openRawResource(R.raw.code);
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "gbk");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
