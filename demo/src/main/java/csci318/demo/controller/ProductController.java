package csci318.demo.controller;

import csci318.demo.repository.ProductRepository;
import csci318.demo.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping("/products")
    Product createProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @PutMapping("/products/{id}")
    Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
