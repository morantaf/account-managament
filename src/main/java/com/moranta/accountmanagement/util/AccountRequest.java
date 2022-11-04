package com.moranta.accountmanagement.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountRequest {

    @NotNull
    private String customerId;

    @Min(value = 0, message = "initialCredit can't be negative")
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
