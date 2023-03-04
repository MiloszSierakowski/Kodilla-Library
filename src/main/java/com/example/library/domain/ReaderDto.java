package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private int id;
    private String firstname;
    private String lastname;
    private Date registrationDate;
}
