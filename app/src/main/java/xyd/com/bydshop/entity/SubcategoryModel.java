package xyd.com.bydshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/23
 * @time: 15:40
 * @description:   获取子级列表分类
 */

public class SubcategoryModel implements Serializable {


    private List<SubCategoriesBean> sub_categories;

    public List<SubCategoriesBean> getSub_categories() {
        return sub_categories;
    }

    public void setSub_categories(List<SubCategoriesBean> sub_categories) {
        this.sub_categories = sub_categories;
    }

    public static class SubCategoriesBean {
        /**
         * sub_cate_id : 10
         * cate_name : 湘菜
         */

        private int sub_cate_id;
        private String cate_name;

        public int getSub_cate_id() {
            return sub_cate_id;
        }

        public void setSub_cate_id(int sub_cate_id) {
            this.sub_cate_id = sub_cate_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
