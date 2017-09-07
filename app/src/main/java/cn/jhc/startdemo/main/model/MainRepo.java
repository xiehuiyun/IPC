package cn.jhc.startdemo.main.model;

import android.support.annotation.NonNull;

import cn.jhc.startdemo.App;
import cn.jhc.startdemo.common.bean.NewsEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/9/5.
 */

public class MainRepo {
    private static APIService service;

    public MainRepo() {
        service = App.getRetrofit().create(APIService.class);
    }

    public Observable<NewsEntity> loadAPIData(String category, int count, int page) {
        return service.getNews(category, count, page);
    }

    /*
    @params category   请求的类型【Android、IOS】
    @params category   请求个数
    @params page       请求所在页
     */
    interface APIService {
        @NonNull
        @GET("/api/data/{category}/{count}/{page}")
        Observable<NewsEntity> getNews(@Path("category") String category, @Path("count") int count, @Path("page") int page);

    }
}
