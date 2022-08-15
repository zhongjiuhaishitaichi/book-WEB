package main.com.WebBook.book.service.impl;

import main.com.WebBook.book.dao.BookDAO;
import main.com.WebBook.book.pojo.Book;
import main.com.WebBook.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    //ioc
    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDAO.getBook(id);
    }
}
