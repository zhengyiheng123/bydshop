package xyd.com.bydshop.entity;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/10
 * @time: 11:29
 * @description:
 */

public class KefuModel implements Serializable {
    private String mobile;
    private String mobile_bak;
    private String address;
    private String time;
    private String email;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile_bak() {
        return mobile_bak;
    }

    public void setMobile_bak(String mobile_bak) {
        this.mobile_bak = mobile_bak;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    private String fax;

}
