package com.yjkj.service_recoder.java.bean;

public class GridBean {
    private float toData;
    private String bottomText;

    private String type;

    private int max;

    public GridBean(float toData, String bottomText, String type, int max) {
        this.toData = toData;
        this.bottomText = bottomText;
        this.type = type;
        this.max = max;
    }

    public float getToData() {
        return toData;
    }

    public void setToData(int toData) {
        this.toData = toData;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
