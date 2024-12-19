package com.example.lab8.service;


import com.example.lab8.product.Category;
import com.example.lab8.product.Product;
import com.example.lab8.repository.CategoryRepository;
import com.example.lab8.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    private boolean isEmpty(){
        return categoryRepository.count() == 0;
    }

    public List<Category> getCategoryList(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public Category getCategoryById(long id){
        var value = categoryRepository.findById(id);
        return value.orElse(null);
    }

    public Category getCategory(Category category){
        return getCategoryById(category.getId());
    }

    public void updateCategory(Category category) {
        categoryRepository.save(category);

    }

    public void deleteCategory(Category category){
        categoryRepository.deleteById(category.getId());
    }

    public void deleteCategoryById(long id) {
        List<Product> products = productRepository.findByCategoryId(id);
        productRepository.deleteAll(products);
        categoryRepository.deleteById(id);
    }

}
