package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CopyOfBookDto {
    private Long id;
    private Long bookId;
    private String status;
    private boolean isRental;
}
