package xyd.com.bydshop.entity;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/5/3
 * @time: 10:16
 * @description:  获取验证码
 */

public class CodeModel implements Serializable{
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    private String captcha;

}
