package xyd.com.bydshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/6
 * @time: 10:38
 * @description: 表演类景区演出列表
 */

public class ShowRouteModel implements Serializable {
    private List<RoutesBean> routes;

    public List<RoutesBean> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RoutesBean> routes) {
        this.routes = routes;
    }

    public static class RoutesBean {
        private int route_id;
        private String route_name;
        private int status;
        private long start_time;
        private long end_time;
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

        public long getStart_time() {
            return start_time;
        }

        public void setStart_time(long start_time) {
            this.start_time = start_time;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
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
