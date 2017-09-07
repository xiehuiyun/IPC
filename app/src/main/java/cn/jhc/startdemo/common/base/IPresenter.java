package cn.jhc.startdemo.common.base;

/**
 * Created by Administrator on 2017/9/5.
 */

public interface IPresenter<V,REPO> {
    void attach(V view,REPO repo);
    void detachView();
}
