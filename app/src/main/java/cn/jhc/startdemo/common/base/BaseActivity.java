package cn.jhc.startdemo.common.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import cn.jhc.startdemo.R;

/**
 * Created by Administrator on 2017/9/5.
 */

public abstract class BaseActivity extends AutoLayoutActivity {

    private ProgressDialog progress;
    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        ButterKnife.bind(this);

        progress = new ProgressDialog(this);
        progress.setCancelable(true);
        progress.setIndeterminate(true);//形成圆形转圈的progressDialog，而不是进度条

        initData();
        setUpView();
    }

    //获取根布局的ID
    protected abstract int getLayoutid();

    /**
     * 初始化，一些只需要在{@link android.app.Activity#onCreate(Bundle)}执行一次的代码，写在这里
     */
    protected void initData() {
    }

    /**
     * {@link #setContentView(int)}执行完后，可以在这里设置adapter，delegate，按钮事件等等，如果不用设置就不用重写
     */
    protected void setUpView() {
    }

    public void log(String TAG, String log) {
        Log.d(TAG, log);
    }

    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLoadingIndicator(boolean isShow, String msg) {
        if (isShow) {
            if (progress == null) {
                progress = new ProgressDialog(BaseActivity.this);
                progress.setCancelable(true);
                progress.setIndeterminate(true);//形成圆形转圈的progressDialog，而不是进度条
            }
            progress.setMessage(msg);
            progress.show();
        } else {
            if (progress != null) {
                progress.dismiss();
            }
        }
    }

    //跳转页面
    protected void moveToNextActivity(Class clazz, boolean isFinished) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (isFinished) {
            finish();
        }
    }

    //跳转页面，并且添加参数
    protected void moveToNextActivity(Class clazz, String name, Bundle bundle, boolean isFinished) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(name, bundle);
        startActivity(intent);
        if (isFinished) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void initActionBar(Toolbar toolbar, String title, boolean hasBackButton) {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if (hasBackButton) {
            actionBar = getSupportActionBar();
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
