package com.ygc.estatedecoration.event;

import android.content.Intent;

public class ChangeContractStateMsg {

    private Intent intent;

    private int requestCode;

    private int resultCode;

    public ChangeContractStateMsg(Intent intent, int requestCode, int resultCode) {
        this.intent = intent;
        this.requestCode = requestCode;
        this.resultCode = resultCode;
    }


    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        intent = intent;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
