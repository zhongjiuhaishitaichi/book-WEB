package main.com.WebBook.book.dao.impl;

import main.com.WebBook.book.dao.CartItemDAO;
import main.com.WebBook.book.pojo.CartItem;
import main.com.WebBook.book.pojo.User;
import main.com.WebBook.myssm.basedao.BaseDAO;

import java.util.List;

public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {
    @Override
    public void addCartItem(CartItem cartItem) {
        //添加更新都是这个方法
        super.executeUpdate("insert into t_cart_item values(0,?,?,?)",
                cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUserBean().getId());
    }

    //有的话就更新 0本也是相当于删除呀
    @Override
    public void updateCartItem(CartItem cartItem) {
        super.executeUpdate("update t_cart_item set buyCount=? where id=?",
                cartItem.getBuyCount(),cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        return super.executeQuery("select * from t_cart_item where userBean=?",user.getId());
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        super.executeUpdate("delete from t_cart_item where id=?",cartItem.getId());
    }
}
