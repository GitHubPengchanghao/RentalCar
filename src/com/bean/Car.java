package com.bean;

import java.io.Serializable;

/**
 * 汽车表
 */
public class Car implements Serializable,Comparable <Car>{
    /**
     * 汽车ID（自增），品牌ID，类别ID，汽车价格，汽车租金，汽车出租状态，汽车上架
     * 汽车牌号，汽车型号，汽车颜色，汽车简介。
     */
    private int carId;
    private int brandId;
    private int categoryId;
    private int carPrice;
    private int carRent;
    private int carStatus;
    private int carUseable;
    private String carNumber;
    private String carModel;
    private String carColor;
    private String carComments;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public int getCarRent() {
        return carRent;
    }

    public void setCarRent(int carRent) {
        this.carRent = carRent;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(int carStatus) {
        this.carStatus = carStatus;
    }

    public int getCarUseable() {
        return carUseable;
    }

    public void setCarUseable(int carUseable) {
        this.carUseable = carUseable;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarComments() {
        return carComments;
    }

    public void setCarComments(String carComments) {
        this.carComments = carComments;
    }

    public Car() {
    }

    public Car(int carId, int brandId, int categoryId, int carPrice, int carRent, int carStatus, int carUseable, String carNumber, String carModel, String carColor, String carComments) {
        this.carId = carId;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.carPrice = carPrice;
        this.carRent = carRent;
        this.carStatus = carStatus;
        this.carUseable = carUseable;
        this.carNumber = carNumber;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carComments = carComments;
    }

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        return carId + "\t\t\t" +
                carModel + "\t\t" +
                carComments + "\t" +
                brandId + "\t\t\t" +
                categoryId + "\t\t" +
                carRent + "\t\t\t" +
                carStatus + "\t\t";
    }

    public String toString1() {
        return carId + "\t\t\t" +
                carModel + "\t\t" +
                carComments + "\t" +
                brandId + "\t\t\t" +
                categoryId + "\t\t" +
                carRent + "\t\t\t" +
                carStatus + "\t\t\t\t" +
                carUseable;
    }

    /**
     * 重写compareTo方法，以汽车ID对汽车表进行排序。
     * @param o
     * @return
     */
    @Override
    public int compareTo(Car o) {
        return this.getBrandId() - o.getBrandId();
    }
}
