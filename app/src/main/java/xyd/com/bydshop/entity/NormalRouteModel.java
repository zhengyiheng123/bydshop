package xyd.com.bydshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/6
 * @time: 10:34
 * @description:  普通景区路线图
 */

public class NormalRouteModel implements Serializable {

    public List<RoutesBean> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RoutesBean> routes) {
        this.routes = routes;
    }

    private List<RoutesBean> routes;


    public static class RoutesBean {
        private int route_id;
        private String route_name;
        private int status;
        private int group_start;

        public int getRoute_id() {
            return route_id;
        }

        public void setRoute_id(int route_id) {
            this.route_id = route_id;
        }

        public String getRoute_name() {
            return route_name;
        }

        public void setRoute_name(String route_name) {
            this.route_name = route_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getGroup_start() {
            return group_start;
        }

        public void setGroup_start(int group_start) {
            this.group_start = group_start;
        }

        public int getGuide_free() {
            return guide_free;
        }

        public void setGuide_free(int guide_free) {
            this.guide_free = guide_free;
        }

        public int getDriver_free() {
            return driver_free;
        }

        public void setDriver_free(int driver_free) {
            this.driver_free = driver_free;
        }

        private int guide_free;
        private int driver_free;
    }

}
