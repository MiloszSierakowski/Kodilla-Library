package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyOfBook {
    private int id;
    private int bookId;
    private String status;
    private boolean isRental;
}
