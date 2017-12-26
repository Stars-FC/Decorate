package com.ygc.estatedecoration.event;

import java.util.ArrayList;

public class PublishNeedUploadPicMsg {

    public PublishNeedUploadPicMsg(ArrayList<String> picList) {
        this.picList = picList;
    }

    public ArrayList<String> getPicList() {
        return picList;
    }

    public void setPicList(ArrayList<String> picList) {
        this.picList = picList;
    }

    private ArrayList<String> picList;

}
