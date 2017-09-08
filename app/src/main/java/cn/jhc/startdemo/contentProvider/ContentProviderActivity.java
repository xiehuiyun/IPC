package cn.jhc.startdemo.contentProvider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jhc.startdemo.R;
import cn.jhc.startdemo.aidl.Book;
import cn.jhc.startdemo.common.base.BaseActivity;

public class ContentProviderActivity extends BaseActivity {

    @Bind(R.id.show_result)
    TextView showResult;
    private Uri bookUri;
    private Uri userUri;
    private StringBuilder builder;


    @Override
    protected int getLayoutid() {
        return R.layout.activity_content_provider;
    }

    @Override
    protected void initData() {
        super.initData();
//        Uri uri = Uri.parse("content://cn.jhc.startdemo");
//        getContentResolver().query(uri, null, null, null, null);
        bookUri = Uri.parse("content://cn.jhc.startdemo/book");
        userUri = Uri.parse("content://cn.jhc.startdemo/user");
        builder = new StringBuilder();
        builder.append(showResult.getText());
    }

    @OnClick({R.id.inert_book, R.id.query_book, R.id.query_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inert_book:
                insertDataToBook();
                break;
            case R.id.query_book:
                queryBookData();
                break;
            case R.id.query_user:
                queryUserData();
                break;
        }
    }

    //查询User表
    private void queryUserData() {
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, "_id = ?", new String[]{"1"}, null);
        builder.replace(0, builder.length(), "显示结果：");
        while (userCursor.moveToNext()) {
            //注意获取数据时的下表按顺序从0开始
            int id = userCursor.getInt(0);
            String name = userCursor.getString(1);
            int sex = userCursor.getInt(2);
            User user = new User(id, name, sex);
            Log.i("MyContentProvider", user.toString());
            builder.append("\n" + user.toString());
        }
        showResult.setText(builder.toString());
        userCursor.close();
    }

    //查询Book数据
    private void queryBookData() {
        builder.replace(0, builder.length(), "显示结果：");
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            //注意获取数据时的下表按顺序从0开始
            int id = bookCursor.getInt(0);
            String name = bookCursor.getString(1);
            Book book = new Book(id, name);
            Log.i("MyContentProvider", book.toString());
            builder.append("\n" + book.toString());
        }
        showResult.setText(builder.toString());
        bookCursor.close();
    }

    //往book表里面添加数据
    private void insertDataToBook() {
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);
    }
}
