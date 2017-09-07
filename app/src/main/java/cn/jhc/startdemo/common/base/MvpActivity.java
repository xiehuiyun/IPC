package cn.jhc.startdemo.common.base;

/**
 * Created by Administrator on 2017/9/5.
 */

public abstract class MvpActivity<T extends BasePresenter> extends BaseActivity {

    protected T presenter;

    protected abstract T createPresenter();

    @Override
    protected void initData() {
        super.initData();
        presenter = createPresenter();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
