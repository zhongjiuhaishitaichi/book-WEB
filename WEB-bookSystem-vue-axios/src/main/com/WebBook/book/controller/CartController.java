package main.com.WebBook.book.controller;

import com.google.gson.Gson;
import main.com.WebBook.book.pojo.Book;
import main.com.WebBook.book.pojo.Cart;
import main.com.WebBook.book.pojo.CartItem;
import main.com.WebBook.book.pojo.User;
import main.com.WebBook.book.service.CartItemService;

import javax.servlet.http.HttpSession;


//学习 用vue+axios 实现的前后分离!!!!
public class CartController {
    //session里保存的是user 所以thymeleaf可以渲染所有的user 属性
    private CartItemService cartItemService;
    //加载当前用户的购物车信息
    public String index(HttpSession session){
        //当添加了以后 cart 要更新的 所以session里重新更新user
      User currentUser =(User) session.getAttribute("currentUser");
        Cart cart = cartItemService.getCart(currentUser);
        currentUser.setCart(cart);//更新
        session.setAttribute("currentUser",currentUser);
        //cart文件夹下的 cart
        return "cart/cart";
    }

    public String addCart(Integer bookId, HttpSession session){
        User user =(User) session.getAttribute("currentUser");
        //添加一本书 buyCount=1
        CartItem cartItem = new CartItem(new Book(bookId),1,user);
        //将指定的图书添加到用户的购物车
        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());

        return "redirect:cart.do";
    }
    public String editCart(Integer cartItemId,Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
        return "";
    }

    public String cartInfo(HttpSession session){
        User currentUser =(User) session.getAttribute("currentUser");
        Cart cart = cartItemService.getCart(currentUser);
        //调用cart的get方法 计算那三个值 要不然为空
        //导致的结果就是  下一步的gson 转化时 没有这个属性 所以就带不到前端
        cart.setTotalCount(cart.getTotalCount());
        cart.setTotalMoney(cart.getTotalMoney());
        cart.setTotalBookCount(cart.getTotalBookCount());
        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:"+cartJsonStr;
    }
}
