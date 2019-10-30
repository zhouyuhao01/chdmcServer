package com.chdmc.spring.demo.bean;

public class CollectionOrder {
    private String ownerId;
    private String type;
    private String[] imgUrlList;
    private String orderMessage;
    private String orderCallNum;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(String[] imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public String getOrderCallNum() {
        return orderCallNum;
    }

    public void setOrderCallNum(String orderCallNum) {
        this.orderCallNum = orderCallNum;
    }
}
