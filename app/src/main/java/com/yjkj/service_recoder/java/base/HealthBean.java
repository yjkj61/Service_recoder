package com.yjkj.service_recoder.java.base;

public class HealthBean {

    public String title = "";

    public String value = "";

    public int status;

    public int bg_res;

    public HealthBean(String a, String b, int c, int d){
        this.title = a;
        this.value = b;
        this.status = c;
        this.bg_res = d;
    }

    public int getBg_res() {
        return bg_res;
    }

    public void setBg_res(int bg_res) {
        this.bg_res = bg_res;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
