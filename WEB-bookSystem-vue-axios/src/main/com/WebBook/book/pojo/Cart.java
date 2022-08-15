package main.com.WebBook.book.pojo;


import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

//这是单个人购物车 !!!!  map 的key 是哪本书的id
public class Cart {
    private Map<Integer, CartItem> cartItemMap;  //购物车中 购物项的集合  就是具体到个人 到底买了哪些书
    private Double totalMoney;                  //总的价钱
    private Integer totalCount;                 //一个用户购物车里的购物项的数量 就是买了几种书
    private Integer totalBookCount;             //所有书的总数量
    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        //计算总价
        if (cartItemMap != null && cartItemMap.size() > 0) {
//            entrySet  就是map集合的抽象样式 里面key value 代表着map里面的k--v
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> CartItemEntry : entries) {
                CartItem cartItem = CartItemEntry.getValue();
                //累加  同样Double类型的会造成好几位小数
                //在使用bigDecimal时 尽量使用String 构造器
                String xjStr = cartItem.getXj().toString();
                BigDecimal bigDecimalXj = new BigDecimal(xjStr);

                String totalMoneyStr = totalMoney.toString();
                BigDecimal bigDecimalTotalMoney = new BigDecimal(totalMoneyStr);

                BigDecimal add = bigDecimalTotalMoney.add(bigDecimalXj);
                totalMoney=add.doubleValue();
            }
        }
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            totalCount = cartItemMap.size();//???????
        }
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalBookCount() {
        totalBookCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            for (CartItem cartItem : cartItemMap.values()) {
                totalBookCount = totalBookCount + cartItem.getBuyCount();
            }
        }
        return totalBookCount;
    }

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }
}
