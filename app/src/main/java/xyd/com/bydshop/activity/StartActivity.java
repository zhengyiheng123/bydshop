package xyd.com.bydshop.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xyd.com.bydshop.R;
import xyd.com.bydshop.utils.ActivityFactory;
import xyd.com.bydshop.utils.PublicStaticData;
import xyd.com.bydshop.utils.StatusBarUtil;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/18
 * @time: 9:09
 * @description:初始界面
 */

public class StartActivity extends Activity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean first = PublicStaticData.sharedPreferences.getBoolean("first", true);
        setContentView(R.layout.activity_start);
        viewPager = (ViewPager) findViewById(R.id.start_vp);
        if (first) {
            PublicStaticData.sharedPreferences.edit().putBoolean("first", false).commit();
            viewPager.setVisibility(View.VISIBLE);
            initView();
        } else {
            viewPager.setVisibility(View.GONE);
            Toast.makeText(StartActivity.this,"三秒关闭",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityFactory.goLogin(StartActivity.this,null);

                }
            },3000);
            PublicStaticData.sharedPreferences.edit().putBoolean("first", true).commit();

        }
    }

    private void initView() {

        final List<View> imageViews=new ArrayList<>();
        imageViews.add(LayoutInflater.from(this).inflate(R.layout.item_start,null));
        imageViews.add(LayoutInflater.from(this).inflate(R.layout.item_start,null));
        imageViews.add(LayoutInflater.from(this).inflate(R.layout.item_start,null));
        imageViews.add(LayoutInflater.from(this).inflate(R.layout.item_start,null));
        viewPager.setAdapter(new MyPagerAdapter(imageViews));
        imageViews.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (  viewPager.getCurrentItem()==3) {
                    Toast.makeText(StartActivity.this,"First",Toast.LENGTH_SHORT).show();
                    ActivityFactory.goLogin(StartActivity.this,null);
            }
            }
        });

    }

    private  class MyPagerAdapter extends PagerAdapter{
        List<View> views;
        public MyPagerAdapter(List<View> views){
            this.views=views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(views.get(position));

            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }
}
