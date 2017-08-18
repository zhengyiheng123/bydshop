package xyd.com.bydshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

/**
 * Created by ${zxl} on 2017/4/5.
 * Describe:  viewpager 适配器
 * CHange:
 */

public class MyViewpagerAdapter extends FragmentStatePagerAdapter{
    private String [] tabTitles;
    private Fragment [] list;

    public MyViewpagerAdapter(FragmentManager fm, Fragment [] list, String [] tabTitles) {
        super(fm);
        this.list = list;
        this.tabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return list[position];
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }
}
