package main.com.WebBook.book.service.impl;

import main.com.WebBook.book.dao.UserDAO;
import main.com.WebBook.book.service.UserService;
import main.com.WebBook.book.pojo.User;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO ;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }
}
