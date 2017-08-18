package xyd.com.bydshop.activity;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
import xyd.com.bydshop.utils.LogUtil;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.RegexUtils;
import xyd.com.bydshop.utils.StringUtils;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/27
 * @time: 13:53
 * @description:
 */

public class SaveZiliaoCyActivity extends BaseActivity {

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
    @Bind(R.id.rcv_tv_tese)
    TextView rcvTvTese;
    @Bind(R.id.rcv_tfl_tese)
    TagFlowLayout rcvTflTese;
    @Bind(R.id.rcv_edt_zuowei)
    EditText rcvEdtZuowei;
    @Bind(R.id.rcv_edt_car)
    EditText rcvEdtCar;
    @Bind(R.id.cv_tfl_car)
    TagFlowLayout cvTflCar;
    @Bind(R.id.rcv_rl_bouttom)
    RelativeLayout rcvRlBouttom;
    @Bind(R.id.rcv_ll_tese)
    LinearLayout rcv_ll_tese;
    @Bind(R.id.rcv_view_tese)
    View rcv_view_tese;
    private UserDataModel model;
    private List<String> teseString;
    private List<SubcategoryModel.SubCategoriesBean> tese;
    public static final String SAVE_USER = "save_user";
    private TagAdapter<String> teseAdapter;
    private String park;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ziliao_canyin;
    }

    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvBack.setVisibility(View.VISIBLE);
        titleTvRight.setVisibility(View.VISIBLE);
        rcvRlBouttom.setVisibility(View.GONE);
        titleTvRight.setText("保存");
        titleTvBack.setText("商家资料");
        rcvTvTese.setText("经营特色");
        rcvEdtCar.setVisibility(View.VISIBLE);
        rcv_ll_tese.setVisibility(View.GONE);
        rcv_view_tese.setVisibility(View.GONE);
        model = (UserDataModel) getIntent().getSerializableExtra(SAVE_USER);
        teseString = new ArrayList<>();
        tese = new ArrayList<>();
        setUserData();
        getSubCate();
        setCarTagFlowLayout();
        setTeseTagFlowLayout();
    }

    private void setUserData() {

        rcvEdtAddress.setText(model.getAddress());
        rcvEdtChuanzhen.setText(model.getFax());
        rcvEdtContact.setText(model.getContacts_name());
        rcvEdtEmail.setText(model.getEmail());
        rcvEdtName.setText(model.getMer_name());
        rcvEdtPhone1.setText(model.getMobile());
        rcvEdtPhone2.setText(model.getMobile_bak());
        rcvEdtWx.setText(model.getWechat_name());
        rcvEdtZuowei.setText(model.getSeat_num() + "");
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


    private void setCarTagFlowLayout() {
        // "大巴停车场", "停车场", "大巴临时上下客"
        String[] car = new String[]{"大巴停车场", "停车场", "大巴临时上下客"};
        TagAdapter<String> carAdapter = new TagAdapter<String>(car) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv;
                tv = (TextView) LayoutInflater.from(SaveZiliaoCyActivity.this).inflate(R.layout.tag_change_order, parent, false);
                tv.setText(s);
                return tv;
            }
        };
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
    }

    private void setTeseTagFlowLayout() {

        teseAdapter = new TagAdapter<String>(teseString) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SaveZiliaoCyActivity.this).inflate(R.layout.tag_change_order, parent, false);
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

                String mer_name = rcvEdtName.getText().toString();
                String contacts_name = rcvEdtContact.getText().toString();
                String mobile = rcvEdtPhone1.getText().toString();
                String mobile_bak = rcvEdtPhone2.getText().toString();
                String email = rcvEdtEmail.getText().toString();
                String wechat_name = rcvEdtWx.getText().toString();
                String fax = rcvEdtChuanzhen.getText().toString();
                String address = rcvEdtAddress.getText().toString();
                String seat_num = rcvEdtZuowei.getText().toString();
                String longitude = 12.00+"";
                String latitude =  12.00+"";
                if (TextUtils.isEmpty(mer_name)) {
                    showToast("请输入商家名称");
                } else if (TextUtils.isEmpty(contacts_name)) {
                    showToast("请输入联系人姓名");
                } else if (!RegexUtils.isMobilePhoneNumber(mobile)) {
                    showToast("请输入正确的手机号");
                } else if (TextUtils.isEmpty(address)) {
                    showToast("请输入餐厅详细地址");
                } else if(TextUtils.isEmpty(park)){
                    showToast("请选择附近停车场");
                }else {
                    commit(mer_name,contacts_name,mobile,mobile_bak,email,wechat_name,fax,address,seat_num,park,longitude,latitude);
                }


                break;
        }
    }

    /**
     * 提交用户信息
     * @param mer_name
     * @param contacts_name
     * @param mobile
     * @param mobile_bak
     * @param email
     * @param wechat_name
     * @param fax
     * @param address
     * @param seat_num
     * @param park
     * @param longitude
     * @param latitude
     */
    private void commit(String mer_name, String contacts_name, String mobile, String mobile_bak, String email, String wechat_name, final String fax,
                        String address, String seat_num, String park, String longitude, String latitude) {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .saveUser(PublicStaticData.sharedPreferences.getString(PublicStaticData.ID, ""),
                        PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""),
                        mer_name, contacts_name, mobile, mobile_bak, email, wechat_name, fax, address, seat_num, park, longitude, latitude,"")
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
