package xyd.com.bydshop.serviceapi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import xyd.com.bydshop.BaseModel;
import xyd.com.bydshop.entity.AddressModel;
import xyd.com.bydshop.entity.CodeModel;
import xyd.com.bydshop.entity.LoginModel;
import xyd.com.bydshop.entity.RegisterModel;
import xyd.com.bydshop.entity.SubcategoryModel;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/23
 * @time: 15:14
 * @description: 登陆注册部分接口
 */

public interface MerchantApi {
    /**
     * 获取验证码
     *
     * @param reg_type  用户注册类型: 1|手机号注册,2|邮箱注册
     * @param mobile    手机号
     * @param area_code 国际区号
     * @param email     邮箱
     * @return
     */
    @POST("merchant/captcha")
    Observable<BaseModel<CodeModel>> getcaptcha(@Query("reg_type") String reg_type,
                                                @Query("mobile") String mobile,
                                                @Query("area_code") String area_code,
                                                @Query("email") String email);

    /**
     * 注册
     *
     * @param reg_type  用户注册类型: 1|手机号注册,2|邮箱注册
     * @param mobile    手机号
     * @param area_code 国际区号
     * @param email     邮箱
     * @param password  密码: 6-20位的大小写英文字母和数字组合,不可以是纯数字或英文
     * @param captcha   验证码: 四位数字
     * @return
     */
    @POST("merchant/register")
    Observable<BaseModel<RegisterModel>> register(@Query("reg_type") String reg_type,
                                                  @Query("mobile") String mobile,
                                                  @Query("area_code") String area_code,
                                                  @Query("email") String email,
                                                  @Query("password") String password,
                                                  @Query("captcha") String captcha);

    /**
     * 忘记密码
     *
     * @param reg_type  用户注册类型: 1|手机号注册,2|邮箱注册
     * @param mobile    手机号
     * @param area_code 国际区号
     * @param email     邮箱
     * @param password  密码: 6-20位的大小写英文字母和数字组合,不可以是纯数字或英文
     * @param captcha   验证码: 四位数字
     * @return
     */
    @POST("merchant/forget")
    Observable<BaseModel<RegisterModel>> forgetPassword(@Query("reg_type") String reg_type,
                                                        @Query("mobile") String mobile,
                                                        @Query("area_code") String area_code,
                                                        @Query("email") String email,
                                                        @Query("password") String password,
                                                        @Query("captcha") String captcha);

    /**
     * 登陆
     *
     * @param user_name 帐号: 注册手机号或邮箱地址
     * @param password  用户密码
     * @return
     */
    @POST("merchant/login")
    Observable<BaseModel<LoginModel>> login(@Query("user_name") String user_name,
                                            @Query("password") String password);

    /**
     * 获取子级分类列表
     *
     * @param cate_id
     * @param apitoken
     * @return
     */
    @GET("merchant/subcategory")
    Observable<BaseModel<SubcategoryModel>> subcategory(
            @Query("apitoken") String apitoken,
            @Query("cate_id") String cate_id);

    /**
     * 保存商户注册详情
     *
     * @param apitoken      身份验证token
     * @param mer_name      商户名称
     * @param contacts_name 联系人姓名
     * @param mobile        手机号
     * @param mobile_bak    备用手机号
     * @param email         邮箱地址
     * @param wechat_name   微信号
     * @param fax           传真
     * @param cate_id       顶级分类id 1|中餐 2|特色餐 3|景区门票 4|车行
     * @param address       详细地址
     * @param sub_cate_id   子级栏目id
     * @param seat_num      座位数量，cate_id为1或2时必须
     * @param park          停车场类型(多选,逗号分隔): 1|大巴停车场,2|停车场,3|大巴临时上长下客
     * @param mer_type      商家性质: 1|商户拥有者: 2|代理商  cate_id=3时必须,
     * @param longitude     经度
     * @param latitude      纬度
     * @return
     */
    @POST("merchant")
    Observable<BaseModel<LoginModel>> merchant(@Query("apitoken") String apitoken,
                                               @Query("mer_name") String mer_name,
                                               @Query("contacts_name") String contacts_name,
                                               @Query("mobile") String mobile,
                                               @Query("mobile_bak") String mobile_bak,
                                               @Query("email") String email,
                                               @Query("wechat_name") String wechat_name,
                                               @Query("fax") String fax,

                                               @Query("cate_id") String cate_id,
                                               @Query("address") String address,
                                               @Query("sub_cate_id") String sub_cate_id,
                                               @Query("seat_num") String seat_num,
                                               @Query("park") String park,
                                               @Query("mer_type") String mer_type,
                                               @Query("longitude") String longitude,
                                               @Query("latitude") String latitude,
                                               @Query("region_id") String region_id);


    /**
     * 获取地区列表
     *
     * @param apitoken 帐号: 注册手机号或邮箱地址
     * @return
     */
    @GET("merchant/region_tree")
    Observable<BaseModel<AddressModel>> region_tree(@Query("apitoken") String apitoken);

}
