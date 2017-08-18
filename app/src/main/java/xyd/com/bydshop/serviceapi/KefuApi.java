package xyd.com.bydshop.serviceapi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.entity.KefuModel;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/10
 * @time: 11:27
 * @description:
 */

public interface KefuApi {

    @GET("system/customer_service")
    Observable<BaseModel<KefuModel>> customer_service(@Query("apitoken") String apitoken);
}
