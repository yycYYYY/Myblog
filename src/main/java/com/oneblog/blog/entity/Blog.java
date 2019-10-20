package com.oneblog.blog.entity;

public class Blog {
    private Integer id;

    private Integer categoryid;

    private String title;

    private String titleintro;

    private String content;

    public Blog(Integer id, Integer categoryid, String title, String titleintro, String content, String md, Long createdtime, Category category) {
        this.id = id;
        this.categoryid = categoryid;
        this.title = title;
        this.titleintro = titleintro;
        this.content = content;
        this.md = md;
        this.createdtime = createdtime;
        this.category = category;
    }

    private String md;

    private Long createdtime;

    public Blog() {
    }

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