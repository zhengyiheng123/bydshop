package xyd.com.bydshop.entity;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/9
 * @time: 16:54
 * @description:
 */

public class AddressModel {

    private List<RegionTreeBean> region_tree;

    public List<RegionTreeBean> getRegion_tree() {
        return region_tree;
    }

    public void setRegion_tree(List<RegionTreeBean> region_tree) {
        this.region_tree = region_tree;
    }

    public static class RegionTreeBean {
        /**
         * region_id : 2
         * region_name : 法国
         * region_pid : 0
         * is_leaf : false
         * nodes : [{"region_id":7,"region_name":"巴黎","region_pid":2,"is_leaf":true,"nodes":[]},{"region_id":10,"region_name":"里昂","region_pid":2,"is_leaf":true,"nodes":[]}]
         */

        private int region_id;
        private String region_name;
        private int region_pid;
        private boolean is_leaf;
        private List<NodesBean> nodes;

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public int getRegion_pid() {
            return region_pid;
        }

        public void setRegion_pid(int region_pid) {
            this.region_pid = region_pid;
        }

        public boolean isIs_leaf() {
            return is_leaf;
        }

        public void setIs_leaf(boolean is_leaf) {
            this.is_leaf = is_leaf;
        }

        public List<NodesBean> getNodes() {
            return nodes;
        }

        public void setNodes(List<NodesBean> nodes) {
            this.nodes = nodes;
        }

        public static class NodesBean {
            /**
             * region_id : 7
             * region_name : 巴黎
             * region_pid : 2
             * is_leaf : true
             * nodes : []
             */

            private int region_id;
            private String region_name;
            private int region_pid;
            private boolean is_leaf;
            private List<NodesBean1> nodes;

            public int getRegion_id() {
                return region_id;
            }

            public void setRegion_id(int region_id) {
                this.region_id = region_id;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public int getRegion_pid() {
                return region_pid;
            }

            public void setRegion_pid(int region_pid) {
                this.region_pid = region_pid;
            }

            public boolean isIs_leaf() {
                return is_leaf;
            }

            public void setIs_leaf(boolean is_leaf) {
                this.is_leaf = is_leaf;
            }

            public List<NodesBean1> getNodes() {
                return nodes;
            }

            public void setNodes(List<NodesBean1> nodes) {
                this.nodes = nodes;
            }

            public static class NodesBean1 {

                public int getRegion_id() {
                    return region_id;
                }

                public void setRegion_id(int region_id) {
                    this.region_id = region_id;
                }

                public String getRegion_name() {
                    return region_name;
                }

                public void setRegion_name(String region_name) {
                    this.region_name = region_name;
                }

                public int getRegion_pid() {
                    return region_pid;
                }

                public void setRegion_pid(int region_pid) {
                    this.region_pid = region_pid;
                }

                private int region_id;
                private String region_name;
                private int region_pid;
            }
        }
    }
}
