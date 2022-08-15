package main.com.WebBook.book.service;


import main.com.WebBook.book.pojo.User;

public interface UserService {
    User login(String uname , String pwd );
    void regist(User user);
}
