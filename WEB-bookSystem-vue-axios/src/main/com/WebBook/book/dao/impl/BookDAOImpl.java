package main.com.WebBook.book.dao.impl;

import main.com.WebBook.book.dao.BookDAO;
import main.com.WebBook.book.pojo.Book;
import main.com.WebBook.myssm.basedao.BaseDAO;

import java.util.List;

public class BookDAOImpl  extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
      return super.executeQuery("select * from t_book");
    }

    @Override
    public Book getBook(Integer id) {
        return  super.load("select * from t_book where id=?",id);
    }
}
