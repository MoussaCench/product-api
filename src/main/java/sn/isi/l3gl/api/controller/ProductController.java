package sn.isi.l3gl.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.l3gl.core.entity.Product;
import sn.isi.l3gl.core.service.ProductService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateQuantity(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
        int newQuantity = body.get("quantity");
        Product updated = productService.updateQuantity(id, newQuantity);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/low-stock/count")
    public ResponseEntity<Map<String, Long>> countLowStockProducts() {
        long count = productService.countLowStockProducts();
        return ResponseEntity.ok(Map.of("lowStockCount", count));
    }
}
