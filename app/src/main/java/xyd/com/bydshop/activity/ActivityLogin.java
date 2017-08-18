package xyd.com.bydshop.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.android.data.JPushLocalNotification;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.MyApplication;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.entity.LoginModel;
import xyd.com.bydshop.entity.QuhaoEntity;
import xyd.com.bydshop.glide.GlideUtil;
import xyd.com.bydshop.promptdialog.PromptDialog;
import xyd.com.bydshop.serviceapi.MerchantApi;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.RegexUtils;
import xyd.com.bydshop.utils.StatusBarUtil;

/**
 * Created by ${zxl} on 2017/4/12.
 * D:登录界面
 * C:
 */

public class ActivityLogin extends BaseActivity {
    @Bind(R.id.login_ib_back)
    ImageButton loginIbBack;
    @Bind(R.id.login_edt_user)
    EditText loginEdtUser;
    @Bind(R.id.login_tv_quhao)
    TextView loginTvQuhao;
    @Bind(R.id.login_tv_pass)
    EditText loginTvPass;
    private PromptDialog waitDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        waitDialog = new PromptDialog(this);
//        普通景区:scenenormal@email.com
//        表演类:sceneshow@email.com
//        游船类:scenetrain@email.com
//        密码: test1234
        loginEdtUser.setText("scenetrain@email.com");   //游船类
       // loginEdtUser.setText("firelord@email.com");   //餐饮
       // loginEdtUser.setText("scenenormal@email.com");  //普通景区
        //loginEdtUser.setText("sceneshow@email.com");     //表演景区
        loginTvPass.setText("test1234");
//        JPushLocalNotification ln = new JPushLocalNotification();
//        ln.setBuilderId(1);
//        ln.setContent("aaaaaaaaaaaaa");
//        ln.setTitle("bbbbbbbbbbb");
//        ln.setNotificationId(1) ;
//        ln.setBroadcastTime(System.currentTimeMillis() );
//        JPushInterface.addLocalNotification(MyApplication.getContext(),ln);

    }

    @OnClick({R.id.login_ib_back, R.id.login_tv_quhao, R.id.login_tv_login, R.id.login_tv_forget, R.id.login_tv_register, R.id.login_iv_qq, R.id.login_iv_wx, R.id.login_iv_wb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_ib_back:
                //返回
                finish();
                break;
            case R.id.login_tv_quhao:

                break;
            case R.id.login_tv_login:
                //登录

                String user = loginEdtUser.getText().toString();
                String password = loginTvPass.getText().toString();
                if (TextUtils.isEmpty(user)) {
                    showToast("请输入账号");
                } else if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                } else if (!RegexUtils.isPWD(password)) {
                    showToast("请输入正确的密码");
                } else {
                    waitDialog.showLoading("正在登录", true);
                    login(user, password);
                }

                break;
            case R.id.login_tv_forget:
                //忘记密码
                ActivityFactory.goForget(this, null);
                break;
            case R.id.login_tv_register:
                //注册

                ActivityFactory.goRegister(this, null);
                break;
            case R.id.login_iv_qq:
                break;
            case R.id.login_iv_wx:
                break;
            case R.id.login_iv_wb:
                break;
        }
    }

    /**
     * 登录
     *
     * @param user
     * @param password
     */
    private void login(String user, String password) {
        BaseApi.getRetrofit()
                .create(MerchantApi.class)
                .login(user, password)
                .compose(RxSchedulers.<BaseModel<LoginModel>>compose())
                .subscribe(new BaseObserver<LoginModel>() {
                    @Override
                    protected void onHandleSuccess(LoginModel loginModel, int code, String msg) {
                        waitDialog.dismiss();
                        // 调用 Handler 来异步设置别名
                        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, "helpd_mer_" +loginModel.getMer_id()));
                     //   mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, "helpd_mer_" + loginModel.getMer_id()));
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.API_TOKEN, loginModel.getApitoken()).commit();
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.ID, loginModel.getMer_id() + "").commit();
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.SUB_ID, loginModel.getCate_id() + "").commit();
                        PublicStaticData.sharedPreferences.edit().putString("name", loginModel.getMer_name() + "").commit();
                        PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.AVATAR,PublicStaticData.URL+loginModel.getAvatar()).commit();

                        if (code == 1) {
                            if (loginModel.getCate_id() == 1 || loginModel.getCate_id() == 2) {
                                PublicStaticData.isCanyin = true;
                            } else if (loginModel.getCate_id() == 3) {
                                PublicStaticData.isCanyin = false;
                            }
                            //子级分类id 5|普通景区 6|演出类 7|火车游船
                            PublicStaticData.sharedPreferences.edit().putString(PublicStaticData.SUB_CATE_ID, loginModel.getSub_cate_id() + "").commit();
                            ActivityFactory.goMain(ActivityLogin.this, null);

                        } else if (code == 2) {
                            if (loginModel.getCate_id() == 1 || loginModel.getCate_id() == 2) {
                                ActivityFactory.goRegisterCanyin(ActivityLogin.this, null);
                                PublicStaticData.isCanyin = true;
                            } else if (loginModel.getCate_id() == 3) {
                                ActivityFactory.goRegisterJingdian(ActivityLogin.this, null);
                                PublicStaticData.isCanyin = false;
                            } else {
                                ActivityFactory.goRegisterType(ActivityLogin.this, null);
                            }
                        }

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        waitDialog.dismiss();
                        showToast(msg);
                    }
                });


    }

    @Override
    protected void setStatusBar() {

        StatusBarUtil.setTranslucent(this, 0);

    }

    @Subscribe
    public void onEvent(QuhaoEntity q) {
        loginTvQuhao.setText(q.getArea_code());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i("Login", logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i("Login", logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.i("Login", logs);
            }

        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d("Login", "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    Log.i("Login", "Unhandled msg - " + msg.what);
            }
        }
    };
}
