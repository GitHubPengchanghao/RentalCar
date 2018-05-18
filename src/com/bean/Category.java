package com.bean;

import java.io.Serializable;

/**
 * 汽车类别表
 * @author Administrator
 */
public class Category implements Serializable,Comparable<Category>{
    /**
     *类别编号（自增），类别名称
     */
    private int categoryId;
    private String categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    /**
     * 重写compareTo方法，以类别ID对类别表进行排序。
     * @param o
     * @return
     */
    @Override
    public int compareTo(Category o) {
        return this.getCategoryId() - o.getCategoryId();
    }
}
