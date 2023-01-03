package com.monikapustula.readstack.domain.discovery;

import java.time.LocalDateTime;

public class Discovery {
    private Integer id;
    private String title;
    private String url;
    private String description;
    private LocalDateTime localDateTime;
    private Integer categoryId;
    private Integer userId;

    public Discovery(Integer id, String title, String url, String description, LocalDateTime localDateTime, Integer categoryId, Integer userId) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.localDateTime = localDateTime;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Discovery(String title, String url, String description, LocalDateTime localDateTime, Integer categoryId, Integer userId) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.localDateTime = localDateTime;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
