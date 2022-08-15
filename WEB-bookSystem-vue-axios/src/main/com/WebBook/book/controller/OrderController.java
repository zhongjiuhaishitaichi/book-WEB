package main.com.WebBook.book.controller;

import main.com.WebBook.book.pojo.OrderBean;
import main.com.WebBook.book.pojo.User;
import main.com.WebBook.book.service.OrderService;


import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderService orderService;


    //结账
    public String checkOut(HttpSession session) {
        OrderBean orderBean = new OrderBean();
        Date date = new Date();
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String nowStr = simpleDataFormat.format(date);
        String[] splits = nowStr.split("-");
        String dateStr = "";
        for (int i = 0; i < splits.length; i++) {
            dateStr += splits[i];
        }
        orderBean.setOrderNo(UUID.randomUUID().toString() + dateStr);
        orderBean.setOrderDate(date);

        User currentUser = (User) session.getAttribute("currentUser");
        orderBean.setOrderUser(currentUser);
        orderBean.setOrderMoney(currentUser.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);
        return "index";
    }

    //查看订单列表
    public String getOrderList(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        List<OrderBean> orderList = orderService.getOrderList(user);
        user.setOrderList(orderList);
        session.setAttribute("currentUser",user);

        return "order/order";
    }
}
