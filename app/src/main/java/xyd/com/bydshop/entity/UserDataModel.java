package xyd.com.bydshop.entity;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/26
 * @time: 15:39
 * @description:  个人信息
 */

public class UserDataModel implements Serializable {


    /**
     * mer_id : 12
     * mer_name : 老赵小卖部
     * contacts_name : 老赵
     * mobile : 15538869641
     * mobile_bak : 18612602404
     * email : 595757504@qq.com
     * fax : 595757504
     * address : 东京
     * cate_id : 1
     * sub_cate_id : 9
     * sub_cate_name : 粤菜
     * seat_num : 50
     * park : 1,2
     */

    private int mer_id;
    private String mer_name;
    private String contacts_name;
    private String mobile;
    private String mobile_bak;
    private String email;
    private String fax;
    private String address;
    private int cate_id;
    private int sub_cate_id;
    private String sub_cate_name;
    private int seat_num;
    private String park;

    public int getMer_type() {
        return mer_type;
    }

    public void setMer_type(int mer_type) {
        this.mer_type = mer_type;
    }

    private int mer_type;

    public String getWechat_name() {
        return wechat_name;
    }

    public void setWechat_name(String wechat_name) {
        this.wechat_name = wechat_name;
    }

    private String wechat_name;

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

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSub_cate_name() {
        return sub_cate_name;
    }

    public void setSub_cate_name(String sub_cate_name) {
        this.sub_cate_name = sub_cate_name;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }
}
