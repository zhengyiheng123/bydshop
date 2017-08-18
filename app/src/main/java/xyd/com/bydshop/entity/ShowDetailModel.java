package xyd.com.bydshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/6
 * @time: 15:42
 * @description:  表演类景区
 */

public class ShowDetailModel implements Serializable {


    /**
     * ord_id : 99
     * group_num : DS10840329
     * ord_num : SN9592959444
     * price : 2572.00
     * pay_type : 2
     * post_type : 1
     * user_name : yasumo koutaro
     * mobile : 1560515256
     * region_name_list : 安土城
     * avatar : /uploads/user/avatar/20170629/60a3759d002fe681f7df72703605b4f8.jpg
     * address : 光明中学
     * ord_status : 0
     * add_time : 1499156925
     * book_time : 1499644800
     * merchant : {"mer_name":"Little Witch Academy","route_name":"交响情人梦","logo_path":"","address":"trigger","mobile":"15605153256"}
     * group_ticket : [{"ticket_id":5,"ticket_name":"一等票","tickets":[{"tp_id":5,"ticket_type":"1-100岁","price":940,"nums":5}]},{"ticket_id":6,"ticket_name":"二等票","tickets":[{"tp_id":10,"ticket_type":"1-12岁","price":180,"nums":2},{"tp_id":12,"ticket_type":"19-100岁","price":684,"nums":3}]},{"ticket_id":7,"ticket_name":"三等票","tickets":[{"tp_id":6,"ticket_type":"1-18岁","price":768,"nums":6}]}]
     * normal_ticket : [{"ticket_id":5,"ticket_name":"一等票","tickets":[{"tp_id":5,"ticket_type":"1-100岁","price":940,"nums":5}]},{"ticket_id":6,"ticket_name":"二等票","tickets":[{"tp_id":10,"ticket_type":"1-12岁","price":180,"nums":2},{"tp_id":12,"ticket_type":"19-100岁","price":684,"nums":3}]},{"ticket_id":7,"ticket_name":"三等票","tickets":[{"tp_id":6,"ticket_type":"1-18岁","price":768,"nums":6}]}]
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
    private int book_time;
    private MerchantBean merchant;
    private List<GroupTicketBean> group_ticket;
    private List<NormalTicketBean> normal_ticket;

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

    public int getBook_time() {
        return book_time;
    }

    public void setBook_time(int book_time) {
        this.book_time = book_time;
    }

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public List<GroupTicketBean> getGroup_ticket() {
        return group_ticket;
    }

    public void setGroup_ticket(List<GroupTicketBean> group_ticket) {
        this.group_ticket = group_ticket;
    }

    public List<NormalTicketBean> getNormal_ticket() {
        return normal_ticket;
    }

    public void setNormal_ticket(List<NormalTicketBean> normal_ticket) {
        this.normal_ticket = normal_ticket;
    }

    public static class MerchantBean {
        /**
         * mer_name : Little Witch Academy
         * route_name : 交响情人梦
         * logo_path :
         * address : trigger
         * mobile : 15605153256
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

    public static class GroupTicketBean {
        /**
         * ticket_id : 5
         * ticket_name : 一等票
         * tickets : [{"tp_id":5,"ticket_type":"1-100岁","price":940,"nums":5}]
         */

        private int ticket_id;
        private String ticket_name;
        private List<TicketsBean> tickets;

        public int getTicket_id() {
            return ticket_id;
        }

        public void setTicket_id(int ticket_id) {
            this.ticket_id = ticket_id;
        }

        public String getTicket_name() {
            return ticket_name;
        }

        public void setTicket_name(String ticket_name) {
            this.ticket_name = ticket_name;
        }

        public List<TicketsBean> getTickets() {
            return tickets;
        }

        public void setTickets(List<TicketsBean> tickets) {
            this.tickets = tickets;
        }

        public static class TicketsBean {
            /**
             * tp_id : 5
             * ticket_type : 1-100岁
             * price : 940
             * nums : 5
             */

            private int tp_id;
            private String ticket_type;
            private int price;
            private int nums;

            public int getTp_id() {
                return tp_id;
            }

            public void setTp_id(int tp_id) {
                this.tp_id = tp_id;
            }

            public String getTicket_type() {
                return ticket_type;
            }

            public void setTicket_type(String ticket_type) {
                this.ticket_type = ticket_type;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getNums() {
                return nums;
            }

            public void setNums(int nums) {
                this.nums = nums;
            }
        }
    }

    public static class NormalTicketBean {
        /**
         * ticket_id : 5
         * ticket_name : 一等票
         * tickets : [{"tp_id":5,"ticket_type":"1-100岁","price":940,"nums":5}]
         */

        private int ticket_id;
        private String ticket_name;
        private List<TicketsBeanX> tickets;

        public int getTicket_id() {
            return ticket_id;
        }

        public void setTicket_id(int ticket_id) {
            this.ticket_id = ticket_id;
        }

        public String getTicket_name() {
            return ticket_name;
        }

        public void setTicket_name(String ticket_name) {
            this.ticket_name = ticket_name;
        }

        public List<TicketsBeanX> getTickets() {
            return tickets;
        }

        public void setTickets(List<TicketsBeanX> tickets) {
            this.tickets = tickets;
        }

        public static class TicketsBeanX {
            /**
             * tp_id : 5
             * ticket_type : 1-100岁
             * price : 940
             * nums : 5
             */

            private int tp_id;
            private String ticket_type;
            private int price;
            private int nums;

            public int getTp_id() {
                return tp_id;
            }

            public void setTp_id(int tp_id) {
                this.tp_id = tp_id;
            }

            public String getTicket_type() {
                return ticket_type;
            }

            public void setTicket_type(String ticket_type) {
                this.ticket_type = ticket_type;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getNums() {
                return nums;
            }

            public void setNums(int nums) {
                this.nums = nums;
            }
        }
    }
}
