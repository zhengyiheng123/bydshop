package xyd.com.bydshop.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxl on 2017/4/10.
 * 保存静态数据
 */

public class PublicStaticData {
    public static SharedPreferences sharedPreferences;
    public static boolean isCanyin=true;
    public static final String API_TOKEN="apiToken";
    public static final String ID="id";
    /**
     *顶级分类id 1|中餐 2|特色餐 3|景点娱乐
     */
    public static final String SUB_ID="sub_id";
    /**
     * 子级分类id 5|普通景区 6|演出类 7|火车游船
     */
    public static final String SUB_CATE_ID="sub_cate_id";
    public static final String AVATAR="avatar";
    public static final  String URL="http://helpd.firelord.xin";

    public static List<Activity> activityList=new ArrayList<>();
    public static void closeAllActivity(){
        for (Activity activity: activityList){
            if (!activity.isFinishing())
                activity.finish();
        }
    }
}
