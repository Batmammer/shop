package mk.shop.service;

import mk.shop.entity.Product;
import mk.shop.exception.ProductNotFoundException;
import mk.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> listProducts() {
        return productRepository.findAllByDeletedIsFalse();
    }

    private Product findProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElseThrow(() -> new ProductNotFoundException("Not found product with ID: " + productId));
    }

    public Product findNotDeletedProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findBySkuAndDeletedIsFalse(productId);
        return productOptional.orElseThrow(() -> new ProductNotFoundException("Not found product with ID: " + productId));
    }

    public Product getProduct(Long productId) {
        return findProduct(productId);
    }

    public void deleteProduct(Long productId) {
        Product product = findNotDeletedProduct(productId);
        product.setDeleted(true);
    }

    public Product createProduct(Product newProduct) {
        Product product = new Product(newProduct.getName(), newProduct.getPrice());
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product product = findNotDeletedProduct(productId);
        return product.update(updatedProduct);
    }
}
