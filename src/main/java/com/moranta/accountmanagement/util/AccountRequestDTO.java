package com.moranta.accountmanagement.util;

import java.math.BigDecimal;

public class AccountRequestDTO {

    String customerId;
    BigDecimal initialCredit;

    public AccountRequestDTO(String customerId, BigDecimal initialCredit) {
        this.customerId = customerId;
        this.initialCredit = initialCredit;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(BigDecimal initialCredit) {
        this.initialCredit = initialCredit;
    }

    @Override
    public String toString() {
        return "AccountRequestDTO{" +
                "customerId='" + customerId + '\'' +
                ", initialCredit=" + initialCredit +
                '}';
    }
}
