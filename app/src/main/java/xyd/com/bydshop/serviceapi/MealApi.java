package xyd.com.bydshop.serviceapi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.entity.CanyinMealModel;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/27
 * @time: 15:43
 * @description:  商品部分
 */

public interface MealApi {
    /**
     * 获取商品列表
     * @param apitoken
     * @return
     */
    @GET("merchant/meal")
    Observable<BaseModel<CanyinMealModel>> meal(@Query("apitoken")String apitoken);
}
