package xyd.com.bydshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/28
 * @time: 9:48
 * @description:    商户导游圈
 */

public class GliderModel implements Serializable {

    private List<GliderBean>  users;
    public List<GliderBean> getUsers() {
        return users;
    }

    public void setUsers(List<GliderBean> users) {
        this.users = users;
    }

    public static class GliderBean{

        private int user_id;
        private String user_name;


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



    }
}
