package com.ygc.estatedecoration.entity;

/**
 * Created by FC on 2017/11/16.
 * 用于显示主页-我的访客页面嵌套的recyclerview内的数据内容
 */

public class HomeMyVisitor {
    private String image;
    private String name;
    private String time;

    public HomeMyVisitor(String image, String name, String time) {
        this.image = image;
        this.name = name;
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
