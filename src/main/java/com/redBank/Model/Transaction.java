package com.redBank.Model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Transaction {
    private String srcAccountNumber;
    private String desAccountNumber;
    private Double amount;
    private Date tDate;
    private Boolean status;
    private String description;
    private TransactionType type;
}
