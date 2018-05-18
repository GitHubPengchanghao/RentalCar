package com.bean;

import java.io.Serializable;

/**
 * 汽车品牌表
 * @author Administrator
 */
public class Brand implements Serializable,Comparable<Brand>{
    /**
     * 汽车品牌ID（自增），汽车品牌名
     */
    private int brandId;
    private String brandName;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Brand() {
    }

    public Brand(int brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                '}';
    }

    /**
     * 重写compareTo方法，以品牌ID对品牌表进行排序。
     * @param o
     * @return
     */
    @Override
    public int compareTo(Brand o) {
        return this.getBrandId() - o.getBrandId();
    }
}
