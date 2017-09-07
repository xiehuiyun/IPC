package cn.jhc.startdemo.common.base;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/9/5.
 */

public interface IView {
    /**
     * 打印日志
     *@param  TAG 日志标签
     * @param log 日志内容
     */
    void log(String TAG,String log);

    /**
     * 显示错误信息/提示/成功等等
     *
     * @param msg 错误信息
     */
    void showMessage(@NonNull String msg);

    /**
     * 显示加载对话框
     *
     * @param isShow 显示还是消失
     */
    void showLoadingIndicator(boolean isShow,String msg);
}
