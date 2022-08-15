package main.com.WebBook.book.pojo;

// javaBean里的一定要与数据库的字段保持一致

// 订单详情
// 哪本书 买了多少 属于哪个订单
public class OrderItem {
    private Integer id ;
    private Book book ;                 //M:1
    private Integer buyCount ;
    private OrderBean orderBean ;       //M:1

    public OrderItem(){}

    public OrderItem(Integer id) {
        this.id = id;
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

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

}
