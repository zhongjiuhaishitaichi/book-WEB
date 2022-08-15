package main.com.WebBook.book.pojo;


//需要一个cart类 代表购物车实体


import java.math.BigDecimal;

//这个bean 只是后端数据库显示所有的用户购物车的状态  但是当我新假如一个购物车的时候 需要一个购物车对象
public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userBean;
    private Double xj;//解决double 出现好几位小数问题

    //thymeleaf可以调用 但是vue不行
    public Double getXj() {
        //字符串类型
    BigDecimal bigDecimalPrice=new BigDecimal(this.getBook().getPrice()+"");
        BigDecimal bigDecimalBuyCount = new BigDecimal("" + buyCount);
        BigDecimal bigDecimalXj = bigDecimalPrice.multiply(bigDecimalBuyCount);
        double xj = bigDecimalXj.doubleValue();
        return xj;
    }

    public void setXj(Double xj) {
        this.xj = xj;
    }

    public CartItem() {
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }
}
