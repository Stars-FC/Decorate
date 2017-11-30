package com.ygc.estatedecoration.event;

public class ContractStateMsg {

    private int contractState;

    public ContractStateMsg(int contractState) {
        this.contractState = contractState;
    }

    public int getContractState() {
        return contractState;
    }

    public void setContractState(int contractState) {
        this.contractState = contractState;
    }
}
