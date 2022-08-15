package main.com.WebBook.book.dao.impl;

import main.com.WebBook.book.dao.OrderDAO;
import main.com.WebBook.book.pojo.OrderBean;
import main.com.WebBook.book.pojo.User;
import main.com.WebBook.myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
       int orderBeanId= super.executeUpdate
                ("insert into t_order values(0,?,?,?,?,?)",
                        orderBean.getOrderNo(),orderBean.getOrderDate(),
                        orderBean.getOrderUser().getId(),orderBean.getOrderMoney(),orderBean.getOrderStatus());
       //这个方法返回的是插入的行数是最后一行的行数   接受一下
       orderBean.setId(orderBeanId);
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        return  super.executeQuery("select * from t_order where orderUser=?",user.getId());
    }

    @Override
    public Integer getOrderTotalBookCount(OrderBean orderBean) {
        String sql="SELECT SUM(t3.buyCount) AS totalBookCount , t3.orderBean FROM " +
                "(" +
                "SELECT t1.id , t2.buyCount , t2.orderBean FROM t_order t1 INNER JOIN t_order_item t2 " +
                "ON t1.id = t2.orderBean WHERE t1.orderUser = ? " +
                ") t3 WHERE t3.orderBean = ? GROUP BY t3.orderBean" ;

        return ((BigDecimal)super.executeComplexQuery(sql,orderBean.getOrderUser().getId(),orderBean.getId())[0]).intValue();


    }
}
