package xyd.com.bydshop.entity;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/26
 * @time: 15:21
 * @description:
 */

public class AvatarModel implements Serializable {
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    String avatar;
}
