package com.weiss.blog.entity;

/**
 * 保存博客页中查询条件的实体类
 */
public class BlogQuery {

    private String title;

    private boolean recommend;

    private Long typeId;

    public BlogQuery(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
