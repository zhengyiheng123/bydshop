package xyd.com.bydshop.entity;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/8
 * @time: 16:43
 * @description:
 */

public class TimeModel {

    /**
     * time_list : [{"mt_id":1,"day_type":1,"start_am":"09:00","end_am":"12:00","start_pm":"13:00","end_pm":"20:01"},{"mt_id":2,"day_type":2,"start_am":"00:00","end_am":"00:00","start_pm":"13:00","end_pm":"16:00"}]
     * time_desc : fzfj
     */

    private String time_desc;
    private List<TimeListBean> time_list;

    public String getTime_desc() {
        return time_desc;
    }

    public void setTime_desc(String time_desc) {
        this.time_desc = time_desc;
    }

    public List<TimeListBean> getTime_list() {
        return time_list;
    }

    public void setTime_list(List<TimeListBean> time_list) {
        this.time_list = time_list;
    }

    public static class TimeListBean {
        /**
         * mt_id : 1
         * day_type : 1
         * start_am : 09:00
         * end_am : 12:00
         * start_pm : 13:00
         * end_pm : 20:01
         */

        private int mt_id;
        private int day_type;
        private String start_am;
        private String end_am;
        private String start_pm;
        private String end_pm;

        public int getMt_id() {
            return mt_id;
        }

        public void setMt_id(int mt_id) {
            this.mt_id = mt_id;
        }

        public int getDay_type() {
            return day_type;
        }

        public void setDay_type(int day_type) {
            this.day_type = day_type;
        }

        public String getStart_am() {
            return start_am;
        }

        public void setStart_am(String start_am) {
            this.start_am = start_am;
        }

        public String getEnd_am() {
            return end_am;
        }

        public void setEnd_am(String end_am) {
            this.end_am = end_am;
        }

        public String getStart_pm() {
            return start_pm;
        }

        public void setStart_pm(String start_pm) {
            this.start_pm = start_pm;
        }

        public String getEnd_pm() {
            return end_pm;
        }

        public void setEnd_pm(String end_pm) {
            this.end_pm = end_pm;
        }
    }
}
