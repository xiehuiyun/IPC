package cn.jhc.startdemo.common.rx.transformer;

import cn.jhc.startdemo.common.schedulers.BaseScheduler;
import cn.jhc.startdemo.common.schedulers.Scheduler;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * Created by Administrator on 2017/9/5.
 */

public class UpdateUIScheduler<T> implements ObservableTransformer<T, T> {

    private BaseScheduler baseScheduler;

    public UpdateUIScheduler() {
        baseScheduler = Scheduler.getInstance();
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .subscribeOn(baseScheduler.io())
                .observeOn(baseScheduler.ui());
    }
}
