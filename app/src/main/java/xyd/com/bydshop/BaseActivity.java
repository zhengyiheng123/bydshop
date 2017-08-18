package xyd.com.bydshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.StatusBarUtil;

/**
 * Created by ${zxl} on 2017/4/5.
 * Describe:  基类
 * CHange:
 */

public abstract class BaseActivity extends AutoLayoutActivity {

    private boolean isTest = true;



    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showTestToast(String msg) {
        if (isTest) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        PublicStaticData.activityList.add(this);
        setStatusBar();
        ButterKnife.bind(this);
        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PublicStaticData.activityList.remove(this);
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.back_blue), 0);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();


}
