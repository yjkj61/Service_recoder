package com.yjkj.service_recoder.java.bean;

public class OrderGood {
    public String name;
    public String rule;
    public String goodsCoverImages;

    public int number;

    public double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getGoodsCoverImages() {
        return goodsCoverImages;
    }

    public void setGoodsCoverImages(String goodsCoverImages) {
        this.goodsCoverImages = goodsCoverImages;
    }
}
