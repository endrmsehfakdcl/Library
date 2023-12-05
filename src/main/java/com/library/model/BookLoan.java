package com.library.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookLoan {
    private Long bookLoanId;
    private Date loanDate;
    private Date returnDate;
    private boolean status;
}
