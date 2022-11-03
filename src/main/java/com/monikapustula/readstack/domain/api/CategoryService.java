package com.monikapustula.readstack.domain.api;

import com.monikapustula.readstack.domain.category.Category;
import com.monikapustula.readstack.domain.category.CategoryDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();

    public List<CategoryName> findAllCategories() {
       return categoryDao.findAllCategories()
                .stream()
                .map(CategoryService::CategoryNameMapper)
                .collect(Collectors.toList());
    }

    public Optional<CategoryFullInfo> findById(int categoryId) {
        return categoryDao.findById(categoryId)
                .map(CategoryService::CategoryFullInfoMapper);
    }

    private static CategoryName CategoryNameMapper(Category category) {
        return new CategoryName(category.getId(), category.getCategoryName());
    }

    private static CategoryFullInfo CategoryFullInfoMapper(Category category) {
        return new CategoryFullInfo(category.getId(), category.getCategoryName(), category.getCategoryDescription());
    }
}
