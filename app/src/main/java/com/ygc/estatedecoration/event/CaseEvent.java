package com.ygc.estatedecoration.event;

public class CaseEvent {

    private String caseStyle;

    private String caseState;

    public CaseEvent(String caseStyle, String caseState) {
        this.caseStyle = caseStyle;
        this.caseState = caseState;
    }

    public String getCaseStyle() {
        return caseStyle;
    }

    public void setCaseStyle(String caseStyle) {
        this.caseStyle = caseStyle;
    }

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }
}
