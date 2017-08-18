package xyd.com.bydshop.entity;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/6
 * @time: 10:30
 * @description:
 */

public class TrainDetailModel {

    /**
     * ord_id : 62
     * group_num : SN19304339338
     * ord_num : SN0486004393
     * price : 2268.00
     * pay_type : 1
     * post_type : 1
     * user_name : 张亮亮
     * mobile : 18319282931
     * region_name_list : 永恒之井
     * avatar : /uploads/user/avatar/20170704/10fd5f358ae5104c2edc1378baec2655.jpg
     * address : 北京市昌平区
     * ord_status : 1
     * add_time : 1498813106
     * merchant : {"mer_name":"天空之城(代理商)","route_name":"线路一","logo_path":"","address":"艾泽拉斯大陆","mobile":"12938293812"}
     * group_ticket : [{"tp_id":8,"ticket_type":"1-18岁","price":576,"nums":"2"},{"tp_id":9,"ticket_type":"18-100岁","price":1692,"nums":"9"}]
     * normal_ticket : [{"tp_id":9,"ticket_type":"18-100岁","price":1692,"nums":"9"}]
     */

    private int ord_id;
    private String group_num;
    private String ord_num;
    private String price;
    private int pay_type;
    private int post_type;
    private String user_name;
    private String mobile;
    private String region_name_list;
    private String avatar;
    private String address;
    private int ord_status;
    private int add_time;
    private MerchantBean merchant;
    private TicketBean ticket ;
    public TicketBean getTicketBean() {
        return ticket;
    }

    public void setTicketBean(TicketBean ticketBean) {
        this.ticket = ticketBean;
    }




    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int rank;

    public int getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(int ord_id) {
        this.ord_id = ord_id;
    }

    public String getGroup_num() {
        return group_num;
    }

    public void setGroup_num(String group_num) {
        this.group_num = group_num;
    }

    public String getOrd_num() {
        return ord_num;
    }

    public void setOrd_num(String ord_num) {
        this.ord_num = ord_num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getPost_type() {
        return post_type;
    }

    public void setPost_type(int post_type) {
        this.post_type = post_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegion_name_list() {
        return region_name_list;
    }

    public void setRegion_name_list(String region_name_list) {
        this.region_name_list = region_name_list;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrd_status() {
        return ord_status;
    }

    public void setOrd_status(int ord_status) {
        this.ord_status = ord_status;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }


    public static class MerchantBean {
        /**
         * mer_name : 天空之城(代理商)
         * route_name : 线路一
         * logo_path :
         * address : 艾泽拉斯大陆
         * mobile : 12938293812
         */

        private String mer_name;
        private String route_name;
        private String logo_path;
        private String address;
        private String mobile;

        public String getMer_name() {
            return mer_name;
        }

        public void setMer_name(String mer_name) {
            this.mer_name = mer_name;
        }

        public String getRoute_name() {
            return route_name;
        }

        public void setRoute_name(String route_name) {
            this.route_name = route_name;
        }

        public String getLogo_path() {
            return logo_path;
        }

        public void setLogo_path(String logo_path) {
            this.logo_path = logo_path;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

    public static class TicketBean {
        private int ticket_type;

        public int getTicket_type() {
            return ticket_type;
        }

        public void setTicket_type(int ticket_type) {
            this.ticket_type = ticket_type;
        }

        public String getStart_station() {
            return start_station;
        }

        public void setStart_station(String start_station) {
            this.start_station = start_station;
        }

        public String getEnd_station() {
            return end_station;
        }

        public void setEnd_station(String end_station) {
            this.end_station = end_station;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime_start() {
            return time_start;
        }

        public void setTime_start(String time_start) {
            this.time_start = time_start;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public String getTime_spend() {
            return time_spend;
        }

        public void setTime_spend(String time_spend) {
            this.time_spend = time_spend;
        }

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        private String start_station;
        private String end_station;
        private String date;
        private String time_start;
        private String time_end;
        private String time_spend;
        private String nums;

    }
}
