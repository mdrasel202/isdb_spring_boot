package com.rasel.bank_management.constants;

public enum DepositInterestRate {
    TEN_PERCENT(0.10),
    FIFTEEN_PERCENT(0.15),
    TWENTY_PERCENT(0.20);

    private final double rate;

    DepositInterestRate(double rate){
        this.rate = rate;
    }
    public double getRate(){
        return rate;
    }
}
