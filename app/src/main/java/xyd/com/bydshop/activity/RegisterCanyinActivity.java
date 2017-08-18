package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import xyd.com.bydshop.utils.StringUtils;

/**
 * Created by ${zxl} on 2017/4/12.
 * D:  注册餐饮部分填写信息
 * C:
 */

public class RegisterCanyinActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    @Bind(R.id.title_tv_back)
    TextView titleTvBack;
    @Bind(R.id.title_tv_right)
    TextView titleTvRight;
    @Bind(R.id.rcv_edt_name)
    EditText rcvEdtName;
    @Bind(R.id.rcv_edt_contact)
    EditText rcvEdtContact;
    @Bind(R.id.rcv_edt_phone1)
    EditText rcvEdtPhone1;
    @Bind(R.id.rcv_edt_phone2)
    EditText rcvEdtPhone2;
    @Bind(R.id.rcv_edt_email)
    EditText rcvEdtEmail;
    @Bind(R.id.rcv_edt_wx)
    EditText rcvEdtWx;
    @Bind(R.id.rcv_edt_chuanzhen)
    EditText rcvEdtChuanzhen;
    @Bind(R.id.rcv_edt_address)
    EditText rcvEdtAddress;
    @Bind(R.id.rcv_tfl_tese)
    TagFlowLayout rcvTflTese;
    @Bind(R.id.rcv_edt_zuowei)
    EditText rcvEdtZuowei;
    @Bind(R.id.rcv_edt_car)
    EditText rcvEdtCar;
    @Bind(R.id.cv_tfl_car)
    TagFlowLayout cvTflCar;
    @Bind(R.id.rcv_tv_mianze)
    CheckBox rcvTvMianze;
    @Bind(R.id.rcv_tv_commit)
    TextView rcvTvCommit;
    @Bind(R.id.rcv_tv_area)
    TextView rcvTvArea;
    private String shopType;
    private List<String> teseString;
    private List<SubcategoryModel.SubCategoriesBean> subCate;
    private String[] carString;
    private String sub_cate_id = "";
    private String park = "";
    private TagAdapter<String> tsAdapter;
    private int region_id = -1;
    private AddressModel addressModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_canyin;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvBack.setText("注册详细信息");
        shopType = getIntent().getStringExtra(ChangeOrderActivity.SHOP_TYPE);
        LogUtil.e(shopType + "0000000000000000");
        teseString = new ArrayList<>();
        subCate = new ArrayList<>();
        //1|大巴停车场,2|停车场,3|大巴临时上下客
        carString = new String[]{"大巴停车场", "停车场", "大巴临时上下客"};
        tsAdapter = new TagAdapter<String>(teseString) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(RegisterCanyinActivity.this).inflate(R.layout.tag_change_order, parent, false);
                tv.setText(s);
                return tv;
            }
        };

        rcvTflTese.setAdapter(tsAdapter);
        rcvTflTese.setMaxSelectCount(1);
        rcvTflTese.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                sub_cate_id = subCate.get(position).getSub_cate_id() + "";
                return false;
            }
        });

        TagAdapter<String> carAdapter = new TagAdapter<String>(carString) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(RegisterCanyinActivity.this).inflate(R.layout.tag_change_order, parent, false);
                tv.setText(s);
                return tv;
            }
        };
        cvTflCar.setMaxSelectCount(3);
        cvTflCar.setAdapter(carAdapter);
        cvTflCar.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                park = "";
                StringBuffer sb = new StringBuffer();
                for (Integer car : selectPosSet) {
                    sb.append(car + 1 + ",");
                }
                park = StringUtils.cutLast(sb.toString());
                LogUtil.e(park);
            }
        });
        cvTflCar.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(RegisterCanyinActivity.this, carString[position], Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        getSubcategory();
    }

    /**
     * 获取子级列表分类
     */
    private void getSubcategory() {
        BaseApi.getRetrofit()
                .create(MerchantApi.class)
                .subcategory(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), shopType)
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
                        tsAdapter.notifyDataChanged();

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

    @OnClick({R.id.title_iv_back, R.id.title_tv_back, R.id.title_tv_right, R.id.rcv_tv_mianze, R.id.rcv_tv_area, R.id.rcv_tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.title_tv_back:
                finish();
                break;
            case R.id.rcv_tv_mianze:

                break;
            case R.id.rcv_tv_area:
                AddressDialog dialog = new AddressDialog(this, addressModel, new AddressDialog.OnAddressSelectedListener() {
                    @Override
                    public void onSelected(int id, String address) {
                        region_id = id;
                        rcvTvArea.setText(address);
                    }
                });
                if (addressModel != null)
                    dialog.show();
                break;
            case R.id.rcv_tv_commit:
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
                String mer_name = rcvEdtName.getText().toString();
                String contacts_name = rcvEdtContact.getText().toString();
                String mobile = rcvEdtPhone1.getText().toString();
                String mobile_bak = rcvEdtPhone2.getText().toString();
                String address = rcvEdtAddress.getText().toString();
                String email = rcvEdtEmail.getText().toString();
                String wechat_name = rcvEdtWx.getText().toString();
                String fax = rcvEdtChuanzhen.getText().toString();
                String seat_num = rcvEdtZuowei.getText().toString();
                String longitude = "0.00";
                String latitude = "0.00";
                if (TextUtils.isEmpty(mer_name)) {
                    showToast("请输入商家名称");
                } else if (TextUtils.isEmpty(contacts_name)) {
                    showToast("请输入联系人姓名");
                } else if (!RegexUtils.isMobilePhoneNumber(mobile)) {
                    showToast("请输入正确的手机号");
                } else if (TextUtils.isEmpty(address)) {
                    showToast("请输入餐厅详细地址");
                } else if (!rcvTvMianze.isChecked()) {
                    showToast("请同意免责声明");
                } else if (region_id==-1){
                    showToast("请选择地区");
                }else {
                    commit(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""), mer_name, contacts_name,
                            mobile, mobile_bak, email, wechat_name, fax, shopType, address, sub_cate_id, seat_num, park, "", longitude, latitude);
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
        BaseApi.getRetrofit()
                .create(MerchantApi.class)
                .merchant(apitoken, mer_name, contacts_name, mobile, mobile_bak, email, wechat_name, fax, cate_id, address,
                        sub_cate_id, seat_num, park, mer_type, longitude, latitude,region_id+"")
                .compose(RxSchedulers.<BaseModel<LoginModel>>compose())
                .subscribe(new BaseObserver<LoginModel>() {
                    @Override
                    protected void onHandleSuccess(LoginModel loginModel, int code, String msg) {
                        showToast(msg);
                        PublicStaticData.isCanyin = true;
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.SUB_ID, loginModel.getCate_id() + "").commit();
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.ID, loginModel.getMer_id() + "").commit();
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.SUB_CATE_ID, loginModel.getSub_cate_id() + "").commit();
                        ActivityFactory.goMain(RegisterCanyinActivity.this, null);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }
}
