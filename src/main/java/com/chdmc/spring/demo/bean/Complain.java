package com.chdmc.spring.demo.bean;

import java.util.ArrayList;

/**
 * 投诉信息
 */
public class Complain {
    private String id;
    private String type;
    private String ownerId;
    private String message;
    private String imgCloseUrl;
    private String imgRemoteUlr;
    private String[] imgMoreUrlList;
    private int state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgCloseUrl() {
        return imgCloseUrl;
    }

    public void setImgCloseUrl(String imgCloseUrl) {
        this.imgCloseUrl = imgCloseUrl;
    }

    public String getImgRemoteUlr() {
        return imgRemoteUlr;
    }

    public void setImgRemoteUlr(String imgRemoteUlr) {
        this.imgRemoteUlr = imgRemoteUlr;
    }

    public String[] getImgMoreUrlList() {
        return imgMoreUrlList;
    }

    public void setImgMoreUrlList(String[] imgMoreUrlList) {
        this.imgMoreUrlList = imgMoreUrlList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
