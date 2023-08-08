package csci318.demo.model;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private long id;

    private String supplier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer companyName;  // 이 부분은 주문을 한 회사를 나타냅니다.

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Customer companyName) {
        this.companyName = companyName;
    }
}
