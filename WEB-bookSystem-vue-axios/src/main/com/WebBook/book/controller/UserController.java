package main.com.WebBook.book.controller;


import main.com.WebBook.book.pojo.Cart;
import main.com.WebBook.book.pojo.User;
import main.com.WebBook.book.service.CartItemService;
import main.com.WebBook.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//controller  user用于处理用户登陆
// 就在于能为servlet提供转向的地址 中央servlet调用里面的方法 获得转向的网页
public class UserController {

    private UserService userService;
    private CartItemService cartItemService;

    //方法参数从mvc中取  controller里的参数要与前端提交的对应
    public String login(String uname, String pwd, HttpSession session) {
//session 保存作用域 保存user
        User user = userService.login(uname, pwd);
        //System.out.println("user = " + user);
        if (user != null) {

            //把查询到的cart 给到对应的user    cart 为user的属性
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);

            session.setAttribute("currentUser", user);
            //重定向  跳转BookController  默认index 方法
            return "redirect:book.do";
        }
        return "user/login";
    }
    //verifyCode 就是从前端传来的你输入的验证码
    public String regist(String verifyCode, String uname, String pwd, String email, HttpSession session, HttpServletResponse response) throws IOException {
        //得到验证码的内容
        Object kaptchaCode = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (kaptchaCode==null||!verifyCode.equals(kaptchaCode)){
            response.setCharacterEncoding("Utf-8");
            response.setContentType("text/html;charset=UTF-8");
            //打印流
            PrintWriter writer = response.getWriter();
            writer.println("<script language='JavaScript'>alert('验证码不正确!');" +
                    "window.location.href='page.do?operate=page&page=user/regist'</script>");
            //return "user/regist";
            return null;
        }else {
            if (verifyCode.equals(kaptchaCode)){
                //对了 就注册账号
                userService.regist(new User(uname,pwd,email,0));
                return "user/login";
            }
        }
        return "user/login";
    }
}
