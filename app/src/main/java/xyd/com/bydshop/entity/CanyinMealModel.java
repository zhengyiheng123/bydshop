package xyd.com.bydshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/27
 * @time: 15:53
 * @description: 餐饮商品列表
 */

public class CanyinMealModel implements Serializable {
    private String pic_meal;
    private List<GroupMealBean> group_meal;
    private List<SingleMealBean> single_meal;

    public String getPic_meal() {
        return pic_meal;
    }

    public void setPic_meal(String pic_meal) {
        this.pic_meal = pic_meal;
    }

    public List<GroupMealBean> getGroup_meal() {
        return group_meal;
    }

    public void setGroup_meal(List<GroupMealBean> group_meal) {
        this.group_meal = group_meal;
    }

    public List<SingleMealBean> getSingle_meal() {
        return single_meal;
    }

    public void setSingle_meal(List<SingleMealBean> single_meal) {
        this.single_meal = single_meal;
    }


    public static class GroupMealBean implements Serializable{
        private int meal_id;
        private String meal_name;
        private String img_path;
        private List<String> meal_price;

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

        public List<String> getMeal_price() {
            return meal_price;
        }

        public void setMeal_price(List<String> meal_price) {
            this.meal_price = meal_price;
        }

    }

    public static class SingleMealBean implements Serializable{
        private int meal_id;
        private int meal_weight;
        private String meal_name;
        private String img_path;
        private String meal_price;

        public int getNums() {
            return nums;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }

        private int nums;

        public int getMeal_id() {
            return meal_id;
        }

        public void setMeal_id(int meal_id) {
            this.meal_id = meal_id;
        }

        public int getMeal_weight() {
            return meal_weight;
        }

        public void setMeal_weight(int meal_weight) {
            this.meal_weight = meal_weight;
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


    }

}
