package cn.jhc.startdemo.common.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class NewsEntity {


    /**
     * error : false
     * results : [{"_id":"59ac06be421aa901c85e6006","createdAt":"2017-09-03T21:42:22.920Z","desc":"如何自己实现一个 EventBus","publishedAt":"2017-09-05T11:29:05.240Z","source":"web","type":"Android","url":"https://github.com/Werb/EventBusKotlin/wiki/%E5%A6%82%E4%BD%95%E8%87%AA%E5%B7%B1%E5%AE%9E%E7%8E%B0%E4%B8%80%E4%B8%AA-EventBus","used":true,"who":"Werb"},{"_id":"59aca1b1421aa901bcb7dbf5","createdAt":"2017-09-04T08:43:29.888Z","desc":"自定义 View：用贝塞尔曲线绘制酷炫轮廓背景","publishedAt":"2017-09-05T11:29:05.240Z","source":"web","type":"Android","url":"http://mp.weixin.qq.com/s/SzZuiRMz8QWzNqCjq2gI_A","used":true,"who":null},{"_id":"59acce3b421aa901c1c0a8db","createdAt":"2017-09-04T11:53:31.833Z","desc":"Gradle 插件，做自动测试过程中的截屏。","images":["http://img.gank.io/95075433-5796-48e0-8310-6bd9132a4988"],"publishedAt":"2017-09-05T11:29:05.240Z","source":"chrome","type":"Android","url":"https://github.com/Karumi/Shot","used":true,"who":"代码家"},{"_id":"59ad7859421aa901bcb7dbfd","createdAt":"2017-09-04T23:59:21.438Z","desc":"Kotlin 一些常用的扩展函数集合","publishedAt":"2017-09-05T11:29:05.240Z","source":"web","type":"Android","url":"https://github.com/ravidsrk/kotlinextensions.com","used":true,"who":" Thunder Bouble"},{"_id":"59a65dd2421aa901c85e5fe9","createdAt":"2017-08-30T14:40:18.444Z","desc":"Android版ARKit","publishedAt":"2017-09-01T12:55:52.582Z","source":"web","type":"Android","url":"https://github.com/google-ar/arcore-android-sdk","used":true,"who":"JackHang"},{"_id":"59a79050421aa901bcb7dbd6","createdAt":"2017-08-31T12:28:00.485Z","desc":"How to create multiple apk files for android application","publishedAt":"2017-09-01T12:55:52.582Z","source":"web","type":"Android","url":"https://mindorks.com/blog/how-to-create-multiple-apk-files-for-android-application","used":true,"who":"AMIT SHEKHAR"},{"_id":"59a8b60a421aa901c1c0a8c2","createdAt":"2017-09-01T09:21:14.889Z","desc":"英语不好？打造一款AndroidStudio翻译插件","publishedAt":"2017-09-01T12:55:52.582Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247486724&idx=1&sn=5a66e01a2ecb72dbd97c619718d906ab","used":true,"who":"陈宇明"},{"_id":"59a621eb421aa901bcb7dbcb","createdAt":"2017-08-30T10:24:43.354Z","desc":"一个滑动关闭Activity的组件，可以设置上下左右滑动关闭","images":["http://img.gank.io/d6ce9191-6f9a-4aa5-96cd-b175fb8eef3e"],"publishedAt":"2017-08-31T08:22:07.982Z","source":"web","type":"Android","url":"https://github.com/gongwen/SwipeBackLayout","used":true,"who":"龚文"},{"_id":"59a64ec7421aa901c1c0a8ab","createdAt":"2017-08-30T13:36:07.559Z","desc":"腾讯团队开源的 致力于提高项目 UI 开发效率的解决方案","publishedAt":"2017-08-31T08:22:07.982Z","source":"chrome","type":"Android","url":"http://qmuiteam.com/android/page/index.html","used":true,"who":"代码家"},{"_id":"59a64ef7421aa901c85e5fe7","createdAt":"2017-08-30T13:36:55.542Z","desc":"Glide 图像变换功能库。","publishedAt":"2017-08-31T08:22:07.982Z","source":"chrome","type":"Android","url":"https://github.com/wasabeef/glide-transformations","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<NewsResultsEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<NewsResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<NewsResultsEntity> results) {
        this.results = results;
    }


}
