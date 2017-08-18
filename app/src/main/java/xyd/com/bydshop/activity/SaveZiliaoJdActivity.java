package xyd.com.bydshop.activity;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
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
import xyd.com.bydshop.utils.RegexUtils;

/**
 * Created by ${zxl} on 2017/4/13.
 * D: 商家资料景点
 * C:
 */

public class SaveZiliaoJdActivity extends BaseActivity {
    public static final String JD_USER = "jd_user";
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
    @Bind(R.id.rjd_ll_tese)
    LinearLayout rjd_ll_tese;
    boolean isSave = false;
    private TagAdapter<String> teseAdapter;
    private UserDataModel userDataModel;
    private List<String> teseString;
    private UserDataModel model;
    private List<SubcategoryModel.SubCategoriesBean> tese;

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
        titleTvRight.setText("保存");
        titleTvBack.setText("商家资料");
        rjdTvTese.setText("售票类型");
        rjd_ll_tese.setVisibility(View.GONE);
        model = (UserDataModel) getIntent().getSerializableExtra(JD_USER);
      //  setTeseTagFlowLayout();
      //  getSubCate();
        setCbCheck();
      //  tese = new ArrayList<>();
        setUserData();
    }
    /**
     * 获取子级分类
     */
    private void getSubCate() {
        BaseApi.getRetrofit()
                .create(MerchantApi.class)
                .subcategory(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        PublicStaticData.sharedPreferences.getString(PublicStaticData.SUB_ID, ""))
                .compose(RxSchedulers.<BaseModel<SubcategoryModel>>compose())
                .subscribe(new BaseObserver<SubcategoryModel>() {
                    @Override
                    protected void onHandleSuccess(SubcategoryModel subcategoryModel, int code, String msg) {
                        tese.clear();
                        teseString.clear();
                        tese.addAll(subcategoryModel.getSub_categories());
                        for (SubcategoryModel.SubCategoriesBean bean : tese) {
                            teseString.add(bean.getCate_name());
                        }
                        teseAdapter.notifyDataChanged();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    /**
     * 设置商家资料
     */
    private void setUserData() {
        rjdEdtAddress.setText(model.getAddress());
        rjdEdtChuanzhen.setText(model.getFax());
        rjdEdtContact.setText(model.getContacts_name());
        rjdEdtEmail.setText(model.getEmail());
        rjdEdtName.setText(model.getMer_name());
        rjdEdtPhone1.setText(model.getMobile());
        rjdEdtPhone2.setText(model.getMobile_bak());
        rjdEdtWx.setText(model.getWechat_name());
        if (model.getMer_type()==1){
            rjdCbOwner.setChecked(true);
            rjdCbDaili.setChecked(false);
        }else{
            rjdCbDaili.setChecked(true);
            rjdCbOwner.setChecked(false);
        }

    }

//    private void setTeseTagFlowLayout() {
//        teseAdapter = new TagAdapter<String>(teseString) {
//            @Override
//            public View getView(FlowLayout parent, int position, String s) {
//                TextView tv = (TextView) LayoutInflater.from(SaveZiliaoJdActivity.this).inflate(R.layout.tag_change_order, parent, false);
//                tv.setText(s);
//                return tv;
//            }
//        };
//        rjdTflTese.setAdapter(teseAdapter);
//
//    }

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
                String mer_name = rjdEdtName.getText().toString();
                String contacts_name = rjdEdtContact.getText().toString();
                String mobile = rjdEdtPhone1.getText().toString();
                String mobile_bak = rjdEdtPhone2.getText().toString();
                String email = rjdEdtEmail.getText().toString();
                String wechat_name = rjdEdtWx.getText().toString();
                String fax = rjdEdtChuanzhen.getText().toString();
                String address = rjdEdtAddress.getText().toString();
                String seat_num ="";
                String longitude = 12.00+"";
                String latitude =  12.00+"";
                String mer_type;
                if (rjdCbOwner.isChecked()){
                    mer_type="1";
                }else {
                    mer_type="2";
                }
                if (TextUtils.isEmpty(mer_name)) {
                    showToast("请输入商家名称");
                } else if (TextUtils.isEmpty(contacts_name)) {
                    showToast("请输入联系人姓名");
                } else if (!RegexUtils.isMobilePhoneNumber(mobile)) {
                    showToast("请输入正确的手机号");
                } else if (TextUtils.isEmpty(address)) {
                    showToast("请输入餐厅详细地址");
                } else {
                    commit(mer_name,contacts_name,mobile,mobile_bak,email,wechat_name,fax,address,seat_num,"",longitude,latitude,mer_type);
                }
                break;
        }
    }

    private void commit(String mer_name, String contacts_name, String mobile, String mobile_bak, String email, String wechat_name, final String fax,
                        String address, String seat_num, String park, String longitude, String latitude,String mer_type) {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .saveUser(PublicStaticData.sharedPreferences.getString(PublicStaticData.ID, ""),
                        PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        mer_name, contacts_name, mobile, mobile_bak, email, wechat_name, fax, address, seat_num, park, longitude, latitude,mer_type)
                .compose(RxSchedulers.<BaseModel<AvatarModel>>compose())
                .subscribe(new BaseObserver<AvatarModel>() {
                    @Override
                    protected void onHandleSuccess(AvatarModel avatarModel, int code, String msg) {
                        showToast(msg);
                        finish();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }
}
