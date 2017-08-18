package xyd.com.bydshop.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseApi;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.BaseObserver;
import xyd.com.bydshop.R;
import xyd.com.bydshop.RxSchedulers;
import xyd.com.bydshop.entity.KefuModel;
import xyd.com.bydshop.runtimepermissions.PermissionUtils;
import xyd.com.bydshop.runtimepermissions.PermissionsManager;
import xyd.com.bydshop.serviceapi.KefuApi;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.ToastUtils;


/**
 * Created by ${zxl} on 2017/4/5.
 * Describe: 客服
 * CHange:
 */

public class KefuFragment extends Fragment {

    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.kefu_tv_dianhua)
    TextView kefuTvDianhua;
    @Bind(R.id.kefu_tv_dianhua2)
    TextView kefuTvDianhua2;
    @Bind(R.id.kefu_tv_dizhi)
    TextView kefuTvDizhi;
    @Bind(R.id.kefu_tv_shijian)
    TextView kefuTvShijian;
    @Bind(R.id.kefu_tv_youxiang)
    TextView kefuTvYouxiang;
    @Bind(R.id.kefu_tv_chuanzhen)
    TextView kefuTvChuanzhen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kefu, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    private void initView() {
        titleTvTitle.setVisibility(View.VISIBLE);
        titleTvTitle.setText("客服");
        getData();
    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(KefuApi.class)
                .customer_service(PublicStaticData.sharedPreferences.getString(PublicStaticData.API_TOKEN, ""))
                .compose(RxSchedulers.<BaseModel<KefuModel>>compose())
                .subscribe(new BaseObserver<KefuModel>() {
                    @Override
                    protected void onHandleSuccess(KefuModel kefuModel, int code, String msg) {
                        ToastUtils.showDebug(msg);
                        kefuTvDianhua.setText(kefuModel.getMobile());
                        kefuTvDianhua2.setText(kefuModel.getMobile_bak());
                        kefuTvChuanzhen.setText(kefuModel.getFax());
                        kefuTvDizhi.setText(kefuModel.getAddress());
                        kefuTvShijian.setText(kefuModel.getTime());
                        kefuTvYouxiang.setText(kefuModel.getEmail());
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.showDebug(msg);
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.kefu_tv_dianhua, R.id.kefu_tv_dianhua2, R.id.kefu_tv_dizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kefu_tv_dianhua:
                PermissionUtils.phone(getActivity(), new PermissionUtils.OnPermissionResult() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + kefuTvDianhua.getText().toString()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                break;
            case R.id.kefu_tv_dianhua2:
                PermissionUtils.phone(getActivity(), new PermissionUtils.OnPermissionResult() {
                    @Override
                    public void onGranted() {
                        Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + kefuTvDianhua2.getText().toString()));
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                    }
                });

                break;
            case R.id.kefu_tv_dizhi:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
}
