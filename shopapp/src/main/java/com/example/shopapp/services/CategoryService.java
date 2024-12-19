package com.example.shopapp.services;

import com.example.shopapp.models.Category;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.CategoryRepository;
import com.example.shopapp.repositories.ProductRepository;
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