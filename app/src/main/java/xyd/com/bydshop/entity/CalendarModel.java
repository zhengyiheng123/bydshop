package xyd.com.bydshop.entity;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/10
 * @time: 11:15
 * @description:
 */

public class CalendarModel {

    public List<Long> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<Long> calendar) {
        this.calendar = calendar;
    }

    private List<Long> calendar;
}
