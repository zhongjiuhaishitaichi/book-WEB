package main.com.WebBook.book.dao;

import main.com.WebBook.book.pojo.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBookList();

    Book getBook(Integer id);
}
