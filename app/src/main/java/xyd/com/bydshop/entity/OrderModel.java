package xyd.com.bydshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/28
 * @time: 9:43
 * @description:  订单列表
 */

public class OrderModel implements Serializable {


    private List<OrdersBean> orders;

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public static class OrdersBean {
        /**
         * ord_id : 6
         * book_time : 1782221228
         * pay_type : 2
         * user_name : Firelord_Sama
         * rank : 2
         * mobile : 15232394003
         * avatar : /uploads/user/avatar/20170627/ba51ad53a29498340ea4e55ab780292e.png
         * seat_cost : 12
         * price : 1200.00
         * ord_status : -1
         */

        private int ord_id;
        private int book_time;
        private int pay_type;
        private String user_name;
        private int rank;
        private String mobile;
        private String avatar;
        private int seat_cost;
        private String price;
        private int ord_status;
        private  String meal_name;
        public String getMeal_name() {
            return meal_name;
        }

        public void setMeal_name(String meal_name) {
            this.meal_name = meal_name;
        }



        public int getOrd_id() {
            return ord_id;
        }

        public void setOrd_id(int ord_id) {
            this.ord_id = ord_id;
        }

        public int getBook_time() {
            return book_time;
        }

        public void setBook_time(int book_time) {
            this.book_time = book_time;
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

        public int getSeat_cost() {
            return seat_cost;
        }

        public void setSeat_cost(int seat_cost) {
            this.seat_cost = seat_cost;
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
    }
}
