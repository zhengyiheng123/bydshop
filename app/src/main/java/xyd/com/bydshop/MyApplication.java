package xyd.com.bydshop;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import cn.jpush.android.api.JPushInterface;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.Utils;

/**
 * Created by ${zxl} on 2017/4/6.
 * Describe:
 * CHange:
 */

public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        Utils.init(this);
        PublicStaticData.sharedPreferences = getSharedPreferences("byd", MODE_PRIVATE);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
    public  static Context getContext(){
        return context;
    }
}
