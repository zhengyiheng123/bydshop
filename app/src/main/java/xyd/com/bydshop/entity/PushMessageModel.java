package xyd.com.bydshop.entity;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/15
 * @time: 10:53
 * @description:
 */

public class PushMessageModel {
    public List<NoticesBean> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticesBean> notices) {
        this.notices = notices;
    }

    private List<NoticesBean> notices;



    public static  class NoticesBean{
        public int getOpm_id() {
            return opm_id;
        }

        public void setOpm_id(int opm_id) {
            this.opm_id = opm_id;
        }

        public int getOrd_id() {
            return ord_id;
        }

        public void setOrd_id(int ord_id) {
            this.ord_id = ord_id;
        }

        public int getOrd_type() {
            return ord_type;
        }

        public void setOrd_type(int ord_type) {
            this.ord_type = ord_type;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        private int opm_id;
        private int ord_id;
        private int ord_type;
        private long add_time;
        private String title;
        private String msg;


    }
}
