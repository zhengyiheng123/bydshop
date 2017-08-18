package xyd.com.bydshop.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import xyd.com.bydshop.MainActivity;
import xyd.com.bydshop.activity.ActivityCanyinDetail;
import xyd.com.bydshop.activity.ActivityGuanyu;
import xyd.com.bydshop.activity.ActivityJingdianDetail;
import xyd.com.bydshop.activity.ActivityLiuyan;
import xyd.com.bydshop.activity.ActivityLogin;
import xyd.com.bydshop.activity.ActivityQuhao;
import xyd.com.bydshop.activity.ActivityQuxiao;
import xyd.com.bydshop.activity.ActivitySetting;
import xyd.com.bydshop.activity.ActivityTixing;
import xyd.com.bydshop.activity.ActivityTongzhi;
import xyd.com.bydshop.activity.ActivityYijian;
import xyd.com.bydshop.activity.ChangeOrderActivity;
import xyd.com.bydshop.activity.ForgetActivity;
import xyd.com.bydshop.activity.PingjiaActivity;
import xyd.com.bydshop.activity.QxYuanyinActivity;
import xyd.com.bydshop.activity.RegisterActivity;
import xyd.com.bydshop.activity.RegisterCanyinActivity;
import xyd.com.bydshop.activity.RegisterJingdianActivity;
import xyd.com.bydshop.activity.RiliActivity;
import xyd.com.bydshop.activity.YingyeTime1Activity;
import xyd.com.bydshop.activity.YingyeTimeActivity;
import xyd.com.bydshop.activity.YjJiludanActivity;
import xyd.com.bydshop.activity.ZiliaoCanyinActivity;
import xyd.com.bydshop.activity.ZiliaoJingdianActivity;

/**
 * Created by ${zxl} on 2017/4/10.
 * Describe:  界面跳转工厂
 * CHange:
 */

public class ActivityFactory {
    /**
     * 跳转到餐饮详情界面
     *
     * @param c
     * @param b
     */
    public static void goCanyiDetail(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityCanyinDetail.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到餐饮详情界面
     *
     * @param c
     * @param b
     */
    public static void goJingdianDetail(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityJingdianDetail.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到评价详情界面
     *
     * @param c
     * @param b
     */
    public static void goPingjia(Context c, Bundle b) {
        Intent intent = new Intent(c, PingjiaActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到留言界面
     *
     * @param c
     * @param b
     */
    public static void goLiuyan(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityLiuyan.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到关于帮预定
     *
     * @param c
     * @param b
     */
    public static void goGuanyu(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityGuanyu.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到取消订单界面
     *
     * @param c
     * @param b
     */
    public static void goQuxioa(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityQuxiao.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到设置界面
     *
     * @param c
     * @param b
     */
    public static void goSetting(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivitySetting.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到通知界面
     *
     * @param c
     * @param b
     */
    public static void goTongzhi(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityTongzhi.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到消息提醒通知界面
     *
     * @param c
     * @param b
     */
    public static void goTixing(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityTixing.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到意见反馈界面
     *
     * @param c
     * @param b
     */
    public static void goYijian(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityYijian.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到登录界面
     *
     * @param c
     * @param b
     */
    public static void goLogin(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityLogin.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到国际区号界面
     *
     * @param c
     * @param b
     */
    public static void goQuhao(Context c, Bundle b) {
        Intent intent = new Intent(c, ActivityQuhao.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到忘记密码界面
     *
     * @param c
     * @param b
     */
    public static void goForget(Context c, Bundle b) {
        Intent intent = new Intent(c, ForgetActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到注册界面
     *
     * @param c
     * @param b
     */
    public static void goRegister(Context c, Bundle b) {
        Intent intent = new Intent(c, RegisterActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到注册选择服务种类界面
     *
     * @param c
     * @param b
     */
    public static void goRegisterType(Context c, Bundle b) {
        Intent intent = new Intent(c, ChangeOrderActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到注册餐饮界面
     *
     * @param c
     * @param b
     */
    public static void goRegisterCanyin(Context c, Bundle b) {
        Intent intent = new Intent(c, RegisterCanyinActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到注册景点界面
     *
     * @param c
     * @param b
     */
    public static void goRegisterJingdian(Context c, Bundle b) {
        Intent intent = new Intent(c, RegisterJingdianActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到商家资料餐饮界面
     *
     * @param c
     * @param b
     */
    public static void goZiliaoCanyin(Context c, Bundle b) {
        Intent intent = new Intent(c, ZiliaoCanyinActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到商家资料景点界面
     *
     * @param c
     * @param b
     */
    public static void goZiliaoJingdian(Context c, Bundle b) {
        Intent intent = new Intent(c, ZiliaoJingdianActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到营业时间界面
     *
     * @param c
     * @param b
     */
    public static void goYingyeTime(Context c, Bundle b) {
        Intent intent = new Intent(c, YingyeTime1Activity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到取消原因界面
     *
     * @param c
     * @param b
     */
    public static void goQxYuanyin(Context c, Bundle b) {
        Intent intent = new Intent(c, QxYuanyinActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到主界面
     *
     * @param c
     * @param b
     */
    public static void goMain(Context c, Bundle b) {
        Intent intent = new Intent(c, MainActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }

    /**
     * 跳转到意见记录单
     *
     * @param c
     * @param b
     */
    public static void goJiludan(Context c, Bundle b) {
        Intent intent = new Intent(c, YjJiludanActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }
    /**
     * 跳转到行程日历
     *
     * @param c
     * @param b
     */
    public static void goRili(Context c, Bundle b) {
        Intent intent = new Intent(c, RiliActivity.class);
        if (b!=null)
            intent.putExtras(b);
        c.startActivity(intent);
    }
}
