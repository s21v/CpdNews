package com.s21v.vo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Paper {
    private Date date;
    private String number;
    private String name;
    private String type;
    private String xmlPath;
    private String imgPath;
    private boolean isWide;
    private String thumbPath;
    private String fullImgPath;

    public Paper(Date date, String number, String name, String type, String xmlPath, String imgPath, boolean isWide, String thumbPath, String fullImgPath) {
        this.date = date;
        this.number = number;
        this.name = name;
        this.type = type;
        this.xmlPath = xmlPath;
        this.imgPath = imgPath;
        this.isWide = isWide;
        this.thumbPath = thumbPath;
        this.fullImgPath = fullImgPath;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setWide(boolean wide) {
        isWide = wide;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public void setFullImgPath(String fullImgPath) {
        this.fullImgPath = fullImgPath;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "date='" + date + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", xmlPath='" + xmlPath + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", isWide=" + isWide +
                ", thumbPath='" + thumbPath + '\'' +
                ", fullImgPath='" + fullImgPath + '\'' +
                '}';
    }
}
