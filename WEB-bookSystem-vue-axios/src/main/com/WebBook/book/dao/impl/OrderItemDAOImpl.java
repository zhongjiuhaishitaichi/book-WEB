package main.com.WebBook.book.dao.impl;


import main.com.WebBook.book.dao.OrderItemDAO;
import main.com.WebBook.book.pojo.OrderItem;
import main.com.WebBook.myssm.basedao.BaseDAO;

public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item values(0,?,?,?)",
                orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }
}
