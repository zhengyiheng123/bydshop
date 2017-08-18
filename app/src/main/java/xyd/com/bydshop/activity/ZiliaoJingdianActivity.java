package xyd.com.bydshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import xyd.com.bydshop.entity.UserDataModel;
import xyd.com.bydshop.flowlayout.FlowLayout;
import xyd.com.bydshop.flowlayout.TagAdapter;
import xyd.com.bydshop.flowlayout.TagFlowLayout;
import xyd.com.bydshop.serviceapi.MineApi;
import xyd.com.bydshop.utils.PublicStaticData;

/**
 * Created by ${zxl} on 2017/4/13.
 * D: 商家资料景点
 * C:
 */

public class ZiliaoJingdianActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.rjd_edt_name)
    EditText rjdEdtName;
    @Bind(R.id.rjd_tv_name)
    TextView rjdTvName;
    @Bind(R.id.rjd_cb_owner)
    CheckBox rjdCbOwner;
    @Bind(R.id.rjd_cb_daili)
    CheckBox rjdCbDaili;
    @Bind(R.id.rjd_edt_contact)
    EditText rjdEdtContact;
    @Bind(R.id.rjd_tv_contact)
    TextView rjdTvContact;
    @Bind(R.id.rjd_edt_phone1)
    EditText rjdEdtPhone1;
    @Bind(R.id.rjd_tv_phone1)
    TextView rjdTvPhone1;
    @Bind(R.id.rjd_edt_phone2)
    EditText rjdEdtPhone2;
    @Bind(R.id.rjd_tv_phone2)
    TextView rjdTvPhone2;
    @Bind(R.id.rjd_edt_email)
    EditText rjdEdtEmail;
    @Bind(R.id.rjd_tv_email)
    TextView rjdTvEmail;
    @Bind(R.id.rjd_edt_wx)
    EditText rjdEdtWx;
    @Bind(R.id.rjd_tv_wx)
    TextView rjdTvWx;
    @Bind(R.id.rjd_edt_chuanzhen)
    EditText rjdEdtChuanzhen;
    @Bind(R.id.rjd_tv_chuanzhen)
    TextView rjdTvChuanzhen;
    @Bind(R.id.rjd_edt_address)
    EditText rjdEdtAddress;
    @Bind(R.id.rjd_tv_address)
    TextView rjdTvAddress;
    @Bind(R.id.rjd_tv_tese)
    TextView rjdTvTese;
    @Bind(R.id.rjd_tfl_tese)
    TagFlowLayout rjdTflTese;
    @Bind(R.id.rjd_rl_bottom)
    RelativeLayout rjdRlBottom;
    boolean isSave=false;
    private TagAdapter<String> teseAdapter;
    private UserDataModel userDataModel;
    private List<String> tese;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ziliao_jingdian;
    }

    private void setCbCheck() {
        rjdCbDaili.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rjdCbOwner.setChecked(false);
                } else {
                    rjdCbOwner.setChecked(true);
                }
            }
        });
        rjdCbOwner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rjdCbDaili.setChecked(false);
                } else {
                    rjdCbDaili.setChecked(true);
                }
            }
        });
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvRight.setVisibility(View.VISIBLE);
        rjdRlBottom.setVisibility(View.GONE);
        titleTvRight.setText("修改");
        titleTvBack.setText("商家资料");
        setChangeVisibility(isSave);
        rjdTvTese.setText("售票类型");
        tese=new ArrayList<>();
        setTeseTagFlowLayout();
        setCbCheck();
        getUserData();
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
                        rjdTvAddress.setText(model.getAddress());
                        rjdTvChuanzhen.setText(model.getFax());
                        rjdTvContact.setText(model.getContacts_name());
                        rjdTvEmail.setText(model.getEmail());
                        rjdTvName.setText(model.getMer_name());
                        rjdTvPhone1.setText(model.getMobile());
                        rjdTvPhone2.setText(model.getMobile_bak());
                        rjdTvWx.setText(model.getWechat_name());
                        if (model.getMer_type()==1){
                            rjdCbOwner.setChecked(true);
                            rjdCbDaili.setChecked(false);
                        }else{
                            rjdCbDaili.setChecked(true);
                            rjdCbOwner.setChecked(false);
                        }
                        tese.clear();
                        tese.add(model.getSub_cate_name());
                        teseAdapter.notifyDataChanged();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }
    private void setTeseTagFlowLayout() {


        teseAdapter = new TagAdapter<String>(tese) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv= (TextView) LayoutInflater.from(ZiliaoJingdianActivity.this).inflate(R.layout.tag_change_order1, parent, false);
                tv.setText(s);
                return tv;
            }
        };
        rjdTflTese.setAdapter(teseAdapter);

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
                Intent intent=new Intent(this,SaveZiliaoJdActivity.class);
                intent.putExtra(SaveZiliaoJdActivity.JD_USER,userDataModel);
                startActivity(intent);
                break;
        }
    }
    private void setChangeVisibility(boolean b) {
        setViewVisibility(rjdEdtAddress, b);
        setViewVisibility(rjdEdtChuanzhen, b);
        setViewVisibility(rjdEdtContact, b);
        setViewVisibility(rjdEdtEmail, b);
        setViewVisibility(rjdEdtName, b);
        setViewVisibility(rjdEdtPhone1, b);
        setViewVisibility(rjdEdtPhone2, b);
        setViewVisibility(rjdEdtWx, b);
        setViewVisibilityTv(rjdTvAddress, b);
        setViewVisibilityTv(rjdTvChuanzhen, b);
        setViewVisibilityTv(rjdTvContact, b);
        setViewVisibilityTv(rjdTvEmail, b);
        setViewVisibilityTv(rjdTvName, b);
        setViewVisibilityTv(rjdTvPhone1, b);
        setViewVisibilityTv(rjdTvPhone2, b);
        setViewVisibilityTv(rjdTvWx, b);

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
