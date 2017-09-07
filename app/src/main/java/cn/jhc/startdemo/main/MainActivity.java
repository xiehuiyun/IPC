package cn.jhc.startdemo.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jhc.startdemo.R;
import cn.jhc.startdemo.aidl.AIDLActivity;
import cn.jhc.startdemo.common.base.MvpActivity;
import cn.jhc.startdemo.common.bean.NewsResultsEntity;
import cn.jhc.startdemo.main.presenter.MainPresenter;
import cn.jhc.startdemo.main.view.MainView;
import cn.jhc.startdemo.main.view.adapter.MainAdapter;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private MainAdapter adapter;
    private List<NewsResultsEntity> list;
    private int currentPage = 1;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this, this);
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        super.setUpView();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadAPIData(10, currentPage);
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new MainAdapter(list);
        recycler.setAdapter(adapter);
        presenter.loadAPIData(10, currentPage);
    }

    @Override
    public void fillDataToView(List<NewsResultsEntity> entity) {
        currentPage++;
        adapter.addData(entity);
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @OnClick(R.id.aidl_activity)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, AIDLActivity.class));

    }
}
