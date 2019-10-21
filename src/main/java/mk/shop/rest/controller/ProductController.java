package mk.shop.rest.controller;

import mk.shop.entity.Product;
import mk.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> listProducts() {
        return productService.listProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable(name = "productId") Long productId) {
        return productService.getProduct(productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable(name = "productId") Long productId) {
        productService.deleteProduct(productId);
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable(name = "productId") Long productId, @Valid @RequestBody Product updatedProduct) {
        return productService.updateProduct(productId, updatedProduct);
    }
}
