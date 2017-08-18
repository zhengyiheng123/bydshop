package xyd.com.bydshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/28
 * @time: 9:19
 * @description: 订单详情
 */

public class OrderDetailModel implements Serializable {


    /**
     * ord_id : 5
     * user_name : Firelord_Sama
     * mobile : 15232394003
     * avatar : /uploads/user/avatar/20170629/60a3759d002fe681f7df72703605b4f8.jpg
     * meal_info : {"res_name":"把子肉","meal_name":"夏日海滨套餐","img_path":"/uploads/20170613/b5917324b92cef7d0060beb687efd7eb.jpg","meal_price":"100.00","mp_num":"12"}
     * price : 1200.00
     * group_num : GN111111
     * seat_cost : 12
     * message :
     * ord_num : DM80417179992141
     * add_time : 1497412102
     * book_time : 1782221225
     * ord_status : 3
     */

    private int ord_id;
    private String user_name;
    private String mobile;
    private String avatar;



    private GroupMealBean group_meal;
    private String price;
    private String group_num;
    private int seat_cost;
    private String message;
    private String ord_num;
    private int add_time;
    private int book_time;
    private int ord_status;
    private List<CanyinMealModel.SingleMealBean> single_meal;

    public List<CanyinMealModel.SingleMealBean> getSingle_meal() {
        return single_meal;
    }

    public void setSingle_meal(List<CanyinMealModel.SingleMealBean> single_meal) {
        this.single_meal = single_meal;
    }

    public GroupMealBean getGroup_meal() {
        return group_meal;
    }

    public void setGroup_meal(GroupMealBean group_meal) {
        this.group_meal = group_meal;
    }
    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    private int pay_type;

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

    public String getGroup_num() {
        return group_num;
    }

    public void setGroup_num(String group_num) {
        this.group_num = group_num;
    }

    public int getSeat_cost() {
        return seat_cost;
    }

    public void setSeat_cost(int seat_cost) {
        this.seat_cost = seat_cost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrd_num() {
        return ord_num;
    }

    public void setOrd_num(String ord_num) {
        this.ord_num = ord_num;
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

    public int getOrd_status() {
        return ord_status;
    }

    public void setOrd_status(int ord_status) {
        this.ord_status = ord_status;
    }

    public static class GroupMealBean {
        /**
         * res_name : 把子肉
         * meal_name : 夏日海滨套餐
         * img_path : /uploads/20170613/b5917324b92cef7d0060beb687efd7eb.jpg
         * meal_price : 100.00
         * mp_num : 12
         */


        private String meal_name;
        private String img_path;
        private String meal_price;

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        private String nums;


        public String getMeal_name() {
            return meal_name;
        }

        public void setMeal_name(String meal_name) {
            this.meal_name = meal_name;
        }

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public String getMeal_price() {
            return meal_price;
        }

        public void setMeal_price(String meal_price) {
            this.meal_price = meal_price;
        }



    }

    public static class SingleMealBean {
        private int meal_id;
        private String meal_name;
        private String img_path;

        public int getMeal_id() {
            return meal_id;
        }

        public void setMeal_id(int meal_id) {
            this.meal_id = meal_id;
        }

        public String getMeal_name() {
            return meal_name;
        }

        public void setMeal_name(String meal_name) {
            this.meal_name = meal_name;
        }

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public String getMeal_price() {
            return meal_price;
        }

        public void setMeal_price(String meal_price) {
            this.meal_price = meal_price;
        }

        public int getMeal_weight() {
            return meal_weight;
        }

        public void setMeal_weight(int meal_weight) {
            this.meal_weight = meal_weight;
        }

        public int getNums() {
            return nums;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }

        private String meal_price;
        private int meal_weight;
        private int nums;
    }
}
