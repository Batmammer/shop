package mk.shop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Please provide client email")
    @Size(max = 256, message = "Maximum client email is 256 characters")
    private String email;

    @ManyToMany
    @JoinTable(name="tbl_products",
            joinColumns=@JoinColumn(name="sku")
    )
    private List<Product> products;

    private LocalDateTime created;

    @Transient
    private Long total;

    public Order() {}

    public Order(@NotEmpty(message = "Please provide client email") @Size(max = 256, message = "Maximum client email is 256 characters") String email) {
        this.email = email;
        this.created = LocalDateTime.now();
        this.total = 0L;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Order updateTotal() {
        if (products != null) {
            products.stream().mapToLong(product -> product.getPrice()).sum();
        }
        return this;
    }
}
