package csci318.demo.controller;

import csci318.demo.model.ProductDetail;
import csci318.demo.repository.ProductDetailRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductDetailController {

    private final ProductDetailRepository productDetailRepository;

    public ProductDetailController(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    @PostMapping("/product-details")
    public ProductDetail createOrUpdateProductDetail(@RequestBody ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @PutMapping("/product-details/{id}")
    public ProductDetail updateProductDetail(@PathVariable Long id, @RequestBody ProductDetail updatedProductDetail) {
        ProductDetail existingProductDetail = productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductDetail not found"));

        existingProductDetail.setDescription(updatedProductDetail.getDescription());
        existingProductDetail.setComment(updatedProductDetail.getComment());

        return productDetailRepository.save(existingProductDetail);
    }
}
