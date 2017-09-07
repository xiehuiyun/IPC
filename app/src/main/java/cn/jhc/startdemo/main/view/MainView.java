package cn.jhc.startdemo.main.view;

import java.util.List;

import cn.jhc.startdemo.common.base.IView;
import cn.jhc.startdemo.common.bean.NewsResultsEntity;

/**
 * Created by Administrator on 2017/9/5.
 */

public interface MainView extends IView{
    void fillDataToView(List<NewsResultsEntity> entity);
}
