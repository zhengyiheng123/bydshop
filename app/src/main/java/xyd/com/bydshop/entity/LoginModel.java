package xyd.com.bydshop.entity;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/5/3
 * @time: 9:56
 * @description:
 */

public class LoginModel implements Serializable{


    /**
     * mer_id : 12
     * mer_name : 老赵小卖部
     * mobile : 15538869641
     * email : 595757504@qq.com
     * cate_id : 1
     * sub_cate_id : 9
     * apitoken : 73fa63336af4478550a0029df825e67e20e8b9fb
     */

    private int mer_id;
    private String mer_name;
    private String mobile;
    private String email;
    private int cate_id;
    private int sub_cate_id;
    private String apitoken;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public int getMer_id() {
        return mer_id;
    }

    public void setMer_id(int mer_id) {
        this.mer_id = mer_id;
    }

    public String getMer_name() {
        return mer_name;
    }

    public void setMer_name(String mer_name) {
        this.mer_name = mer_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public int getSub_cate_id() {
        return sub_cate_id;
    }

    public void setSub_cate_id(int sub_cate_id) {
        this.sub_cate_id = sub_cate_id;
    }

    public String getApitoken() {
        return apitoken;
    }

    public void setApitoken(String apitoken) {
        this.apitoken = apitoken;
    }
}
