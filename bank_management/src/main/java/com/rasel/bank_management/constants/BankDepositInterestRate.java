package com.rasel.bank_management.constants;

public enum BankDepositInterestRate {
    TEN_PERCENT(0.10, "10%"),
    FIFTEEN_PERCENT(0.15, "15%"),
    TWENTY_PERCENT(0.20, "20%");

    private final double rate;
    private final String label;

    BankDepositInterestRate(double rate, String label) {
        this.rate = rate;
        this.label = label;
    }

    public double getRate() {
        return rate;
    }

    public String getLabel() {
        return label;
    }

    public static BankDepositInterestRate fromLabel(String label) {
        for (BankDepositInterestRate r : values()) {
            if (r.label.equalsIgnoreCase(label.trim())) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown interest rate: " + label);
    }
}
