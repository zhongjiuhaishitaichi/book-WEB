package main.com.WebBook.book.service;


import main.com.WebBook.book.pojo.OrderBean;
import main.com.WebBook.book.pojo.User;

import java.util.List;

public interface OrderService {
    //结账功能
    void addOrderBean(OrderBean orderBean);
    //查看订单
    List<OrderBean> getOrderList(User user);
}
