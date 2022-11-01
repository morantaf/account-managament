package com.moranta.accountmanagement.util;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountRequest {

    @NotNull
    private String customerId;

    @NotNull
    private BigDecimal initialCredit;

    public AccountRequest(String customerId, BigDecimal initialCredit) {
        this.customerId = customerId;
        this.initialCredit = initialCredit;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }

    @Override
    public String toString() {
        return "AccountRequestDTO{" +
                "customerId='" + customerId + '\'' +
                ", initialCredit=" + initialCredit +
                '}';
    }
}
