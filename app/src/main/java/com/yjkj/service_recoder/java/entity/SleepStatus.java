package com.yjkj.service_recoder.java.entity;

public class SleepStatus {
    private String type;
    private String data;
    private String context;

    public SleepStatus(String type, String data, String context) {
        this.type = type;
        this.data = data;
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
