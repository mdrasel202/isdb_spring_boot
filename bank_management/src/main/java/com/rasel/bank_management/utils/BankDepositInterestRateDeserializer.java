package com.rasel.bank_management.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.rasel.bank_management.constants.BankDepositInterestRate;

import java.io.IOException;

public class BankDepositInterestRateDeserializer extends JsonDeserializer<BankDepositInterestRate> {
    @Override
    public BankDepositInterestRate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String value = p.getText();
        return BankDepositInterestRate.fromLabel(value);
    }
}
