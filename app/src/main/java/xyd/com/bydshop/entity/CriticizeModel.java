package xyd.com.bydshop.entity;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/28
 * @time: 9:34
 * @description: 商家评价导游详情
 */

public class CriticizeModel implements Serializable {

    private int user_id;
    private String user_name;
    private String avatar;
    private int rank;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

