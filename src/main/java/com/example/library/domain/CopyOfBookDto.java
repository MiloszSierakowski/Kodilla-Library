package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CopyOfBookDto {
    private Long id;
    private Long bookId;
    private String status;
    private boolean rented;
}
