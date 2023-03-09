package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RentalDto {
    private Long id;
    private CopyOfBook copyOfBook;
    private Reader reader;
    private Date rentDate;
    private Date returnDate;
}
