package com.example.shopapp.services;

import org.springframework.stereotype.Service;

import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private boolean isEmpty(){
        return productRepository.count() == 0;
    }

    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public Product getProductById(long id){
        var value = productRepository.findById(id);
        return value.orElse(null);
    }
    public List<Product> getProductsByIds(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    public Product getProduct(Product product){
        return getProductById(product.getId());
    }

    public void updateProduct(Product updatedProduct) {
        productRepository.save(updatedProduct);

    }

    public void deleteProduct(Product product){
        productRepository.deleteById(product.getId());
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

}