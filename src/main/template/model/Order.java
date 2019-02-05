package template.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "pay_date")
    private Timestamp payDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public String getTitle() {
        return product.getTitle();
    }

    public int getPrice() {
        return product.getPrice();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(product.getTitle());
        builder.append(" for ");
        builder.append(product.getPrice());
        builder.append("$ is ordered in  ");
        builder.append(orderDate.toString());
        builder.append((payDate == null) ? (" - was not paid") : (" - was paid at " + payDate));
        builder.append("<br/>");
        return builder.toString();
    }
}
