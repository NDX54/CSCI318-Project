package csci318.demo.model;

import javax.persistence.*;

@Entity
public class ProductDetail {

    @Id
    @GeneratedValue
    private long id;

    private String description;
    private String comment;

    @OneToOne
    private Product product;

    public ProductDetail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
