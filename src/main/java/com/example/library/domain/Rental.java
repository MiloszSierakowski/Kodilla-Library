package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    private int id;
    private int copyOfBookId;
    private int readerId;
    private Date rentDate;
    private Date returnDate;
}
