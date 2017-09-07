package cn.jhc.startdemo.common.schedulers;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/9/5.
 */

public interface BaseScheduler {

    @NonNull
    io.reactivex.Scheduler computation();

    @NonNull
    io.reactivex.Scheduler io();

    @NonNull
    io.reactivex.Scheduler ui();
}
