package com.bean;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 租赁记录表
 */
public class Record implements Serializable,Comparable<Record>{
    /**
     * 租赁ID（自增），用户ID，汽车ID，租金总额
     * 出租日期，归还日期
     */
    private int recordId;
    private int userId;
    private int carId;
    private int payment;
    private Timestamp starDate;
    private Timestamp returnDate;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Timestamp getStarDate() {
        return starDate;
    }

    public void setStarDate(Timestamp starDate) {
        this.starDate = starDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public Record() {
    }

    public Record(int recordId, int userId, int carId, int payment, Timestamp starDate, Timestamp returnDate) {
        this.recordId = recordId;
        this.userId = userId;
        this.carId = carId;
        this.payment = payment;
        this.starDate = starDate;
        this.returnDate = returnDate;
    }

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Record{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", payment=" + payment +
                ", starDate=" + starDate +
                ", returnDate=" + returnDate +
                '}';
    }

    /**
     * 重写compareTo方法，以租赁ID对租赁记录表进行排序。
     * @param o
     * @return
     */
    @Override
    public int compareTo(Record o) {
        return this.recordId - o.getRecordId();
    }
}
