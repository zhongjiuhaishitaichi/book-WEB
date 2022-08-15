package main.com.WebBook.book.dao.impl;


import main.com.WebBook.book.dao.UserDAO;
import main.com.WebBook.book.pojo.User;
import main.com.WebBook.myssm.basedao.BaseDAO;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        //单单使用like 和= 一个效果 因为没有 %
        return super.load("select * from t_user where uname =? and pwd =?" , uname, pwd);
    }

    @Override
    public void addUser(User user) {
        super.executeUpdate("insert into t_user values(0,?,?,?,0)",user.getUname(),user.getPwd(),user.getEmail());
    }
}
