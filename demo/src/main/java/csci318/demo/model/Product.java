package csci318.demo.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String productCategory;

    @Column(nullable = false)
    private String name;

    private double price;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
