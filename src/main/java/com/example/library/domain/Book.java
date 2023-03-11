package com.example.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "PUBLICATION_DATE")
    private LocalDate publicationDate;

    @OneToMany(
            targetEntity = CopyOfBook.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<CopyOfBook> copyOfBookList = new ArrayList<>();
}
