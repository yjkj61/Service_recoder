package com.yjkj.service_recoder.java.bean;

import android.app.Activity;
import android.content.Context;

public class CareringServiceData {

    private static CareringServiceData instance;

    private String user;

    public String token;


    private String userName = "请登录";

    private String userHeader;

    private String userId;

    private String phoneNumber;

    private String ownerRemainMoney;

    private String markId = "";
    private String markName = "";
    private String rFoodCanteenId = "";

    public static Context mcontext = null;

    private String rFoodCanteenName = "";

    // 私有构造
    private CareringServiceData(Context context) {
        mcontext = context;
    }

    public static CareringServiceData getInstance(Context context) {
        if (instance == null) {
            instance = new CareringServiceData(context);
        }

        return instance;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
        //return "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX2tleSI6ImZmNDFjZGM0LWJiNzgtNDEyMy1iZGMwLThhM2EyZjhhODBmNCIsInVzZXJuYW1lIjoiYWRtaW4ifQ.mPl-QMmUGaZ3Nj3Ifi7MnVX9dIADoy4vmt6sRuYVkBEV8qnj7nwRlAIupdrzqsPMFKAKeMlzSRd29flLDai1OQ";
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeader() {
        return userHeader;
    }

    public void setUserHeader(String userHeader) {
        this.userHeader = userHeader;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwnerRemainMoney() {
        return ownerRemainMoney;
    }

    public void setOwnerRemainMoney(String ownerRemainMoney) {
        this.ownerRemainMoney = ownerRemainMoney;
    }

    public String getMarkId() {
        return markId;
    }

    public void setMarkId(String markId) {
        this.markId = markId;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String getrFoodCanteenId() {
        return rFoodCanteenId;
    }

    public void setrFoodCanteenId(String rFoodCanteenId) {
        this.rFoodCanteenId = rFoodCanteenId;
    }

    public String getrFoodCanteenName() {
        return rFoodCanteenName;
    }

    public void setrFoodCanteenName(String rFoodCanteenName) {
        this.rFoodCanteenName = rFoodCanteenName;
    }

}
