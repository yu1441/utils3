package com.yujing.kotlinapp.activity.page07;

import java.io.Serializable;
import java.util.Date;

public class User07  implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private String carNumber;
    private Date createTime;
    private Integer createYear;
    private Integer createMonth;
    private Integer createDay;
    private String type;//类型 ETC OIL
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateYear() {
        return createYear;
    }

    public void setCreateYear(Integer createYear) {
        this.createYear = createYear;
    }

    public Integer getCreateMonth() {
        return createMonth;
    }

    public void setCreateMonth(Integer createMonth) {
        this.createMonth = createMonth;
    }

    public Integer getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Integer createDay) {
        this.createDay = createDay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Page7Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", createTime=" + createTime +
                ", createYear=" + createYear +
                ", createMonth=" + createMonth +
                ", createDay=" + createDay +
                ", type='" + type + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
