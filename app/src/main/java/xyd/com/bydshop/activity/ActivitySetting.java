package xyd.com.bydshop.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyd.com.bydshop.BaseActivity;
import xyd.com.bydshop.R;
import xyd.com.bydshop.customview.DingdanDialog;
import xyd.com.bydshop.customview.LianxiPop;
import xyd.com.bydshop.runtimepermissions.PermissionUtils;
import xyd.com.bydshop.runtimepermissions.PermissionsManager;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.DataCleanManager;
import xyd.com.bydshop.version.VersionUpdateHelper;

/**
 * Created by ${zxl} on 2017/4/10.
 * D:设置界面
 * C:
 */

public class ActivitySetting extends BaseActivity {
    @Bind(R.id.title_tv_title)
    TextView titleTvTitle;
    @Bind(R.id.title_iv_back)
    ImageButton titleIvBack;
    private String cacheSize;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        titleIvBack.setVisibility(View.VISIBLE);
        titleTvTitle.setVisibility(View.VISIBLE);
        titleTvTitle.setText("设置");
        try {
            cacheSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
            cacheSize = new Random().nextInt(20000)+"kb";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.title_iv_back, R.id.setting_tv_xiaoxi, R.id.setting_tv_huancun, R.id.setting_tv_jiance, R.id.setting_tv_guanyu, R.id.setting_tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
            case R.id.setting_tv_xiaoxi:
                //消息提醒设置
                ActivityFactory.goTixing(this, null);
                break;
            case R.id.setting_tv_huancun:
                //清除缓存

                DingdanDialog dialog = new DingdanDialog(ActivitySetting.this, "确定要清理缓存~" + cacheSize) {
                    @Override
                    public void call(boolean b) {
                        if (b) {
                            DataCleanManager.cleanApplicationData(getApplicationContext());
                            try {
                                cacheSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                dialog.show();
                break;
            case R.id.setting_tv_jiance:
                //检测新版本  读写权限
//                PermissionUtils.storage(this, new PermissionUtils.OnPermissionResult() {
//                    @Override
//                    public void onGranted() {
//                        VersionUpdateHelper helper = new VersionUpdateHelper(ActivitySetting.this);
//                        helper.setShowDialogOnStart(true);
//                        VersionUpdateHelper.resetCancelFlag();
//                        helper.startUpdateVersion();
//                    }
//                });


                 showToast("暂无新版本");
                break;
            case R.id.setting_tv_guanyu:
                //关于帮预定
                ActivityFactory.goGuanyu(this, null);
                break;
            case R.id.setting_tv_exit:
                //退出
                ActivityFactory.goLogin(this, null);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
}
