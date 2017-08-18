package xyd.com.bydshop.serviceapi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.entity.CriticizeModel;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.entity.NormalDetailModel;
import xyd.com.bydshop.entity.NormalRouteModel;
import xyd.com.bydshop.entity.NormalSceneModel;
import xyd.com.bydshop.entity.RoutesModel;
import xyd.com.bydshop.entity.ShowDetailModel;
import xyd.com.bydshop.entity.TrainDetailModel;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/6
 * @time: 10:10
 * @description:
 */

public interface JingdianOrderApi {

    /**
     * 普通景区列表
     *
     * @param apitoken
     * @param p
     * @param ord_status
     * @param user_id
     * @param start_time
     * @param end_time
     * @return
     */
    @GET("merchant/scene/normal/order")
    Observable<BaseModel<NormalSceneModel>> normalOrder(@Query("apitoken") String apitoken,
                                                        @Query("p") String p,
                                                        @Query("ord_status") String ord_status,
                                                        @Query("user_id") String user_id,
                                                        @Query("start_time") String start_time,
                                                        @Query("end_time") String end_time);

    /**
     * 普通景区订单详情
     *
     * @param id
     * @param apitoken
     * @return
     */
    @GET("merchant/scene/normal/order/{id}")
    Observable<BaseModel<NormalDetailModel>> normalOrderDetail(@Path("id") String id,
                                                               @Query("apitoken") String apitoken);


    /**
     * 表演景区列表
     *
     * @param apitoken
     * @param p
     * @param ord_status
     * @param user_id
     * @param start_time
     * @param end_time
     * @return
     */
    @GET("merchant/scene/show/order")
    Observable<BaseModel<NormalSceneModel>> showOrder(@Query("apitoken") String apitoken,
                                                      @Query("p") String p,
                                                      @Query("ord_status") String ord_status,
                                                      @Query("user_id") String user_id,
                                                      @Query("start_time") String start_time,
                                                      @Query("end_time") String end_time);
    /**
     * 游船景区列表
     *
     * @param apitoken
     * @param p
     * @param ord_status
     * @param user_id
     * @param start_time
     * @param end_time
     * @return
     */
    @GET("merchant/scene/train/order")
    Observable<BaseModel<NormalSceneModel>> trainOrder(@Query("apitoken") String apitoken,
                                                       @Query("p") String p,
                                                       @Query("ord_status") String ord_status,
                                                       @Query("user_id") String user_id,
                                                       @Query("start_time") String start_time,
                                                       @Query("end_time") String end_time);


    /**
     * 游船景区订单详情
     *
     * @param id
     * @param apitoken
     * @return
     */
    @GET("merchant/scene/normal/order/{id}")
    Observable<BaseModel<TrainDetailModel>> trainOrderDetail(@Path("id") String id,
                                                               @Query("apitoken") String apitoken);

    /**
     * 商家确认消费
     *
     * @param apitoken
     * @param ord_id
     * @return
     */
    @POST("merchant/scene/order/confirm")
    Observable<BaseModel<EmptyModel>> confirmOrder(@Query("apitoken") String apitoken,
                                                   @Query("ord_id") String ord_id);

    /**
     * 商家确认退款
     *
     * @param apitoken
     * @param ord_id
     * @return
     */
    @POST("merchant/scene/order/refund")
    Observable<BaseModel<EmptyModel>> refund(@Query("apitoken") String apitoken,
                                             @Query("ord_id") String ord_id);
    /**
     * 保存商户取消订单内容
     *
     * @param apitoken
     * @param ord_id
     * @param cancel_content
     * @return
     */
    @POST("merchant/scene/order/refuse")
    Observable<BaseModel<EmptyModel>> refuse(@Query("apitoken") String apitoken,
                                             @Query("ord_id") String ord_id,
                                             @Query("cancel_content") String cancel_content);

    /**
     * 商家接受订单
     *
     * @param apitoken
     * @param ord_id
     * @return
     */
    @POST("merchant/scene/order/accept")
    Observable<BaseModel<EmptyModel>> accept(@Query("apitoken") String apitoken,
                                             @Query("ord_id") String ord_id);


    /**
     * 商家评价导游详情
     *
     * @param apitoken
     * @param ord_id
     * @return
     */
    @GET("merchant/scene/order/criticize")
    Observable<BaseModel<CriticizeModel>> criticize(@Query("apitoken") String apitoken,
                                                    @Query("ord_id") String ord_id);


    /**
     * 表演景区订单详情
     *
     * @param id
     * @param apitoken
     * @return
     */
    @GET("merchant/scene/show/order/{id}")
    Observable<BaseModel<ShowDetailModel>> showOrderDetail(@Path("id") String id,
                                                           @Query("apitoken") String apitoken);

    /**
     * @param apitoken
     * @param ord_id
     * @param rank     评价: -1|拉黑 0|未设置 1|一般 2|可信任
     * @return
     */
    @POST("merchant/scene/order/criticize")
    Observable<BaseModel<EmptyModel>> saveCriticize(@Query("apitoken") String apitoken,
                                                    @Query("ord_id") String ord_id,
                                                    @Query("rank") String rank);


    /**
     * 普通景区线路图
     *
     * @param apitoken
     */
    @GET("merchant/scene/normal/route")
    Observable<BaseModel<RoutesModel>> normalRoute(@Query("apitoken") String apitoken);


    /**
     * 表演景区表演图
     *
     * @param apitoken
     */
    @GET("merchant/scene/show/route")
    Observable<BaseModel<RoutesModel>> showlRoute(@Query("apitoken") String apitoken);

    /**
     * 游船景区表演图
     *
     * @param apitoken
     */
    @GET("merchant/scene/train/route")
    Observable<BaseModel<RoutesModel>> trainlRoute(@Query("apitoken") String apitoken);
    /**
     * 先联系 留言
     *
     * @param apitoken
     * @return
     */
    @POST("merchant/scene/order/msg")
    Observable<BaseModel<EmptyModel>> msg(@Query("apitoken") String apitoken,
                                          @Query("msg") String msg,
                                          @Query("ord_id") String ord_id);
}
