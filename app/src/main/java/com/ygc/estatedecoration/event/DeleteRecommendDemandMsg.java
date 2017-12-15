package com.ygc.estatedecoration.event;

public class DeleteRecommendDemandMsg {

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;

    public DeleteRecommendDemandMsg(int position) {
        this.position = position;
    }
}
