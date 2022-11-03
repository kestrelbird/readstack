package com.monikapustula.readstack.domain.api;

public class CategoryName {
    private Integer id;
    private String categoryName;

    public CategoryName(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
