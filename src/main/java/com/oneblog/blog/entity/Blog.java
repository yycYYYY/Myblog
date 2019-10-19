package com.oneblog.blog.entity;

import java.util.Date;

public class Blog {
    private Integer id;

    private Integer categoryid;

    private String title;

    private String titleintro;

    private String content;

    private String md;

    private Long createdtime;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitleintro() {
        return titleintro;
    }

    public void setTitleintro(String titleintro) {
        this.titleintro = titleintro == null ? null : titleintro.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md == null ? null : md.trim();
    }

    public Long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Long createdtime) {
        this.createdtime = createdtime;
    }
}