package sn.isi.l3gl.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.l3gl.core.entity.Product;
import sn.isi.l3gl.core.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product updateQuantity(Long id, int newQuantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'id : " + id));
        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    public long countLowStockProducts() {
        return productRepository.countByQuantityLessThanEqual(5);
    }
}
