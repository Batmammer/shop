package mk.shop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long sku;

    @NotEmpty(message = "Please provide product name")
    @Size(max = 256, message = "Maximum product name length is 256 characters")
    private String name;

    @NotNull
    @Positive
    private Long price;

    private LocalDateTime created;

    private Boolean deleted;

    public Product() {}

    public Product(@NotEmpty(message = "Please provide product name") @Size(max = 256, message = "Maximum product name length is 256 characters") String name, @Positive Long price) {
        this.name = name;
        this.price = price;
        this.deleted = false;
        this.created = LocalDateTime.now();
    }

    public Product update(Product updatedProduct) {
       if (!this.price.equals(updatedProduct.getPrice())) {
           this.price = updatedProduct.getPrice();
       }
        if (!this.name.equals(updatedProduct.getName())) {
            this.name = updatedProduct.getName();
        }
        return this;
    }

    public Long getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
