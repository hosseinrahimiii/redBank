package com.redBank.Model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CreditCard {
    private String cardNumber;
    private String cvv2;
    private Date expiration;

    private String accountNumber;
}
