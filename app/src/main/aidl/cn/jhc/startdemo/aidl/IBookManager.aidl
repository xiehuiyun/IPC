// IBookManager.aidl
package cn.jhc.startdemo.aidl;

// Declare any non-default types here with import statements
import cn.jhc.startdemo.aidl.Book;
interface IBookManager {

    void addBook( in Book book);

    List<Book> getBooks();
}
