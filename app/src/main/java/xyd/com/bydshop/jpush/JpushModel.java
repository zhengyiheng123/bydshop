package xyd.com.bydshop.jpush;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/8
 * @time: 14:22
 * @description:
 */

public class JpushModel {

    /**
     * user_id : 34
     * title : 订单已取消
     * ord_status : -2
     * ord_type : 1
     * msg : 订单 RS7177565741 已被用户 18518158917 已取消
     * to_user_id : 9
     * ord_id : 56
     * add_time : 1502172269
     */

    private int user_id;
    private String title;
    private int ord_status;
    private int ord_type;
    private String msg;
    private int to_user_id;
    private int ord_id;
    private int add_time;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrd_status() {
        return ord_status;
    }

    public void setOrd_status(int ord_status) {
        this.ord_status = ord_status;
    }

    public int getOrd_type() {
        return ord_type;
    }

    public void setOrd_type(int ord_type) {
        this.ord_type = ord_type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public int getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(int ord_id) {
        this.ord_id = ord_id;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }
}
