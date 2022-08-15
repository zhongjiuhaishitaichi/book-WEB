package main.com.WebBook.book.dao;


import main.com.WebBook.book.pojo.User;

public interface UserDAO {
    User getUser(String uname , String pwd );
    void addUser(User user);
}
