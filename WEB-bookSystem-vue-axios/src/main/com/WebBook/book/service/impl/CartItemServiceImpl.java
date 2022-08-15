package main.com.WebBook.book.service.impl;

import main.com.WebBook.book.dao.CartItemDAO;
import main.com.WebBook.book.pojo.Book;
import main.com.WebBook.book.pojo.Cart;
import main.com.WebBook.book.pojo.CartItem;
import main.com.WebBook.book.pojo.User;
import main.com.WebBook.book.service.BookService;
import main.com.WebBook.book.service.CartItemService;
import main.com.WebBook.myssm.basedao.BaseDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl extends BaseDAO<CartItem> implements CartItemService {
    private CartItemDAO cartItemDAO;
    private BookService bookService;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    //1.如果已经有书了 就 数量＋1
    //2.如果没有的话 在 购物车里面 新增一个这个 书的 CartItem 数量是1
    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //判断当前用户的购物车是否有这本书的cartItem 有update数量 无add 书编号 数量 用户
        if (cart != null) {
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null) {
                cartItemMap = new HashMap<>();
            }
            //map里面有对应的这个图书
            if (cartItemMap.containsKey(cartItem.getBook().getId())) {
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount() + 1);
                updateCartItem(cartItemTemp);
            } else {//没有这本书
                addCartItem(cartItem);
            }
        } else {//连购物车都没有
            addCartItem(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        for(CartItem cartItem:cartItemList){
            //查到具体的book  不能只有id
           Book book= bookService.getBook(cartItem.getBook().getId());
           cartItem.setBook(book);
           //get方法  就会把xj赋给这个属性了
            cartItem.setXj(cartItem.getXj());
        }
        return cartItemList;
    }

    @Override
    public Cart getCart(User user) {
        //得到一个人的购物车里的所有购物项
        //这里调用自己的 方法getCartItemList 里面的book是具体的信息
        List<CartItem> cartItemList = this.getCartItemList(user);
        //放入map集合
        HashMap<Integer, CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList) {
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }
        //设置cart  其他的会自己计算的
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;//现在购物车里面就有了 book的具体信息
    }
}
