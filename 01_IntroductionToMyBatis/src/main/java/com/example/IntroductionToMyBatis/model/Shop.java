package com.example.IntroductionToMyBatis.model;

import java.io.Serializable;

public class Shop implements Serializable {
    private int shopNo;
    private String shopName;
    private String shopLocation;

    public Shop() {

    }

    public Shop(int shopNo, String shopName, String shopLocation) {
        this.shopNo = shopNo;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
    }

    public int getShopNo() {
        return this.shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLocation() {
        return this.shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

}
