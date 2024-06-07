package com.yjkj.service_recoder.java.entity;

import java.util.List;

public class SleepTime {
    private String datTime;

    private List<Float> childBens;

    public String getDatTime() {
        return datTime;
    }

    public void setDatTime(String datTime) {
        this.datTime = datTime;
    }


    public List<Float> getChildBens() {
        return childBens;
    }

    public void setChildBens(List<Float> childBens) {
        this.childBens = childBens;
    }
}
