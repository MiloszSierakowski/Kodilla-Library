package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private List<CopyOfBook> copyOfBookList;
}
