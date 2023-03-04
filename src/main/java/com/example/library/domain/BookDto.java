package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class BookDto {
    private int id;
    private String title;
    private String author;
    private Date publicationDate;
}
