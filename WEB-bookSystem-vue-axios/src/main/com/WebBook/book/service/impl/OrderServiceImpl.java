package main.com.WebBook.book.service.impl;


import main.com.WebBook.book.dao.CartItemDAO;
import main.com.WebBook.book.dao.OrderDAO;
import main.com.WebBook.book.dao.OrderItemDAO;
import main.com.WebBook.book.pojo.CartItem;
import main.com.WebBook.book.pojo.OrderBean;
import main.com.WebBook.book.pojo.OrderItem;
import main.com.WebBook.book.pojo.User;
import main.com.WebBook.book.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;
    private Integer getOrderTotalBookCount;

    @Override
    public void addOrderBean(OrderBean orderBean) {
        //1.订单表要添加一条记录
        //2.订单详情表添加 已支付的那些书的具体的信息记录
        //3.购物车项表里要删除 对应的项

        orderDAO.addOrderBean(orderBean);//orderBean 里的id 是有值的

        //orderItemList 为空
        //把cartItem-->orderItem
        User currentUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currentUser.getCart().getCartItemMap();
        //封装 cartItem-->orderItem
        for (CartItem cartItem : cartItemMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        //这里默认 如果是结账的话 是结一个人的所有账
        for (CartItem cartItem : cartItemMap.values()) {
            cartItemDAO.delCartItem(cartItem);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderBeanList = orderDAO.getOrderList(user);
        for (OrderBean  orderBean : orderBeanList) {
            Integer totalBookCount = orderDAO.getOrderTotalBookCount(orderBean);//每一个订单的总数
            orderBean.setTotalBookCount(totalBookCount);
        }

        return orderBeanList;
    }
}
