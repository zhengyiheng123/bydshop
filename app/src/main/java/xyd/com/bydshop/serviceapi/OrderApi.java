package xyd.com.bydshop.serviceapi;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.entity.CriticizeModel;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.entity.GliderModel;
import xyd.com.bydshop.entity.OrderDetailModel;
import xyd.com.bydshop.entity.OrderModel;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/28
 * @time: 9:17
 * @description:
 */

public interface OrderApi {
    /**
     * 订单详情
     *
     * @param ord_id
     * @param apitoken
     * @return
     */
    @GET("merchant/res_order/{ord_id}")
    Observable<BaseModel<OrderDetailModel>> orderDetail(@Path("ord_id") String ord_id,
                                                        @Query("apitoken") String apitoken);

    /**
     * 商家接受订单
     *
     * @param apitoken
     * @param ord_id
     * @return
     */
    @POST("merchant/res_order/accept")
    Observable<BaseModel<EmptyModel>> acceptOrder(@Query("apitoken") String apitoken,
                                                  @Query("ord_id") String ord_id);

    /**
     * 保存商家取消订单内容
     *
     * @param apitoken
     * @param ord_id
     * @param cancel_content
     * @return
     */
    @POST("merchant/res_order/refuse")
    Observable<BaseModel<EmptyModel>> qxOrder(@Query("apitoken") String apitoken,
                                              @Query("ord_id") String ord_id,
                                              @Query("cancel_content") String cancel_content);


    /**
     * 商家确认消费
     *
     * @param apitoken
     * @param ord_id
     * @return
     */
    @POST("merchant/res_order/confirm")
    Observable<BaseModel<EmptyModel>> confirmOrder(@Query("apitoken") String apitoken,
                                                   @Query("ord_id") String ord_id);
    /**
     * 商家确认退款
     *
     * @param apitoken
     * @param ord_id
     * @return
     */
    @POST("merchant/res_order/refund")
    Observable<BaseModel<EmptyModel>> refund(@Query("apitoken") String apitoken,
                                                   @Query("ord_id") String ord_id);


    /**
     * 商家评价导游详情
     *
     * @param apitoken
     * @param ord_id
     * @return
     */
    @GET("merchant/res_order/criticize")
    Observable<BaseModel<CriticizeModel>> criticize(@Query("apitoken") String apitoken,
                                                    @Query("ord_id") String ord_id);


    /**
     * @param apitoken
     * @param ord_id
     * @param rank     评价: -1|拉黑 0|未设置 1|一般 2|可信任
     * @return
     */
    @POST("merchant/res_order/criticize")
    Observable<BaseModel<EmptyModel>> saveCriticize(@Query("apitoken") String apitoken,
                                                    @Query("ord_id") String ord_id,
                                                    @Query("rank") String rank);

    /**
     * 餐饮商户订单列表
     *
     * @param apitoken
     * @param p
     * @param ord_status
     * @param user_id
     * @param start_time
     * @param end_time
     * @return
     */
    @GET("merchant/res_order")
    Observable<BaseModel<OrderModel>> order(@Query("apitoken") String apitoken,
                                            @Query("p") String p,
                                            @Query("ord_status") String ord_status,
                                            @Query("user_id") String user_id,
                                            @Query("start_time") String start_time,
                                            @Query("end_time") String end_time);

    /**
     * 商户导游圈列表
     *
     * @param apitoken
     * @return
     */
    @GET("merchant/set")
    Observable<BaseModel<GliderModel>> set(@Query("apitoken") String apitoken);

    /**
     * 先联系 留言
     *
     * @param apitoken
     * @return
     */
    @POST("merchant/res_order/msg")
    Observable<BaseModel<EmptyModel>> msg(@Query("apitoken") String apitoken,
                                          @Query("msg") String msg,
                                          @Query("ord_id") String ord_id);
}
