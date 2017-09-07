package cn.jhc.startdemo.common.base;

import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/9/5.
 */

public class BasePresenter<V extends IView, REPO> implements IPresenter<V, REPO> {

    protected V view;
    protected REPO repo;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void attach(V view, REPO repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void detachView() {
        this.view = null;
        compositeDisposable.clear();
    }

    /**
     * 预定义的Observer，定义了{@link #onError(Throwable)}、{@link #onComplete()}消息对应的处理逻辑;
     * 在BasePresenter的子类中只需定义{@link #onNext(Object)}
     *
     * @param <T> 目标处理的数据类型
     */
    protected abstract class AbstractObserver<T> implements Observer<T> {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            compositeDisposable.add(d);
        }

        @Override
        public void onNext(@NonNull T t) {
            if (t.toString().isEmpty()) {
                Log.d("BasePresenter", "onNext: 返回值为空。");
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            processError(e);
        }

        @Override
        public void onComplete() {
            view.showLoadingIndicator(false, "");
        }
    }



    private void processError(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
            view.showMessage("服务器没有响应，请稍后再试。。。");
        } else if (e instanceof UnknownHostException) {
            view.showMessage("网络连接失败，请检查网络设置。。。");
        } else if (e instanceof ConnectException) {
            view.showMessage("网络连接异常。。。");
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int responseCode = httpException.code();
            if (responseCode >= 400 && responseCode <= 417) {
                view.showMessage("访问地址异常，请稍后再试。。。");
            } else if (responseCode >= 500 && responseCode <= 505) {
                view.showMessage("服务器繁忙。。。");
            } else {
                view.showMessage("网络连接异常。。。");
            }
        } else {
            view.showMessage(e.getMessage());
        }
        view.showLoadingIndicator(false, "");
    }
}

