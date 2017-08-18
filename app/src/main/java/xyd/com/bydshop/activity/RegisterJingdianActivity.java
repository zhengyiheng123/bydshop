package xyd.com.bydshop.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.customview.AddressDialog;
import xyd.com.bydshop.entity.AddressModel;
import xyd.com.bydshop.entity.LoginModel;
import xyd.com.bydshop.entity.SubcategoryModel;
import xyd.com.bydshop.flowlayout.FlowLayout;
import xyd.com.bydshop.flowlayout.TagAdapter;
import xyd.com.bydshop.flowlayout.TagFlowLayout;
import xyd.com.bydshop.serviceapi.MerchantApi;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.LogUtil;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.RegexUtils;

/**
 * Created by ${zxl} on 2017/4/12.
 * D: 景点注册时填写信息
 * C:
 */

public class RegisterJingdianActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.rjd_edt_name)
    EditText rjdEdtName;
    @Bind(R.id.rjd_cb_owner)
    CheckBox rjdCbOwner;
    @Bind(R.id.rjd_cb_daili)
    CheckBox rjdCbDaili;
    @Bind(R.id.rjd_edt_contact)
    EditText rjdEdtContact;
    @Bind(R.id.rjd_edt_phone1)
    EditText rjdEdtPhone1;
    @Bind(R.id.rjd_edt_phone2)
    EditText rjdEdtPhone2;
    @Bind(R.id.rjd_edt_email)
    EditText rjdEdtEmail;
    @Bind(R.id.rjd_edt_wx)
    EditText rjdEdtWx;
    @Bind(R.id.rjd_edt_chuanzhen)
    EditText rjdEdtChuanzhen;
    @Bind(R.id.rjd_edt_address)
    EditText rjdEdtAddress;
    @Bind(R.id.rjd_tfl_tese)
    TagFlowLayout rjdTflTese;

    @Bind(R.id.rjd_tv_commit)
    TextView rjdTvCommit;
    @Bind(R.id.rjd_tv_area)
    TextView rjdTvArea;
    @Bind(R.id.rjd_tv_mianze)
    CheckBox rjdTvMianze;
    private String shopType;
    private List<String> teseString;
    private List<SubcategoryModel.SubCategoriesBean> subCate;
    private TagAdapter<String> adapter;
    private String sub_cate_id = "";
    private String park = "";
    private AddressModel addressModel;
    private int region_id=-1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_jingdian;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("注册详细信息");
        shopType = getIntent().getStringExtra(ChangeOrderActivity.SHOP_TYPE);


        teseString = new ArrayList<>();
        subCate = new ArrayList<>();
        adapter = new TagAdapter<String>(teseString) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(RegisterJingdianActivity.this).inflate(R.layout.tag_change_order, parent, false);
                tv.setText(s);
                return tv;
            }
        };
        rjdTflTese.setAdapter(adapter);
        rjdTflTese.setMaxSelectCount(1);
        rjdTflTese.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                sub_cate_id = subCate.get(position).getSub_cate_id() + "";
                return false;
            }
        });
        getArea();
        getSubcategory();
        rjdCbOwner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    rjdCbDaili.setChecked(false);
                else
                    rjdCbDaili.setChecked(true);
            }
        });
        rjdCbDaili.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    rjdCbOwner.setChecked(false);
                else
                    rjdCbOwner.setChecked(true);
            }
        });
    }


    /**
     * 获取子级列表分类
     */
    private void getSubcategory() {
        BaseApi.getRetrofit()
                .create(MerchantApi.class)
                .subcategory(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),shopType)
                .compose(RxSchedulers.<BaseModel<SubcategoryModel>>compose())
                .subscribe(new BaseObserver<SubcategoryModel>() {
                    @Override
                    protected void onHandleSuccess(SubcategoryModel subcategoryModel, int code, String msg) {
                        teseString.clear();
                        subCate.clear();
                        subCate.addAll(subcategoryModel.getSub_categories());
                        for (SubcategoryModel.SubCategoriesBean bean : subCate) {
                            teseString.add(bean.getCate_name());
                        }
                        adapter.notifyDataChanged();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });

    }


    /**
     * 获取地区列表分类
     */
    private void getArea() {
        BaseApi.getRetrofit()
                .create(MerchantApi.class)
                .region_tree(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<AddressModel>>compose())
                .subscribe(new BaseObserver<AddressModel>() {
                    @Override
                    protected void onHandleSuccess(AddressModel model, int code, String msg) {
                        addressModel = model;
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });

    }
    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.rjd_tv_mianze, R.id.rjd_tv_area,R.id.rjd_tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.rjd_tv_mianze:

                break;
            case R.id.rjd_tv_area:
                AddressDialog dialog=new AddressDialog(this, addressModel, new AddressDialog.OnAddressSelectedListener() {
                    @Override
                    public void onSelected(int id, String address) {
                        region_id = id;
                        rjdTvArea.setText(address);
                    }
                });
                if (addressModel != null)
                    dialog.show();

                break;
            case R.id.rjd_tv_commit:
                /**
                 * 保存商户注册详情
                 * @param apitoken            身份验证token
                 * @param mer_name            商户名称
                 * @param contacts_name       联系人姓名
                 * @param mobile              手机号
                 * @param mobile_bak          备用手机号
                 * @param email               邮箱地址
                 * @param wechat_name         微信号
                 * @param fax                 传真
                 * @param cate_id             顶级分类id 1|中餐 2|特色餐 3|景区门票 4|车行
                 * @param address             详细地址
                 * @param sub_cate_id         子级栏目id
                 * @param seat_num            座位数量，cate_id为1或2时必须
                 * @param park                停车场类型(多选,逗号分隔): 1|大巴停车场,2|停车场,3|大巴临时上长下客
                 * @param mer_type            商家性质: 1|商户拥有者: 2|代理商  cate_id=3时必须,
                 * @param longitude           经度
                 * @param latitude            纬度
                 * @return
                 */
                String mer_name = rjdEdtName.getText().toString();
                String contacts_name = rjdEdtContact.getText().toString();
                String mobile = rjdEdtPhone1.getText().toString();
                String mobile_bak = rjdEdtPhone2.getText().toString();
                String address = rjdEdtAddress.getText().toString();
                String email = rjdEdtEmail.getText().toString();
                String wechat_name = rjdEdtWx.getText().toString();
                String fax = rjdEdtChuanzhen.getText().toString();
                String seat_num = "";
                String longitude = "0.00";
                String latitude = "0.00";
                String mer_type = "";
                if (rjdCbOwner.isChecked()) {
                    mer_type = "1";
                } else {
                    mer_type = "2";
                }
                if (TextUtils.isEmpty(mer_name)) {
                    showToast("请输入商家名称");
                } else if (TextUtils.isEmpty(contacts_name)) {
                    showToast("请输入联系人姓名");
                } else if (!RegexUtils.isMobilePhoneNumber(mobile)) {
                    showToast("请输入正确的手机号");
                } else if (TextUtils.isEmpty(address)) {
                    showToast("请输入餐厅详细地址");
                } else if (!rjdTvMianze.isChecked()) {
                    showToast("请同意免责声明");
                } else if (region_id==-1){
                    showToast("请选择地区");
                }else {
                    commit(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), mer_name, contacts_name,
                            mobile, mobile_bak, email, wechat_name, fax, shopType, address, sub_cate_id, seat_num, park, mer_type, longitude, latitude);
                }
                ;
                break;
        }
    }

    /**
     * 保存商户注册详情
     *
     * @param apitoken      身份验证token
     * @param mer_name      商户名称
     * @param contacts_name 联系人姓名
     * @param mobile        手机号
     * @param mobile_bak    备用手机号
     * @param email         邮箱地址
     * @param wechat_name   微信号
     * @param fax           传真
     * @param cate_id       顶级分类id 1|中餐 2|特色餐 3|景区门票 4|车行
     * @param address       详细地址
     * @param sub_cate_id   子级栏目id
     * @param seat_num      座位数量，cate_id为1或2时必须
     * @param park          停车场类型(多选,逗号分隔): 1|大巴停车场,2|停车场,3|大巴临时上长下客
     * @param mer_type      商家性质: 1|商户拥有者: 2|代理商  cate_id=3时必须,
     * @param longitude     经度
     * @param latitude      纬度
     * @return
     */
    private void commit(String apitoken, String mer_name, String contacts_name, String mobile, String mobile_bak, String email, String wechat_name,
                        String fax, String cate_id, String address, String sub_cate_id, String seat_num, String park, String mer_type, String longitude,
                        String latitude) {
        LogUtil.e("apitoken:"+apitoken+"\n"+
                "mer_name:"+mer_name+"\n"+
                "contacts_name:"+contacts_name+"\n"+
                "mobile:"+mobile+"\n"+
                "mobile_bak:"+mobile_bak+"\n"+
                "email:"+email+"\n"+
                "wechat_name:"+wechat_name+"\n"+
                "fax:"+fax+"\n"+
                "cate_id:"+cate_id+"\n"+
                "address:"+address+"\n"+
                "sub_cate_id:"+sub_cate_id+"\n"+
                "seat_num:"+seat_num+"\n"+
                "park:"+park+"\n"+
                "mer_type:"+mer_type+"\n"+
                "longitude:"+longitude+"\n"+
                "latitude:"+latitude+"\n"
        );
        BaseApi.getRetrofit()
                .create(MerchantApi.class)
                .merchant(apitoken, mer_name, contacts_name, mobile, mobile_bak, email, wechat_name, fax,
                        cate_id, address, sub_cate_id, seat_num, park, mer_type, longitude, latitude,region_id+"")
                .compose(RxSchedulers.<BaseModel<LoginModel>>compose())
                .subscribe(new BaseObserver<LoginModel>() {
                    @Override
                    protected void onHandleSuccess(LoginModel loginModel, int code, String msg) {
                        showToast(msg);
                        PublicStaticData.isCanyin=false;
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.SUB_ID, loginModel.getCate_id() + "").commit();
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.ID, loginModel.getMer_id() + "").commit();
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.SUB_CATE_ID, loginModel.getSub_cate_id() + "").commit();
                        ActivityFactory.goMain(RegisterJingdianActivity.this, null);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }
}

