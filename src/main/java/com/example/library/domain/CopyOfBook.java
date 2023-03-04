package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//todo entity i zależności
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyOfBook {
    private Long id;
    private int bookId;
    private String status;
    private boolean isRental;
}
