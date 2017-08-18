package xyd.com.bydshop;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/17
 * @time: 14:24
 * @description:
 */

public class BaseModel <T> implements Serializable{


public T getData() {
    return data;
}

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //private Boolean ReState = false;
    private T data;
    private int code;
    private String message;
}
