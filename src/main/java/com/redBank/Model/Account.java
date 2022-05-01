package com.redBank.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    //id
    private String accountNumber;
    private String sheba;
    private Boolean status;
    private Double balance;

    private Long bankId;
    private Long customerCode;
}
