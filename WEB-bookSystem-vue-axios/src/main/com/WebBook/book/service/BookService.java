package main.com.WebBook.book.service;

import main.com.WebBook.book.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    Book getBook(Integer id);
}
