package com.moranta.accountmanagement.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountRequest {

    @NotNull
    private String customerId;

    @NotNull
    private BigDecimal initialCredit;

    @Override
    public String toString() {
        return "AccountRequestDTO{" +
                "customerId='" + customerId + '\'' +
                ", initialCredit=" + initialCredit +
                '}';
    }
}
