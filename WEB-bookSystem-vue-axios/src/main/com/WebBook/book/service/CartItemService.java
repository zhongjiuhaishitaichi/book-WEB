package main.com.WebBook.book.service;

import main.com.WebBook.book.pojo.Cart;
import main.com.WebBook.book.pojo.CartItem;
import main.com.WebBook.book.pojo.User;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);
    //获取指定购物车的所有购物项列表(会有book的详细信息)
    List<CartItem> getCartItemList(User user);
    //当用户登陆的时候 才 展示他的购物车
    Cart getCart(User user);
}
