package cn.jhc.startdemo.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {
    private static final String TAG = "BookProvider";
    public static final String AUTHORITY = "cn.jhc.startdemo";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;
    private static SQLiteDatabase dbData;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        uriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    public MyContentProvider() {
    }

    private String getTableName(Uri uri) {
        String table_name = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                table_name = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                table_name = DbOpenHelper.USER_TABLE_NAME;
                break;
        }
        return table_name;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i("MyContentProvider", "delete");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        return dbData.delete(table, selection, selectionArgs);
    }

    /*  必须以vnd开头
        如果uri是以路径结尾的，就有android.cursor.dir/
        如果uri是以id结尾的，就用android.cursor.item/
        最后接上 vnd.<authority>.<path>
    */
    @Override
    public String getType(Uri uri) {
        Log.i("MyContentProvider", "getType");
        switch (uriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                return "vnd.android,cursor.dir/vnd.cn.jhc.startdemo.book";
            case USER_URI_CODE:
                return "vnd.android,cursor.dir/vnd.cn.jhc.startdemo.user";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i("MyContentProvider", "insert");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        dbData.insert(table, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public boolean onCreate() {
        dbData = new DbOpenHelper(getContext()).getWritableDatabase();
        dbData.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        dbData.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);
        dbData.execSQL("insert into book values(3,'Android');");
        dbData.execSQL("insert into book values(4,'IOS');");
        dbData.execSQL("insert into book values(5,'HTML5');");

        dbData.execSQL("insert into user values(1,'jake',1);");
        dbData.execSQL("insert into user values(2,'rose',1);");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.i("MyContentProvider", "query");
        Log.i("MyContentProvider", "query currentThread:" + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        return dbData.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.i("MyContentProvider", "update");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        int row = dbData.update(table, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}
