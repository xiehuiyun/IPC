package cn.jhc.startdemo.main.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import cn.jhc.startdemo.common.base.BasePresenter;
import cn.jhc.startdemo.common.bean.NewsEntity;
import cn.jhc.startdemo.common.rx.transformer.UpdateUIScheduler;
import cn.jhc.startdemo.main.model.MainRepo;
import cn.jhc.startdemo.main.view.MainView;

/**
 * Created by Administrator on 2017/9/5.
 */

public class MainPresenter extends BasePresenter<MainView, MainRepo> {

    private Context context;

    public MainPresenter(Context context, MainView view) {
        attach(view, new MainRepo());
        this.context = context;
    }

    public void loadAPIData(int count, int page) {
        view.showLoadingIndicator(true,"正在请求。。。");
        repo.loadAPIData("Android", count, page)
                .compose(new UpdateUIScheduler<NewsEntity>())
                .subscribe(new AbstractObserver<NewsEntity>() {
                    @Override
                    public void onNext(@NonNull NewsEntity newsEntity) {
                        super.onNext(newsEntity);
                        if (newsEntity!=null){
                            view.fillDataToView(newsEntity.getResults());
                        }else {
                            view.showMessage("请求返回的结果为空。");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        view.showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.showLoadingIndicator(false,"");
                    }
                });
    }
}
