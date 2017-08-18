package xyd.com.bydshop.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.entity.CodeModel;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.entity.QuhaoEntity;
import xyd.com.bydshop.entity.RegisterModel;
import xyd.com.bydshop.serviceapi.MerchantApi;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.RegexUtils;

/**
 * Created by ${zxl} on 2017/4/12.
 * D: 密码找回密码
 * C:
 */

public class ForgetEmailFragment extends Fragment {
    @Bind(R.id.edt_phone)
    EditText edtPhone;
    @Bind(R.id.tv_quhao)
    TextView tvQuhao;
    @Bind(R.id.edt_email)
    EditText edtEmail;
    @Bind(R.id.edt_yanzhenma)
    EditText edtYanzhenma;
    @Bind(R.id.tv_get_yanzhengma)
    TextView tvGetYanzhengma;
    @Bind(R.id.edt_password)
    EditText edtPassword;
    @Bind(R.id.edt_password_sure)
    EditText edtPasswordSure;
    @Bind(R.id.fg_forget_tv_next)
    TextView fgForgetTvNext;
    @Bind(R.id.forget_ll)
    LinearLayout forgetLl;
    private int downTime = 60;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        forgetLl.setVisibility(View.GONE);
        edtEmail.setVisibility(View.VISIBLE);

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_get_yanzhengma, R.id.fg_forget_tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_yanzhengma:
                if (downTime == 60) {
                    if (RegexUtils.isEmail(edtPhone.getText().toString())) {
                        getCode(edtPhone.getText().toString());

                        handler.postDelayed(runnable, 1000);
                    } else {
                        showToast("请输入正确的邮箱号");
                    }
                }


                break;
            case R.id.fg_forget_tv_next:
                String email = edtPhone.getText().toString();
                String code = edtYanzhenma.getText().toString();
                String password = edtPassword.getText().toString();
                String ok = edtPasswordSure.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    showToast("请输入邮箱");
                } else if (TextUtils.isEmpty(code)) {
                    showToast("请输入验证码");
                } else if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                } else if (TextUtils.isEmpty(ok)) {
                    showToast("请确认密码");
                } else if (!RegexUtils.isEmail(email)) {
                    showToast("请输入正确的邮箱号");
                } else if (!RegexUtils.isPWD(password)) {
                    showToast("密码必须是包含字母和数字");
                } else if (!password.equals(ok)) {
                    showToast("请确认密码");
                } else {
                    registerNext(email, code, password);
                }
                break;
        }
    }

    /**
     * 获取验证码
     *
     * @param email
     */
    private void getCode(String email) {
        BaseApi.getRetrofit()
                .create(MerchantApi.class)
                .getcaptcha("2", "", "", email)
                .compose(RxSchedulers.<BaseModel<CodeModel>>compose())
                .subscribe(new BaseObserver<CodeModel>() {
                    @Override
                    protected void onHandleSuccess(CodeModel codeModel, int code, String msg) {
                        showToast(codeModel.getCaptcha());
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    /**
     * @param email
     * @param code
     * @param password
     */
    private void registerNext(String email, String code, String password) {
        BaseApi.getRetrofit()
                .create(MerchantApi.class)
                .forgetPassword("2", "", "", email, password, code)
                .compose(RxSchedulers.<BaseModel<RegisterModel>>compose())
                .subscribe(new BaseObserver<RegisterModel>() {
                    @Override
                    protected void onHandleSuccess(RegisterModel registerModel, int code, String msg) {
                        showToast(msg);
                        ActivityFactory.goLogin(getActivity(), null);

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (downTime > 0) {
                downTime--;
                tvGetYanzhengma.setText("还剩" + downTime + "秒");
                handler.postDelayed(this, 1000);
            } else {
                tvGetYanzhengma.setText("重新获取验证码");
                downTime = 60;
            }
        }
    };

    @Subscribe
    public void onEvent(QuhaoEntity q) {
        tvQuhao.setText(q.getArea_code());
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
