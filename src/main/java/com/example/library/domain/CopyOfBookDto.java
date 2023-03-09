package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CopyOfBookDto {
    private Long id;
    private Book book;
    private String status;
    private boolean isRental;
    private List<Rental> rentalList;
}
