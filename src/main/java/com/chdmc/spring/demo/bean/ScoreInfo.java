package com.chdmc.spring.demo.bean;

/**
 * 积分信息
 */
public class ScoreInfo {
    /**
     * 积分类型
     */
    private String type;
    private int score;
    private int ownerId;
    /**
     * 备用额外字段
     */
    private String ext;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
