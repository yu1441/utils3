package com.yujing.kotlinapp.activity.page07;

import java.io.Serializable;
import java.util.List;

public class Status07<T> implements Serializable {
    private boolean status;
    private String message;
    private List<T> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
