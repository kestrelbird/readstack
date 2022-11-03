package com.monikapustula.readstack.domain.category;

public class Category {
    private Integer id;
    private String categoryName;
    private String categoryDescription;

    public Category(Integer id, String categoryName, String categoryDescription) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }
}
