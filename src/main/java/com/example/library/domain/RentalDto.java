package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RentalDto {
    private int id;
    private int copyOfBookId;
    private int readerId;
    private Date rentDate;
    private Date returnDate;
}
