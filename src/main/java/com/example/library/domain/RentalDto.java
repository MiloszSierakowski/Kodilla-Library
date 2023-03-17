package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private Long id;
    private Long copyOfBookId;
    private Long readerId;
    private LocalDate rentDate;
    private LocalDate returnDate;
}
