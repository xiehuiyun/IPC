package cn.jhc.startdemo.common.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yohom on 08/11/2016.
 * 基础实体类，泛型{@link T}定义了内容类型
 */
public class ResultEntity<T> {

    @SerializedName("id")
    private int id;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private T data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
