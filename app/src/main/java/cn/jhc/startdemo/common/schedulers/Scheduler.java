package cn.jhc.startdemo.common.schedulers;

import android.support.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/5.
 */

public class Scheduler implements BaseScheduler {

    @NonNull
    private static Scheduler INSTANCE;

    private Scheduler() {
    }

    public static Scheduler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Scheduler();
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public io.reactivex.Scheduler computation() {
        return Schedulers.computation();
    }

    @NonNull
    @Override
    public io.reactivex.Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public io.reactivex.Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
