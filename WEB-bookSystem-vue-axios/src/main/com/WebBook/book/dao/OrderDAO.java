package main.com.WebBook.book.dao;

import main.com.WebBook.book.pojo.OrderBean;
import main.com.WebBook.book.pojo.User;
import main.com.WebBook.book.service.OrderService;

import java.util.List;

public interface OrderDAO {
    //添加订单
    void addOrderBean(OrderBean orderBean);
    //获取指定用户的订单列表
    List<OrderBean> getOrderList(User user);

    Integer getOrderTotalBookCount(OrderBean orderBean);
}
