package com.bartmilan.e_comm_api.service;

import com.bartmilan.e_comm_api.model.Category;
import com.bartmilan.e_comm_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

    public Category update(Long id, Category updated){
        Category current = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        current.setName(updated.getName());
        current.setDescription(updated.getDescription());
        return categoryRepository.save(current);
    }

}
