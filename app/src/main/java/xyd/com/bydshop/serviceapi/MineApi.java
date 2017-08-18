package xyd.com.bydshop.serviceapi;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.entity.AvatarModel;
import xyd.com.bydshop.entity.CalendarModel;
import xyd.com.bydshop.entity.CancelConditionModel;
import xyd.com.bydshop.entity.EmptyModel;
import xyd.com.bydshop.entity.IntroductionModel;
import xyd.com.bydshop.entity.JiludanModel;
import xyd.com.bydshop.entity.PushMessageModel;
import xyd.com.bydshop.entity.TimeModel;
import xyd.com.bydshop.entity.UserDataModel;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/26
 * @time: 15:19
 * @description: 个人中心
 */

public interface MineApi {
    /**
     * 修改头像
     *
     * @param body
     * @return
     */
    @POST("merchant/avatar")
    Observable<BaseModel<AvatarModel>> avatar(@Body RequestBody body);


    /**
     * 获取个人信息
     *
     * @param apitoken
     * @return
     */
    @GET("merchant/{id}/edit")
    Observable<BaseModel<UserDataModel>> editUser(
            @Path("id")  String id,
            @Query("apitoken") String apitoken);

    /**
     *
     * @param id
     * @param apitoken        身份验证token
     * @param mer_name      商家名称
     * @param contacts_name    联系人姓名
     * @param mobile              电话1
     * @param mobile_bak        电话2
     * @param email          邮箱地址
     * @param wechat_name    微信号
     * @param fax         传真
     * @param address     详细地址
     * @param seat_num     座位数
     * @param park         停车场类型(多选,逗号分隔): 1|大巴停车场,2|停车场,3|大巴临时上长下客
     * @param longitude   经度
     * @param latitude    纬度
     * @return
     */
    @PUT("merchant/{id}")
    Observable<BaseModel<AvatarModel>> saveUser(
            @Path("id") String id,
            @Query("apitoken") String apitoken,
            @Query("mer_name") String mer_name,
            @Query("contacts_name") String contacts_name,
            @Query("mobile") String mobile,
            @Query("mobile_bak") String mobile_bak,
            @Query("email") String email,
            @Query("wechat_name") String wechat_name,
            @Query("fax") String fax,
            @Query("address") String address,
            @Query("seat_num") String seat_num,
            @Query("park") String park,
            @Query("longitude") String longitude,
            @Query("latitude") String latitude,
            @Query("mer_type") String mer_type
    );

    /**
     * 获取营业时间
     *
     * @param apitoken
     * @return
     */
    @GET("merchant/mer_time/edit")
    Observable<BaseModel<TimeModel>> time(@Query("apitoken") String apitoken);

    /**
     * 更新营业时间
     * @param apitoken
     * @param time_list
     * @param time_desc
     * @return
     */
    @PUT("merchant/mer_time/update")
    Observable<BaseModel<EmptyModel>> updateTime(@Query("apitoken") String apitoken,
                                                 @Query("time_list") String time_list,
                                                 @Query("time_desc") String time_desc);

    /**
     * 获取订单日历
     *
     * @param apitoken
     * @return
     */
    @GET("merchant/res_order/calendar")
    Observable<BaseModel<CalendarModel>> calendar(@Query("apitoken") String apitoken);

    /**
     * 获取推送消息列表
     *
     * @param apitoken
     * @return
     */
    @GET("merchant/notice")
    Observable<BaseModel<PushMessageModel>> notice(@Query("apitoken") String apitoken,
                                                   @Query("p")int p);
    /**
     * canyin获取意见记录单
     *
     * @param apitoken
     * @return
     */
    @GET("merchant/res_comment")
    Observable<BaseModel<JiludanModel>> res_comment(@Query("apitoken") String apitoken,
                                                    @Query("p") int p);
    /**
     * jingdian获取意见记录单
     *
     * @param apitoken
     * @return
     */
    @GET("merchant/scnene/comment")
    Observable<BaseModel<JiludanModel>> scnene_comment(@Query("apitoken") String apitoken,
                                                    @Query("p") int p);



    /**
     * 取消条件
     *
     * @param apitoken
     * @return
     */
    @GET("merchant/cancel_condition")
    Observable<BaseModel<CancelConditionModel>> cancel_condition(@Query("apitoken") String apitoken);

    /**
     * 景区订票说明
     *
     * @param apitoken
     * @return
     */
    @GET("merchant/introduction")
    Observable<BaseModel<IntroductionModel>> introduction(@Query("apitoken") String apitoken);
    /**
     * 更新取消条件
     *
     * @param apitoken
     * @return
     */
    @POST("merchant/cancel_condition")
    Observable<BaseModel<EmptyModel>> cancel_condition(@Query("apitoken") String apitoken,
                                                                 @Query("cancel_condition")String cancel_condition);
    /**
     * 更新景区订票说明
     *
     * @param apitoken
     * @return
     */
    @POST("merchant/introduction")
    Observable<BaseModel<EmptyModel>> introduction(@Query("apitoken") String apitoken,
                                                          @Query("introduction")String introduction);
    /**
     * 意见反馈
     *
     * @param apitoken
     * @return
     */
    @POST("merchant/suggestion")
    Observable<BaseModel<EmptyModel>> suggestion(@Query("apitoken") String apitoken,
                                                          @Query("content")String content);


}
