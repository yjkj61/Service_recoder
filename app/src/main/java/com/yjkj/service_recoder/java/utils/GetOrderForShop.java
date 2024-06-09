package com.yjkj.service_recoder.java.utils;


import com.yjkj.service_recoder.java.bean.BuyGoodBean;
import com.yjkj.service_recoder.java.bean.OrderGood;

import java.util.List;

public class GetOrderForShop {


    private static volatile GetOrderForShop instance;

    private String addressId = "";

    private String address = "";
    private String name="";
    private String phoneNumber;

    private List<BuyGoodBean>  buyGoodBeans;

    private List<OrderGood>  goods;

    private GetOrderForShop() {
// 私有构造函数
    }

    public static GetOrderForShop getInstance() {
        if (instance == null) {
            synchronized (GetOrderForShop.class) {
                if (instance == null) {
                    instance = new GetOrderForShop();
                }
            }
        }
        return instance;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<BuyGoodBean> getBuyGoodBeans() {
        return buyGoodBeans;
    }

    public void setBuyGoodBeans(List<BuyGoodBean> buyGoodBeans) {
        this.buyGoodBeans = buyGoodBeans;
    }

    public List<OrderGood> getGoods() {
        return goods;
    }

    public void setGoods(List<OrderGood> goods) {
        this.goods = goods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
