package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RentalDto {
    private Long id;
    private CopyOfBook copyOfBook;
    private Reader reader;
    private LocalDate rentDate;
    private LocalDate returnDate;
}
