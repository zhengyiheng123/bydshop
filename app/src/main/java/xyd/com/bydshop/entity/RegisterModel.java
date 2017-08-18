package xyd.com.bydshop.entity;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/23
 * @time: 14:36
 * @description:
 */

public class RegisterModel implements Serializable {

    /**
     * email :
     * mobile : 15538869641
     * mer_id : 12
     * apitoken : 6935b0f98789e1cb7ba468b137977d43e36b8ff2
     */

    private String email;
    private String mobile;
    private String mer_id;
    private String apitoken;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMer_id() {
        return mer_id;
    }

    public void setMer_id(String mer_id) {
        this.mer_id = mer_id;
    }

    public String getApitoken() {
        return apitoken;
    }

    public void setApitoken(String apitoken) {
        this.apitoken = apitoken;
    }
}
