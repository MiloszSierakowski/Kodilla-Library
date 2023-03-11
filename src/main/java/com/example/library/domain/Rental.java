package com.example.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RENTAL")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "COPY_OF_BOOK_ID")
    private CopyOfBook copyOfBook;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Reader reader;
    @Column(name = "RENT_DATE")
    private LocalDate rentDate;
    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;
}
