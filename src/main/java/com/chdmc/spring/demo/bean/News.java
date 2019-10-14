package com.chdmc.spring.demo.bean;

/**
 * 新闻
 */
public class News {
    private String url;
    private String imgUrl;
    private String title;
    private int read_count;
    private long newsTimeStamp;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }

    public long getNewsTimeStamp() {
        return newsTimeStamp;
    }

    public void setNewsTimeStamp(long newsTimeStamp) {
        this.newsTimeStamp = newsTimeStamp;
    }
}
