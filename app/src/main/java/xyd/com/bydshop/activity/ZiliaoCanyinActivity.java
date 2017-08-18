package xyd.com.bydshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.entity.AvatarModel;
import xyd.com.bydshop.entity.SubcategoryModel;
import xyd.com.bydshop.entity.UserDataModel;
import xyd.com.bydshop.flowlayout.FlowLayout;
import xyd.com.bydshop.flowlayout.TagAdapter;
import xyd.com.bydshop.flowlayout.TagFlowLayout;
import xyd.com.bydshop.serviceapi.MerchantApi;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/13.
 * D:  商家资料餐饮
 * C:
 */

public class ZiliaoCanyinActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.rcv_edt_name)
    EditText rcvEdtName;
    @Bind(R.id.rcv_tv_name)
    TextView rcvTvName;
    @Bind(R.id.rcv_edt_contact)
    EditText rcvEdtContact;
    @Bind(R.id.rcv_tv_contact)
    TextView rcvTvContact;
    @Bind(R.id.rcv_edt_phone1)
    EditText rcvEdtPhone1;
    @Bind(R.id.rcv_tv_phone1)
    TextView rcvTvPhone1;
    @Bind(R.id.rcv_edt_phone2)
    EditText rcvEdtPhone2;
    @Bind(R.id.rcv_tv_phone2)
    TextView rcvTvPhone2;
    @Bind(R.id.rcv_edt_email)
    EditText rcvEdtEmail;
    @Bind(R.id.rcv_tv_email)
    TextView rcvTvEmail;
    @Bind(R.id.rcv_edt_wx)
    EditText rcvEdtWx;
    @Bind(R.id.rcv_tv_wx)
    TextView rcvTvWx;
    @Bind(R.id.rcv_edt_chuanzhen)
    EditText rcvEdtChuanzhen;
    @Bind(R.id.rcv_tv_chuanzhen)
    TextView rcvTvChuanzhen;
    @Bind(R.id.rcv_edt_address)
    EditText rcvEdtAddress;
    @Bind(R.id.rcv_tv_address)
    TextView rcvTvAddress;
    @Bind(R.id.rcv_tv_tese)
    TextView rcvTvTese;
    @Bind(R.id.rcv_tfl_tese)
    TagFlowLayout rcvTflTese;
    @Bind(R.id.rcv_edt_zuowei)
    EditText rcvEdtZuowei;
    @Bind(R.id.rcv_tv_zuowei)
    TextView rcvTvZuowei;
    @Bind(R.id.rcv_edt_car)
    EditText rcvEdtCar;
    @Bind(R.id.cv_tfl_car)
    TagFlowLayout cvTflCar;
    @Bind(R.id.rcv_rl_bouttom)
    RelativeLayout rcvRlBouttom;
    boolean isSave = false;
    private TagAdapter<String> carAdapter;
    private TagAdapter<String> teseAdapter;
    private List<String> car;
    private List<String> tese;
    private List<String> carStringList;
    private String teseString;
    private UserDataModel userDataModel;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ziliao_canyin;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvRight.setVisibility(View.VISIBLE);
        rcvRlBouttom.setVisibility(View.GONE);
        titleTvRight.setText("修改");
        titleTvBack.setText("商家资料");
        setChangeVisibility(isSave);
        rcvTvTese.setText("经营特色");
        rcvEdtCar.setVisibility(View.GONE);
        tese = new ArrayList<>();
        car = new ArrayList<>();
        carStringList = new ArrayList<>();
        getUserData();
        setCarTagFlowLayout();
        setTeseTagFlowLayout();
    }


    /**
     * 获取商家资料
     */
    private void getUserData() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .editUser(PublicStaticData.sharedPreferences.getString(PublicStaticData.ID, ""),
                        PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<UserDataModel>>compose())
                .subscribe(new BaseObserver<UserDataModel>() {
                    @Override
                    protected void onHandleSuccess(UserDataModel model, int code, String msg) {
                        userDataModel = model;
                        rcvTvAddress.setText(model.getAddress());
                        rcvTvChuanzhen.setText(model.getFax());
                        rcvTvContact.setText(model.getContacts_name());
                        rcvTvEmail.setText(model.getEmail());
                        rcvTvName.setText(model.getMer_name());
                        rcvTvPhone1.setText(model.getMobile());
                        rcvTvPhone2.setText(model.getMobile_bak());
                        rcvTvWx.setText(model.getWechat_name());
                        rcvTvZuowei.setText(model.getSeat_num() + "");
                        carStringList.clear();
                        teseString = model.getSub_cate_name();
                        // "大巴停车场", "停车场", "大巴临时上下客"
                        for (String s : Arrays.asList(model.getPark().split(","))) {
                            if (s.equals("1"))
                                carStringList.add("大巴停车场");
                            if (s.equals("2"))
                                carStringList.add("停车场");
                            if (s.equals("3"))
                                carStringList.add("大巴临时上下客");
                        }
                        tese.clear();
                        tese.add(teseString);
                        teseAdapter.notifyDataChanged();
                        // "大巴停车场", "停车场", "大巴临时上下客"
                        car.clear();
                        car.addAll(carStringList);
                        carAdapter.notifyDataChanged();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    private void setCarTagFlowLayout() {

        carAdapter = new TagAdapter<String>(car) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(ZiliaoCanyinActivity.this).inflate(R.layout.tag_change_order1, parent, false);
                tv.setText(s);
                return tv;
            }
        };
        cvTflCar.setAdapter(carAdapter);

    }

    private void setTeseTagFlowLayout() {

        teseAdapter = new TagAdapter<String>(tese) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(ZiliaoCanyinActivity.this).inflate(R.layout.tag_change_order1, parent, false);
                tv.setText(s);
                return tv;
            }
        };
        rcvTflTese.setMaxSelectCount(1);
        rcvTflTese.setAdapter(teseAdapter);

    }

    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.title_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.title_tv_right:
                Intent intent = new Intent(this, SaveZiliaoCyActivity.class);
                intent.putExtra(SaveZiliaoCyActivity.SAVE_USER, userDataModel);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getUserData();
    }

    private void setChangeVisibility(boolean b) {
        setViewVisibility(rcvEdtAddress, b);
        setViewVisibility(rcvEdtCar, b);
        setViewVisibility(rcvEdtChuanzhen, b);
        setViewVisibility(rcvEdtContact, b);
        setViewVisibility(rcvEdtEmail, b);
        setViewVisibility(rcvEdtName, b);
        setViewVisibility(rcvEdtPhone1, b);
        setViewVisibility(rcvEdtPhone2, b);
        setViewVisibility(rcvEdtWx, b);
        setViewVisibility(rcvEdtZuowei, b);
        setViewVisibilityTv(rcvTvAddress, b);
        setViewVisibilityTv(rcvTvChuanzhen, b);
        setViewVisibilityTv(rcvTvContact, b);
        setViewVisibilityTv(rcvTvEmail, b);
        setViewVisibilityTv(rcvTvName, b);
        setViewVisibilityTv(rcvTvPhone1, b);
        setViewVisibilityTv(rcvTvPhone2, b);
        setViewVisibilityTv(rcvTvWx, b);
        setViewVisibilityTv(rcvTvZuowei, b);

    }

    private void setViewVisibility(View v, boolean b) {
        if (b) {
            v.setVisibility(View.VISIBLE);
        } else {
            v.setVisibility(View.GONE);
        }
    }

    private void setViewVisibilityTv(View v, boolean b) {
        if (b) {
            v.setVisibility(View.GONE);
        } else {
            v.setVisibility(View.VISIBLE);
        }
    }
}
