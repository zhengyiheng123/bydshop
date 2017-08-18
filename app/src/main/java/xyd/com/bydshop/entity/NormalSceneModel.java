package xyd.com.bydshop.entity;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/6
 * @time: 10:14
 * @description:  景点列表
 */

public class NormalSceneModel {

    private List<OrdersBean> orders;

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public static class OrdersBean {
        /**
         * ord_id : 62
         * ord_num : SN0486004393
         * add_time : 1498813106
         * pay_type : 1
         * user_name : 张亮亮
         * rank : 1
         * mobile : 18319282931
         * avatar : /uploads/user/avatar/20170704/10fd5f358ae5104c2edc1378baec2655.jpg
         * price : 2268.00
         * ord_status : 1
         * route_name : 线路一
         */

        private int ord_id;
        private String ord_num;
        private long add_time;
        private int pay_type;
        private String user_name;
        private int rank;
        private String mobile;
        private String avatar;
        private String price;
        private int ord_status;
        private String route_name;

        public long getBook_time() {
            return book_time;
        }

        public void setBook_time(long book_time) {
            this.book_time = book_time;
        }

        private  long book_time;

        public int getOrd_id() {
            return ord_id;
        }

        public void setOrd_id(int ord_id) {
            this.ord_id = ord_id;
        }

        public String getOrd_num() {
            return ord_num;
        }

        public void setOrd_num(String ord_num) {
            this.ord_num = ord_num;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getOrd_status() {
            return ord_status;
        }

        public void setOrd_status(int ord_status) {
            this.ord_status = ord_status;
        }

        public String getRoute_name() {
            return route_name;
        }

        public void setRoute_name(String route_name) {
            this.route_name = route_name;
        }
    }
}
