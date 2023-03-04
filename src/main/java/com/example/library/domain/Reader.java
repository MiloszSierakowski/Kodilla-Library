package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//todo entity i zależności
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reader {
    private Long id;
    private String firstname;
    private String lastname;
    private Date registrationDate;
}
