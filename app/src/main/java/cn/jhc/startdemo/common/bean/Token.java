package cn.jhc.startdemo.common.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yohom on 24/03/2017.
 */
public class Token {

    @SerializedName("sign")
    private String sign;
    @SerializedName("token")
    private String token;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "sign='" + sign + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
