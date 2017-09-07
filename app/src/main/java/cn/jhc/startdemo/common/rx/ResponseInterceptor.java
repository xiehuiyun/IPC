package cn.jhc.startdemo.common.rx;

import cn.jhc.startdemo.common.bean.ResultEntity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/9/5.
 */

public class ResponseInterceptor<T> implements Function<ResultEntity<T>, Observable<T>> {

    @Override
    public Observable<T> apply(final ResultEntity<T> tBean) throws Exception {
        if (tBean.getId() <= 0) {
            return Observable.error(new Throwable(tBean.getMsg()));
        } else {
            return Observable.create(new ObservableOnSubscribe<T>() {
                @Override
                public void subscribe(ObservableEmitter<T> e) throws Exception {
                    e.onNext(tBean.getData());
                    e.onComplete();
                }
            });
        }
    }
}
