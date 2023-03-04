package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//todo entity i zależności
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    private Long id;
    private Long copyOfBookId;
    private Long readerId;
    private Date rentDate;
    private Date returnDate;
}
