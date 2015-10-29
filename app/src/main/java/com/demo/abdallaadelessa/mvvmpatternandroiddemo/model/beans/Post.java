package com.demo.abdallaadelessa.mvvmpatternandroiddemo.model.beans;

/**
 * Created by abdallah on 29/10/15.
 */
public class Post {
    private String text;
    private String postedBy;
    private String updateDate;
    private String createDate;

    public Post() {
    }

    public Post(String text, String postedBy, String updateDate, String createDate) {
        this.text = text;
        this.postedBy = postedBy;
        this.updateDate = updateDate;
        this.createDate = createDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "text='" + text + '\'' +
                ", postedBy='" + postedBy + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
