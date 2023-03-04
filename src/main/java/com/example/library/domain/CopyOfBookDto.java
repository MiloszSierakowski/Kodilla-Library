package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CopyOfBookDto {
    private int id;
    private int bookId;
    private String status;
    private boolean isRental;
}
