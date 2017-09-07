package cn.jhc.startdemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.jhc.startdemo.aidl.Book;
import cn.jhc.startdemo.aidl.IBookManager;

public class AIDLService extends Service {

    private List<Book> list;

    public AIDLService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        list = new ArrayList<>();
        list.add(new Book(1, "book1"));
        list.add(new Book(2, "Book2"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private IBookManager.Stub binder = new IBookManager.Stub() {

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (list) {
                if (!list.contains(book)) {
                    Log.i("AIDLService", book.toString());
                    list.add(book);
                }
            }
        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (list) {
                return list;
            }
        }
    };
}
