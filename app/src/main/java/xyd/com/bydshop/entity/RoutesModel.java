package xyd.com.bydshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/10
 * @time: 9:20
 * @description: 景区商户线路，门票
 */

public class RoutesModel implements Serializable {

    public List<RoutesBean> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RoutesBean> routes) {
        this.routes = routes;
    }


    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    private String logo_path;
    private List<RoutesBean> routes;

    public static class RoutesBean {

        private int route_id;

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

        public double getGroup_start() {
            return group_start;
        }

        public void setGroup_start(double group_start) {
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

        private String route_name;
        private int status;
        private long start_time;
        private long end_time;
        private double group_start;
        private int guide_free;
        private int driver_free;

    }


}
